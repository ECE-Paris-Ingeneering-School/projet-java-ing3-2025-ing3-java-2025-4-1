
package Controleur;

import Vue.ConsultationRendezVousFrame;
import dao.RendezVousDAO;
import dao.SpecialisteDAO;
import dao.LieuDAO;
import dao.PatientDAO;
import Model.Specialiste;
import Model.Lieu;
import Model.Patient;
import Model.RendezVous;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsultationRendezVousController {
    private ConsultationRendezVousFrame view;
    private RendezVousDAO dao;

    private List<String[]> allData;
    private List<Patient> patients;
    private List<Specialiste> specialistes;
    private List<Lieu> lieux;

    private final PatientDAO patientDAO = new PatientDAO();
    private final SpecialisteDAO specialisteDAO = new SpecialisteDAO();
    private final LieuDAO lieuDAO = new LieuDAO();

    public ConsultationRendezVousController() {
        this.view = new ConsultationRendezVousFrame();
        this.dao = new RendezVousDAO();

        patients = patientDAO.findAll();
        specialistes = specialisteDAO.findAll();
        lieux = lieuDAO.findAll();

        loadRendezVous();
        setupFilters();

        view.addRefreshListener(e -> {
            loadRendezVous();
            setupFilters();
        });

        view.addFilterListener(e -> applyFilter());

        view.addDeleteListener(e -> {
            String idStr = view.getSelectedRendezVousId();
            if (idStr == null) {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner un rendez-vous à supprimer.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(view, "Confirmer la suppression de ce rendez-vous ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(idStr);
                if (dao.delete(id)) {
                    loadRendezVous();
                    setupFilters();
                    JOptionPane.showMessageDialog(view, "Rendez-vous supprimé.");
                } else {
                    JOptionPane.showMessageDialog(view, "Erreur lors de la suppression.");
                }
            }
        });

        view.addModifierListener(e -> {
            String idStr = view.getSelectedRendezVousId();
            if (idStr == null) {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner un rendez-vous à modifier.");
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                String patientNom = view.getSelectedPatient();
                String specialisteNom = view.getSelectedSpecialiste();
                String lieuNom = view.getSelectedLieu();
                LocalDateTime dateHeure = LocalDateTime.parse(view.getDateHeureField().replace(" ", "T"));

                Patient patient = patients.stream().filter(p -> (p.getPrenom() + " " + p.getNom()).equals(patientNom)).findFirst().orElse(null);
                Specialiste specialiste = specialistes.stream().filter(s -> (s.getPrenom() + " " + s.getNom()).equals(specialisteNom)).findFirst().orElse(null);
                Lieu lieu = lieux.stream().filter(l -> (l.getNomEtablissement() + " - " + l.getVille()).equals(lieuNom)).findFirst().orElse(null);

                if (patient == null || specialiste == null || lieu == null) {
                    JOptionPane.showMessageDialog(view, "Erreur dans les sélections.");
                    return;
                }

                RendezVous rdv = new RendezVous(id, dateHeure, patient.getId(), specialiste.getId(), lieu.getId());
                if (dao.update(rdv)) {
                    loadRendezVous();
                    setupFilters();
                    JOptionPane.showMessageDialog(view, "Rendez-vous modifié !");
                } else {
                    JOptionPane.showMessageDialog(view, "Erreur lors de la modification.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Format de date invalide (attendu : YYYY-MM-DD HH:MM)");
            }
        });

        view.addRetourListener(e -> {
            view.dispose();
            new AdministrationController();
        });


        view.setVisible(true);
    }

    private void loadRendezVous() {
        allData = dao.findAllDetailed();

        String[] columns = {"ID", "Patient", "Spécialiste", "Lieu", "Date/Heure"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (String[] row : allData) {
            model.addRow(row);
        }

        view.setTableModel(model);
    }

    private void setupFilters() {
        Set<String> patientSet = allData.stream().map(row -> row[1]).collect(Collectors.toSet());
        Set<String> specialisteSet = allData.stream().map(row -> row[2]).collect(Collectors.toSet());
        Set<String> lieuSet = allData.stream().map(row -> row[3]).collect(Collectors.toSet());

        view.setPatientOptions(mergeWithDefault(patientSet, "Tous les patients"));
        view.setSpecialisteOptions(mergeWithDefault(specialisteSet, "Tous les spécialistes"));
        view.setLieuOptions(mergeWithDefault(lieuSet, "Tous les lieux"));
    }

    private String[] mergeWithDefault(Set<String> set, String defaultText) {
        String[] array = new String[set.size() + 1];
        array[0] = defaultText;
        int i = 1;
        for (String s : set) {
            array[i++] = s;
        }
        return array;
    }

    private void applyFilter() {
        String selectedPatient = view.getSelectedPatient();
        String selectedSpec = view.getSelectedSpecialiste();
        String selectedLieu = view.getSelectedLieu();

        String[] columns = {"ID", "Patient", "Spécialiste", "Lieu", "Date/Heure"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (String[] row : allData) {
            boolean matchPatient = selectedPatient.equals("Tous les patients") || row[1].equals(selectedPatient);
            boolean matchSpec = selectedSpec.equals("Tous les spécialistes") || row[2].equals(selectedSpec);
            boolean matchLieu = selectedLieu.equals("Tous les lieux") || row[3].equals(selectedLieu);

            if (matchPatient && matchSpec && matchLieu) {
                model.addRow(row);
            }
        }

        view.setTableModel(model);
    }
}
