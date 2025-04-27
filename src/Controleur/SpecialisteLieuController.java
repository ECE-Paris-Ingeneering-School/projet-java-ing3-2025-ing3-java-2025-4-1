package Controleur;

import Vue.SpecialisteLieuFrame;
import dao.SpecialisteDAO;
import dao.LieuDAO;
import dao.SpecialisteLieuDAO;
import Model.Specialiste;
import Model.Lieu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Contrôleur de la vue d'affectation Spécialiste ↔ Lieu pour les administrateurs.
 * Permet d'affecter ou de désaffecter un spécialiste à un lieu, et d'afficher toutes les affectations.
 *
 * Vue : {@link SpecialisteLieuFrame}
 * DAO : {@link SpecialisteDAO}, {@link LieuDAO}, {@link SpecialisteLieuDAO}
 *
 * Fonctionnalités :
 * - Lister les affectations existantes
 * - Ajouter une affectation
 * - Supprimer une affectation
 * - Retour à l’accueil admin
 *
 * Cette classe complète le module de gestion des affectations dans l’architecture MVC.
 *
 */
public class SpecialisteLieuController {
    private SpecialisteLieuFrame view;
    private SpecialisteDAO specialisteDAO;
    private LieuDAO lieuDAO;
    private SpecialisteLieuDAO slDAO;
    private List<Specialiste> specialistes;
    private List<Lieu> lieux;

    /**
     * Initialise la vue d'affectation et configure les actions utilisateurs.
     */
    public SpecialisteLieuController() {
        this.view = new SpecialisteLieuFrame();
        this.specialisteDAO = new SpecialisteDAO();
        this.lieuDAO = new LieuDAO();
        this.slDAO = new SpecialisteLieuDAO();

        specialistes = specialisteDAO.findAll();
        lieux = lieuDAO.findAll();

        // Remplissage des comboBox
        view.setSpecialistes(specialistes.stream()
                .map(s -> s.getPrenom() + " " + s.getNom() + " (ID:" + s.getId() + ")")
                .toArray(String[]::new));

        view.setLieux(lieux.stream()
                .map(l -> l.getNomEtablissement() + " - " + l.getVille() + " (ID:" + l.getId() + ")")
                .toArray(String[]::new));

        loadAffectations();

        // Affectation d’un spécialiste à un lieu
        view.addAffecterListener(e -> {
            String selectedSpec = view.getSelectedSpecialiste();
            String selectedLieu = view.getSelectedLieu();

            if (selectedSpec == null || selectedLieu == null ||
                    !selectedSpec.contains("ID:") || !selectedLieu.contains("ID:")) {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner un spécialiste et un lieu.");
                return;
            }

            int idSpec = extractIdFromCombo(selectedSpec);
            int idLieu = extractIdFromCombo(selectedLieu);

            if (slDAO.create(idSpec, idLieu)) {
                loadAffectations();
                JOptionPane.showMessageDialog(view, "✅ Affectation ajoutée !");
            } else {
                JOptionPane.showMessageDialog(view, "❌ Erreur : affectation existante ou problème SQL.");
            }
        });

        // Suppression d’une affectation
        view.addSupprimerListener(e -> {
            String[] selected = view.getSelectedAffectation();
            if (selected == null) {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner une affectation à supprimer.");
                return;
            }

            int idSpec = Integer.parseInt(selected[0]);
            int idLieu = Integer.parseInt(selected[1]);

            int confirm = JOptionPane.showConfirmDialog(view, "Confirmer la suppression ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (slDAO.delete(idSpec, idLieu)) {
                    loadAffectations();
                    JOptionPane.showMessageDialog(view, "🗑️ Affectation supprimée.");
                } else {
                    JOptionPane.showMessageDialog(view, "❌ Erreur lors de la suppression.");
                }
            }
        });

        // Retour à l’administration
        view.addRetourListener(e -> {
            view.dispose();
            new AdministrationController();
        });

        view.setVisible(true);
    }

    /**
     * Charge les affectations actuelles depuis la base et les affiche dans un tableau.
     */
    private void loadAffectations() {
        List<String[]> data = slDAO.findAll();
        String[] columns = {"ID Spécialiste", "Nom Spécialiste", "ID Lieu", "Lieu"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (String[] row : data) {
            model.addRow(row);
        }
        view.setTableModel(model);
    }

    /**
     * Extrait l'identifiant numérique depuis une chaîne contenant "(ID:x)".
     *
     * @param str Chaîne formatée comme "Nom Prénom (ID:3)"
     * @return Identifiant entier extrait.
     */
    private int extractIdFromCombo(String str) {
        return Integer.parseInt(str.substring(str.indexOf("ID:") + 3, str.indexOf(")")));
    }
}
