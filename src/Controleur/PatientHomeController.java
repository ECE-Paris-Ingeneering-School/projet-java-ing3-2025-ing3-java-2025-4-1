package Controleur;

import Vue.PatientHomeView;
import Vue.ConnexionView;
import dao.AdministrateurDAO;
import dao.PatientDAO;
import Model.Patient;

/**
 * Contrôleur de l'accueil patient.
 * Gère la navigation principale pour un patient connecté :
 * - Prise de rendez-vous
 * - Consultation du profil
 * - Historique des rendez-vous
 * - Déconnexion
 *
 * Vue : {@link PatientHomeView}
 * Modèle : {@link Patient}
 *
 * Ce contrôleur permet une navigation fluide et centralisée dans l'espace personnel du patient.
 *
 */
public class PatientHomeController {
    private PatientHomeView view;
    private Patient patient;

    /**
     * Construit le contrôleur avec le patient connecté et initialise la vue.
     *
     * @param patient L'utilisateur actuellement connecté.
     */
    public PatientHomeController(Patient patient) {
        this.patient = patient;
        this.view = new PatientHomeView(patient.getPrenom());

        // Accès à la prise de RDV
        view.addRdvListener(e -> {
            view.dispose();
            new PriseRdvController(patient);
        });

        // Accès au profil
        view.addProfilListener(e -> {
            view.dispose();
            new ProfilController(patient);
        });

        // Accès à l'historique
        view.addHistoriqueListener(e -> {
            view.dispose();
            new HistoriqueController(patient);
        });

        // Déconnexion → retour à l'écran de connexion
        view.addLogoutListener(e -> {
            view.dispose();
            ConnexionView loginView = new ConnexionView();
            new ConnexionController(loginView, new PatientDAO(), new AdministrateurDAO());
            loginView.setVisible(true);
        });

        view.setVisible(true);
    }
}
