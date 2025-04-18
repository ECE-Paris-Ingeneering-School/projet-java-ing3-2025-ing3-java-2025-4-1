package Controleur;

import dao.*;
import Model.*;
import Vue.PatientDashboardView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDashboardController {
    private PatientDashboardView view;
    private RendezVousDAO rendezVousDAO;
    private SpecialisteDAO specialisteDAO;
    private LieuDAO lieuDAO;
    private Patient patient;

    public PatientDashboardController(Patient patient) {
        this.patient = patient;
        this.view = new PatientDashboardView(patient.getPrenom());

        this.rendezVousDAO = new RendezVousDAO();
        this.specialisteDAO = new SpecialisteDAO();
        this.lieuDAO = new LieuDAO();

        refresh();

        view.addLogoutListener(e -> view.dispose());

        view.addPrendreRdvListener(e -> {
            new PriseRendezVousController(patient, this);
        });

        view.addAnnulerRdvListener(e -> {
            String selection = view.getRdvSelectionne();
            if (selection == null) return;

            int id = Integer.parseInt(selection.substring(selection.indexOf("[") + 1, selection.indexOf("]")));

            int confirm = JOptionPane.showConfirmDialog(view, "Confirmer l'annulation ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (rendezVousDAO.delete(id)) {
                    JOptionPane.showMessageDialog(view, "✅ Rendez-vous annulé.");
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(view, "❌ Échec de l'annulation.");
                }
            }
        });

        view.setVisible(true);
    }

    public void refresh() {
        List<RendezVous> rdvs = rendezVousDAO.findByPatientId(patient.getId());
        StringBuilder builder = new StringBuilder();
        List<String> rdvComboListe = new ArrayList<>();

        if (rdvs.isEmpty()) {
            builder.append("Aucun rendez-vous trouvé.");
        } else {
            for (RendezVous rdv : rdvs) {
                String date = rdv.getDateHeure().toString();

                Specialiste s = specialisteDAO.findById(rdv.getIdSpecialiste());
                String specialisteNom = (s != null) ? s.getPrenom() + " " + s.getNom() : "Inconnu";

                Lieu l = lieuDAO.findById(rdv.getIdLieu());
                String lieuNom = (l != null) ? l.getNomEtablissement() + " - " + l.getVille() : "Inconnu";

                builder.append("📅 ").append(date)
                        .append(" – 👨‍⚕️ ").append(specialisteNom)
                        .append(" – 🏥 ").append(lieuNom)
                        .append("\n");

                String label = "[" + rdv.getId() + "] " + date;
                rdvComboListe.add(label);
            }
        }

        view.afficherRendezVous(builder.toString());
        view.setRdvListe(rdvComboListe.toArray(new String[0]));
    }
}