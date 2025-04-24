package Controleur;

import Vue.HistoriqueView;
import Model.*;
import dao.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;

public class HistoriqueController {
    private HistoriqueView view;
    private Patient patient;
    private RendezVousDAO rendezVousDAO;
    private SpecialisteDAO specialisteDAO;
    private LieuDAO lieuDAO;

    private Map<String, RendezVous> mapRdvByLabel = new HashMap<>();

    public HistoriqueController(Patient patient) {
        this.patient = patient;
        this.rendezVousDAO = new RendezVousDAO();
        this.specialisteDAO = new SpecialisteDAO();
        this.lieuDAO = new LieuDAO();

        this.view = new HistoriqueView(patient.getPrenom());

        loadHistorique();

        view.addRdvSelectionListener(e -> {
            String label = view.getSelectedRdv();
            if (label == null) return;
            RendezVous rdv = mapRdvByLabel.get(label);
            view.setNoteText(rdv.getNote());
        });

        view.addSaveNoteListener(e -> {
            String label = view.getSelectedRdv();
            if (label == null) return;

            RendezVous rdv = mapRdvByLabel.get(label);
            String note = view.getNoteText();

            if (rendezVousDAO.updateNote(rdv.getId(), note)) {
                JOptionPane.showMessageDialog(view, "✅ Note enregistrée !");
            } else {
                JOptionPane.showMessageDialog(view, "❌ Erreur lors de l'enregistrement.");
            }
        });

        view.addRetourListener(e -> {
            view.dispose();
            new PatientHomeController(patient);
        });

        view.setVisible(true);
    }

    private void loadHistorique() {
        List<RendezVous> rdvs = rendezVousDAO.findByPatientId(patient.getId());
        List<String> labels = new ArrayList<>();
        mapRdvByLabel.clear();

        for (RendezVous rdv : rdvs) {
            if (rdv.getDateHeure().isBefore(LocalDateTime.now())) {
                Specialiste s = specialisteDAO.findById(rdv.getIdSpecialiste());
                Lieu l = lieuDAO.findById(rdv.getIdLieu());

                String label = "[" + rdv.getId() + "] " + rdv.getDateHeure().toLocalDate()
                        + " – " + (s != null ? s.getPrenom() + " " + s.getNom() : "Spécialiste inconnu")
                        + " @ " + (l != null ? l.getNomEtablissement() : "Lieu inconnu");

                labels.add(label);
                mapRdvByLabel.put(label, rdv);
            }
        }

        view.setRdvList(labels.toArray(new String[0]));
    }
}
