package Controleur;

import dao.PatientDAO;
import Model.Patient;
import Vue.ConnexionView;
import Vue.InscriptionView;

public class ConnexionController {
    private ConnexionView view;
    private PatientDAO patientDAO;

    public ConnexionController(ConnexionView view, PatientDAO patientDAO) {
        this.view = view;
        this.patientDAO = patientDAO;

        this.view.addLoginListener(e -> {
            String email = view.getEmail();
            String password = view.getPassword();

            Patient patient = patientDAO.findByEmailAndPassword(email, password);

            if (patient != null) {
                view.dispose(); // ferme la vue de connexion
                new PatientDashboardController(patient);
            } else {
                view.setStatus("Email ou mot de passe incorrect.");
            }
        });

        this.view.addInscriptionListener(e -> {
            view.dispose(); // Ferme la fenêtre de connexion
            InscriptionView inscriptionView = new InscriptionView();
            new InscriptionController(inscriptionView, patientDAO);
            inscriptionView.setVisible(true);
        });
    }
}
