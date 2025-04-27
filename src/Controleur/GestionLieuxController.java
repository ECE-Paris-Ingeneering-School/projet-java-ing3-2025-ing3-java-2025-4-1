package Controleur;

import Vue.GestionLieuxFrame;
import dao.LieuDAO;
import Model.Lieu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Contrôleur responsable de la gestion des lieux dans l'interface d'administration.
 * Permet à l'administrateur d'ajouter, modifier, supprimer et visualiser les lieux.
 *
 * Vue : {@link GestionLieuxFrame}
 * DAO : {@link LieuDAO}
 *
 * Cette classe suit le pattern MVC pour assurer la séparation des responsabilités.
 *
 */
public class GestionLieuxController {
    private GestionLieuxFrame view;
    private LieuDAO dao;

    /**
     * Initialise la vue de gestion des lieux et configure tous les listeners.
     */
    public GestionLieuxController() {
        this.view = new GestionLieuxFrame();
        this.dao = new LieuDAO();

        loadLieux();

        // Ajout d’un lieu
        view.addAjouterListener(e -> {
            String nom = view.getNom();
            String adresse = view.getAdresse();
            String ville = view.getVille();
            String cp = view.getCodePostal();

            if (nom.isEmpty() || adresse.isEmpty() || ville.isEmpty() || cp.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.");
                return;
            }

            Lieu lieu = new Lieu(nom, adresse, ville, cp);
            if (dao.create(lieu)) {
                loadLieux();
                view.clearFields();
                JOptionPane.showMessageDialog(view, "Lieu ajouté !");
            } else {
                JOptionPane.showMessageDialog(view, "Erreur lors de l'ajout.");
            }
        });

        // Modification d’un lieu
        view.addModifierListener(e -> {
            int id = view.getSelectedLieuId();
            if (id == -1) {
                JOptionPane.showMessageDialog(view, "Sélectionnez un lieu à modifier.");
                return;
            }

            String nom = view.getNom();
            String adresse = view.getAdresse();
            String ville = view.getVille();
            String cp = view.getCodePostal();

            if (nom.isEmpty() || adresse.isEmpty() || ville.isEmpty() || cp.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.");
                return;
            }

            Lieu lieu = new Lieu(id, nom, adresse, ville, cp);
            if (dao.update(lieu)) {
                loadLieux();
                view.clearFields();
                JOptionPane.showMessageDialog(view, "Lieu modifié !");
            } else {
                JOptionPane.showMessageDialog(view, "Erreur lors de la modification.");
            }
        });

        // Suppression d’un lieu
        view.addSupprimerListener(e -> {
            int id = view.getSelectedLieuId();
            if (id == -1) {
                JOptionPane.showMessageDialog(view, "Sélectionnez un lieu à supprimer.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(view, "Confirmer la suppression ?", "Attention", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (dao.delete(id)) {
                    loadLieux();
                    view.clearFields();
                    JOptionPane.showMessageDialog(view, "Lieu supprimé.");
                } else {
                    JOptionPane.showMessageDialog(view, "Erreur lors de la suppression.");
                }
            }
        });

        // Retour à l’accueil administrateur
        view.addRetourListener(e -> {
            view.dispose();
            new AdministrationController();
        });

        view.setVisible(true);
    }

    /**
     * Recharge les lieux depuis la base et les affiche dans la JTable.
     */
    private void loadLieux() {
        List<Lieu> lieux = dao.findAll();
        String[] columns = {"ID", "Nom", "Adresse", "Ville", "Code postal"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Lieu l : lieux) {
            Object[] row = {l.getId(), l.getNomEtablissement(), l.getAdresse(), l.getVille(), l.getCodePostal()};
            model.addRow(row);
        }

        view.setTableModel(model);
    }
}
