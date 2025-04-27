package Controleur;

import dao.PatientDAO;
import dao.AdministrateurDAO;
import Model.Patient;
import Model.Administrateur;
import Vue.ConnexionView;
import Vue.InscriptionView;
import Vue.AdministrationFrame;

/**
 * Contrôleur de l'écran de connexion.
 * Gère la vérification des identifiants et redirige les utilisateurs vers
 * leur interface respective selon leur rôle (patient ou administrateur).
 *
 * Ce contrôleur respecte le modèle MVC :
 * - Vue : {@link ConnexionView}
 * - Modèles : {@link Patient}, {@link Administrateur}
 * - Accès aux données via {@link PatientDAO} et {@link AdministrateurDAO}
 *
 */
public class ConnexionController {
    private ConnexionView view;
    private PatientDAO patientDAO;
    private AdministrateurDAO administrateurDAO;

    /**
     * Construit le contrôleur à partir de la vue de connexion et des DAO.
     * Initialise les listeners pour la connexion et la redirection vers l'inscription.
     *
     * @param view La vue de connexion à contrôler.
     * @param patientDAO DAO pour les patients.
     * @param administrateurDAO DAO pour les administrateurs.
     */
    public ConnexionController(ConnexionView view, PatientDAO patientDAO, AdministrateurDAO administrateurDAO) {
        this.view = view;
        this.patientDAO = patientDAO;
        this.administrateurDAO = administrateurDAO;

        // Action de connexion
        this.view.addLoginListener(e -> {
            String email = view.getEmail();
            String password = view.getPassword();

            // Vérification en tant qu'administrateur
            Administrateur admin = administrateurDAO.findByEmailAndPassword(email, password);
            if (admin != null) {
                view.dispose();
                new AdministrationController();
                return;
            }

            // Vérification en tant que patient
            Patient patient = patientDAO.findByEmailAndPassword(email, password);
            if (patient != null) {
                view.dispose();
                new PatientHomeController(patient);
            } else {
                view.setStatus("Email ou mot de passe incorrect.");
            }
        });

        // Redirection vers l'inscription
        this.view.addInscriptionListener(e -> {
            view.dispose();
            InscriptionView inscriptionView = new InscriptionView();
            new InscriptionController(inscriptionView, patientDAO);
            inscriptionView.setVisible(true);
        });
    }
}
