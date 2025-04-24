package Controleur;

import Vue.ConnexionView;
import dao.PatientDAO;
import Model.Patient;
import dao.AdministrateurDAO;
import Model.Administrateur;
import Vue.InscriptionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InscriptionController {
    private InscriptionView view;
    private PatientDAO patientDAO;

    public InscriptionController(InscriptionView view, PatientDAO patientDAO) {
        this.view = view;
        this.patientDAO = patientDAO;

        this.view.addInscriptionListener(new InscriptionListener());

        view.addRetourConnexionListener(e -> {
            view.dispose(); // ferme la vue inscription
            ConnexionView connexionView = new ConnexionView();
            new ConnexionController(connexionView, patientDAO, new AdministrateurDAO());
            connexionView.setVisible(true);
        });
    }

    class InscriptionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = view.getNom();
            String prenom = view.getPrenom();
            String email = view.getEmail();
            String password = view.getPassword();

            if (patientDAO.findByEmailAndPassword(email, password) != null) {
                view.setStatus("Cet utilisateur existe déjà.");
                return;
            }

            Patient patient = new Patient(nom, prenom, email, password, "nouveau");

            if (patientDAO.create(patient)) {
                view.setStatus("✅ Inscription réussie !");
                view.dispose(); // on ferme la fenêtre d’inscription

                // On récupère le patient nouvellement créé (recherche par email + mdp)
                Patient newPatient = patientDAO.findByEmailAndPassword(email, password);
                new PatientHomeController(newPatient);
            } else {
                view.setStatus("Erreur lors de l'inscription.");
            }
        }
    }
}
