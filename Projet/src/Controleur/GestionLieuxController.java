
package Controleur;

import Vue.GestionLieuxFrame;
import dao.LieuDAO;
import Model.Lieu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GestionLieuxController {
    private GestionLieuxFrame view;
    private LieuDAO dao;

    public GestionLieuxController() {
        this.view = new GestionLieuxFrame();
        this.dao = new LieuDAO();

        loadLieux();

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

        view.setVisible(true);
    }

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
