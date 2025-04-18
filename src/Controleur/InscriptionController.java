package Controleur;

import dao.PatientDAO;
import Model.Patient;
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
                view.setStatus("Inscription réussie !");
            } else {
                view.setStatus("Erreur lors de l'inscription.");
            }
        }
    }
}
