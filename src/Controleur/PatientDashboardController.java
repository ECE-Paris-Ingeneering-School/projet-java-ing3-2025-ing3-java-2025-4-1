package Controleur;

import Model.Lieu;
import Model.Specialiste;
import dao.LieuDAO;
import dao.RendezVousDAO;
import Model.Patient;
import Model.RendezVous;
import Vue.PatientDashboardView;
import dao.SpecialisteDAO;

import java.util.List;

public class PatientDashboardController {
    private PatientDashboardView view;
    private RendezVousDAO rendezVousDAO;
    private Patient patient;

    public PatientDashboardController(Patient patient) {
        this.patient = patient;
        this.view = new PatientDashboardView(patient.getPrenom());
        this.rendezVousDAO = new RendezVousDAO();

        loadRendezVous();
        addActions();

        view.setVisible(true);
    }

    private void loadRendezVous() {
        List<RendezVous> rdvs = rendezVousDAO.findByPatientId(patient.getId());
        StringBuilder builder = new StringBuilder();

        SpecialisteDAO specialisteDAO = new SpecialisteDAO();
        LieuDAO lieuDAO = new LieuDAO();

        if (rdvs.isEmpty()) {
            builder.append("Aucun rendez-vous trouvé.");
        } else {
            for (RendezVous rdv : rdvs) {
                String date = rdv.getDateHeure().toString();
                String specialisteNom = "Inconnu";
                String lieuNom = "Inconnu";

                Specialiste s = specialisteDAO.findById(rdv.getIdSpecialiste());
                if (s != null) {
                    specialisteNom = s.getPrenom() + " " + s.getNom();
                }

                Lieu l = lieuDAO.findById(rdv.getIdLieu());
                if (l != null) {
                    lieuNom = l.getNomEtablissement() + " - " + l.getVille();
                }

                builder.append("📅 ").append(date)
                        .append(" – 👨‍⚕️ ").append(specialisteNom)
                        .append(" – 🏥 ").append(lieuNom)
                        .append("\n");
            }
        }

        view.afficherRendezVous(builder.toString());
    }


    private void addActions() {
        view.addLogoutListener(e -> {
            view.dispose(); // ferme le dashboard
            // Tu peux relancer la ConnexionView ici si tu veux
        });
    }
}
