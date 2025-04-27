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

/**
 * Contrôleur de la vue d'administration permettant de consulter,
 * filtrer, modifier et supprimer les rendez-vous existants.
 *
 * Ce module est utilisé par les administrateurs pour la gestion globale
 * des rendez-vous enregistrés en base de données.
 *
 * Il permet aussi d'afficher dynamiquement les filtres par patient, spécialiste, lieu.
 *
 * Vue : {@link ConsultationRendezVousFrame}
 * DAO : {@link RendezVousDAO}, {@link PatientDAO}, {@link SpecialisteDAO}, {@link LieuDAO}
 *
 */
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

    /**
     * Initialise la vue de consultation des rendez-vous.
     * Charge les données, configure les filtres, écoute les événements d'action.
     */
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

        view.addDeleteListener(e -> handleDelete());

        view.addModifierListener(e -> handleModifier());

        view.addRetourListener(e -> {
            view.dispose();
            new AdministrationController();
        });

        view.setVisible(true);
    }

    /**
     * Charge tous les rendez-vous depuis la base et les affiche dans la table.
     */
    private void loadRendezVous() {
        allData = dao.findAllDetailed();

        String[] columns = {"ID", "Patient", "Spécialiste", "Lieu", "Date/Heure"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (String[] row : allData) {
            model.addRow(row);
        }

        view.setTableModel(model);
    }

    /**
     * Configure les filtres (comboBox) en fonction des données présentes.
     */
    private void setupFilters() {
        Set<String> patientSet = allData.stream().map(row -> row[1]).collect(Collectors.toSet());
        Set<String> specialisteSet = allData.stream().map(row -> row[2]).collect(Collectors.toSet());
        Set<String> lieuSet = allData.stream().map(row -> row[3]).collect(Collectors.toSet());

        view.setPatientOptions(mergeWithDefault(patientSet, "Tous les patients"));
        view.setSpecialisteOptions(mergeWithDefault(specialisteSet, "Tous les spécialistes"));
        view.setLieuOptions(mergeWithDefault(lieuSet, "Tous les lieux"));
    }

    /**
     * Ajoute un élément de filtre par défaut à une liste (ComboBox).
     *
     * @param set Ensemble des valeurs uniques.
     * @param defaultText Texte par défaut à insérer en premier.
     * @return Tableau avec l'option par défaut + les autres valeurs.
     */
    private String[] mergeWithDefault(Set<String> set, String defaultText) {
        String[] array = new String[set.size() + 1];
        array[0] = defaultText;
        int i = 1;
        for (String s : set) {
            array[i++] = s;
        }
        return array;
    }

    /**
     * Applique les filtres de recherche en fonction des sélections utilisateur.
     */
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

    /**
     * Gère la suppression d’un rendez-vous sélectionné.
     */
    private void handleDelete() {
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
    }

    /**
     * Gère la modification d’un rendez-vous existant.
     * Lit les nouveaux champs depuis la vue, reconstruit un objet {@link RendezVous},
     * et applique la mise à jour via DAO.
     */
    private void handleModifier() {
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

            Patient patient = patients.stream()
                    .filter(p -> (p.getPrenom() + " " + p.getNom()).equals(patientNom))
                    .findFirst().orElse(null);
            Specialiste specialiste = specialistes.stream()
                    .filter(s -> (s.getPrenom() + " " + s.getNom()).equals(specialisteNom))
                    .findFirst().orElse(null);
            Lieu lieu = lieux.stream()
                    .filter(l -> (l.getNomEtablissement() + " - " + l.getVille()).equals(lieuNom))
                    .findFirst().orElse(null);

            if (patient == null || specialiste == null || lieu == null) {
                JOptionPane.showMessageDialog(view, "Erreur dans les sélections.");
                return;
            }

            RendezVous rdv = new RendezVous(dateHeure, patient.getId(), specialiste.getId(), lieu.getId());
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
    }
}
