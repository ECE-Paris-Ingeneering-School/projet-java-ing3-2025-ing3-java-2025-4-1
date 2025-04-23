
package Controleur;

import Vue.GestionSpecialistesFrame;
import dao.SpecialisteDAO;
import Model.Specialiste;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;


public class GestionSpecialistesController {
    private GestionSpecialistesFrame view;
    private SpecialisteDAO dao;

    public GestionSpecialistesController() {
        this.view = new GestionSpecialistesFrame();
        this.dao = new SpecialisteDAO();

        loadSpecialistes();

        view.addAjouterListener(e -> {
            String nom = view.getNom();
            String prenom = view.getPrenom();
            String specialisation = view.getSpecialisation();
            String qualification = view.getQualification();

            if (nom.isEmpty() || prenom.isEmpty() || specialisation.isEmpty() || qualification.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Specialiste newS = new Specialiste(nom, prenom, specialisation, qualification);
            if (dao.create(newS)) {
                loadSpecialistes();
                view.clearFields();
                JOptionPane.showMessageDialog(view, "Spécialiste ajouté avec succès !");
            } else {
                JOptionPane.showMessageDialog(view, "Erreur lors de l'ajout.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.addModifierListener(e -> {
            int id = view.getSelectedSpecialisteId();
            if (id == -1) {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner un spécialiste à modifier.");
                return;
            }

            String nom = view.getNom();
            String prenom = view.getPrenom();
            String specialisation = view.getSpecialisation();
            String qualification = view.getQualification();

            if (nom.isEmpty() || prenom.isEmpty() || specialisation.isEmpty() || qualification.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Tous les champs doivent être remplis pour modifier.");
                return;
            }

            Specialiste s = new Specialiste(id, nom, prenom, specialisation, qualification);
            if (dao.update(s)) {
                loadSpecialistes();
                view.clearFields();
                JOptionPane.showMessageDialog(view, "Spécialiste modifié !");
            } else {
                JOptionPane.showMessageDialog(view, "Erreur lors de la modification.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.addSupprimerListener(e -> {
            int id = view.getSelectedSpecialisteId();
            if (id == -1) {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner un spécialiste à supprimer.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(view, "Confirmer la suppression ?", "Attention", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (dao.delete(id)) {
                    loadSpecialistes();
                    view.clearFields();
                    JOptionPane.showMessageDialog(view, "Spécialiste supprimé.");
                } else {
                    JOptionPane.showMessageDialog(view, "Erreur lors de la suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.addRetourListener(e -> {
            view.dispose();
            new AdministrationController();
        });



        view.setVisible(true);
    }

    private void loadSpecialistes() {
        List<Specialiste> list = dao.findAll();

        String[] columns = {"ID", "Nom", "Prénom", "Spécialisation", "Qualification"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Specialiste s : list) {
            Object[] row = {s.getId(), s.getNom(), s.getPrenom(), s.getSpecialisation(), s.getQualification()};
            model.addRow(row);
        }

        view.setTableModel(model);
    }
}
