package Controleur;

import Vue.ProfilView;
import Model.Patient;
import dao.PatientDAO;

/**
 * Contrôleur de la vue profil patient.
 * Permet au patient de consulter et de modifier ses informations personnelles (nom, prénom, email, mot de passe).
 *
 * Vue : {@link ProfilView}
 * Modèle : {@link Patient}
 * DAO : {@link PatientDAO}
 *
 * Ce contrôleur suit le modèle MVC et assure la sauvegarde des modifications dans la base de données.
 *
 */
public class ProfilController {
    private ProfilView view;
    private Patient patient;
    private PatientDAO patientDAO;

    /**
     * Initialise la vue de profil patient, pré-remplit les champs et configure les listeners.
     *
     * @param patient Le patient connecté.
     */
    public ProfilController(Patient patient) {
        this.patient = patient;
        this.patientDAO = new PatientDAO();

        this.view = new ProfilView(patient.getPrenom());

        // Pré-remplir les champs avec les données actuelles du patient
        view.setNom(patient.getNom());
        view.setPrenom(patient.getPrenom());
        view.setEmail(patient.getEmail());
        view.setPassword(patient.getMotDePasse());

        // Listener pour la mise à jour du profil
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

        // Listener pour retourner à l'accueil patient
        view.addRetourListener(e -> {
            view.dispose();
            new PatientHomeController(patient);
        });

        view.setVisible(true);
    }
}
