package Controleur;

import dao.RendezVousDAO;
import Model.Patient;
import Model.RendezVous;
import Vue.PatientDashboardView;

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

        if (rdvs.isEmpty()) {
            builder.append("Aucun rendez-vous trouvé.");
        } else {
            for (RendezVous rdv : rdvs) {
                builder.append("- Le ").append(rdv.getDateHeure())
                        .append(" avec Spécialiste ID ").append(rdv.getIdSpecialiste())
                        .append(" à Lieu ID ").append(rdv.getIdLieu()).append("\n");
                // Tu pourras afficher le nom du spécialiste ou lieu si tu veux plus tard
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
