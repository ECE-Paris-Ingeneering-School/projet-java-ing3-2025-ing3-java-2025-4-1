package Controleur;

import Vue.ConnexionView;
import dao.PatientDAO;
import Model.Patient;
import dao.AdministrateurDAO;
import Model.Administrateur;
import Vue.InscriptionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur de la vue d'inscription pour les nouveaux patients.
 * Gère l'enregistrement d'un nouvel utilisateur en base de données
 * et redirige automatiquement vers l'espace personnel du patient après succès.
 *
 * Vue : {@link InscriptionView}
 * DAO : {@link PatientDAO}
 *
 * Utilise également {@link ConnexionView} pour permettre un retour à la page de connexion.
 *
 */
public class InscriptionController {
    private InscriptionView view;
    private PatientDAO patientDAO;

    /**
     * Initialise le contrôleur d'inscription avec la vue et le DAO des patients.
     *
     * @param view La vue graphique pour l'inscription.
     * @param patientDAO DAO pour l'accès aux données patients.
     */
    public InscriptionController(InscriptionView view, PatientDAO patientDAO) {
        this.view = view;
        this.patientDAO = patientDAO;

        // Action : Inscription
        this.view.addInscriptionListener(new InscriptionListener());

        // Action : Retour à l'écran de connexion
        view.addRetourConnexionListener(e -> {
            view.dispose();
            ConnexionView connexionView = new ConnexionView();
            new ConnexionController(connexionView, patientDAO, new AdministrateurDAO());
            connexionView.setVisible(true);
        });
    }

    /**
     * Listener pour gérer l'action d'inscription lorsqu'un utilisateur clique sur le bouton.
     * Crée un nouvel objet {@link Patient}, vérifie l'existence, puis insère en base.
     */
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
                view.dispose();

                // Connexion automatique après inscription
                Patient newPatient = patientDAO.findByEmailAndPassword(email, password);
                new PatientHomeController(newPatient);
            } else {
                view.setStatus("Erreur lors de l'inscription.");
            }
        }
    }
}
