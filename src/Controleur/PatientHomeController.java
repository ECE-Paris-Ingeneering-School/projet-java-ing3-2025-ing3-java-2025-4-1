package Controleur;

import Vue.PatientHomeView;
import Vue.ConnexionView;
import dao.AdministrateurDAO;
import dao.PatientDAO;
import Model.Patient;

public class PatientHomeController {
    private PatientHomeView view;
    private Patient patient;

    public PatientHomeController(Patient patient) {
        this.patient = patient;
        this.view = new PatientHomeView(patient.getPrenom());

        view.addRdvListener(e -> {
            view.dispose();
            new PriseRdvController(patient);
        });

        view.addProfilListener(e -> {
            view.dispose();
            new ProfilController(patient);
        });

        view.addHistoriqueListener(e -> {
            view.dispose();
            new HistoriqueController(patient);
        });

        view.addLogoutListener(e -> {
            view.dispose();
            ConnexionView loginView = new ConnexionView();
            new ConnexionController(loginView, new PatientDAO(), new AdministrateurDAO());
            loginView.setVisible(true);
        });

        view.setVisible(true);
    }
}
