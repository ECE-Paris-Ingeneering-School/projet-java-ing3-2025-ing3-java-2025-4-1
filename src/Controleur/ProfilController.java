package Controleur;

import Vue.ProfilView;
import Model.Patient;
import dao.PatientDAO;

public class ProfilController {
    private ProfilView view;
    private Patient patient;
    private PatientDAO patientDAO;

    public ProfilController(Patient patient) {
        this.patient = patient;
        this.patientDAO = new PatientDAO();

        this.view = new ProfilView(patient.getPrenom());

        // Pré-remplir les champs
        view.setNom(patient.getNom());
        view.setPrenom(patient.getPrenom());
        view.setEmail(patient.getEmail());
        view.setPassword(patient.getMotDePasse());

        // Listener pour maj du profil
        view.addUpdateListener(e -> {
            String nom = view.getNom();
            String prenom = view.getPrenom();
            String email = view.getEmail();
            String mdp = view.getPassword();

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
                view.setStatus("❌ Tous les champs sont obligatoires.");
                return;
            }

            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setEmail(email);
            patient.setMotDePasse(mdp);

            if (patientDAO.update(patient)) {
                view.setStatus("✅ Profil mis à jour !");
            } else {
                view.setStatus("❌ Erreur lors de la mise à jour.");
            }
        });

        // Bouton retour
        view.addRetourListener(e -> {
            view.dispose();
            new PatientHomeController(patient);
        });

        view.setVisible(true);
    }
}
