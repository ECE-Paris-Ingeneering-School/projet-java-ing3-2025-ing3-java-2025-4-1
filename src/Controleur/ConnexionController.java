package Controleur;

import dao.PatientDAO;
import dao.AdministrateurDAO;
import Model.Patient;
import Model.Administrateur;
import Vue.ConnexionView;
import Vue.InscriptionView;
import Vue.AdministrationFrame;

public class ConnexionController {
    private ConnexionView view;
    private PatientDAO patientDAO;
    private AdministrateurDAO administrateurDAO;

    public ConnexionController(ConnexionView view, PatientDAO patientDAO, AdministrateurDAO administrateurDAO) {
        this.view = view;
        this.patientDAO = patientDAO;
        this.administrateurDAO = administrateurDAO;

        this.view.addLoginListener(e -> {
            String email = view.getEmail();
            String password = view.getPassword();

            // Vérifier si c'est un administrateur
            Administrateur admin = administrateurDAO.findByEmailAndPassword(email, password);
            if (admin != null) {
                view.dispose();
                new AdministrationController(); // ← ça lance la frame + contrôleur
                return;
            }


            // Sinon, essayer comme patient
            Patient patient = patientDAO.findByEmailAndPassword(email, password);
            if (patient != null) {
                view.dispose();
                new PatientHomeController(patient);
            } else {
                view.setStatus("Email ou mot de passe incorrect.");
            }
        });

        this.view.addInscriptionListener(e -> {
            view.dispose();
            InscriptionView inscriptionView = new InscriptionView();
            new InscriptionController(inscriptionView, patientDAO);
            inscriptionView.setVisible(true);
        });
    }
}
