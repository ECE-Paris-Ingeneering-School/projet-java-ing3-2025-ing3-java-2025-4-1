package Controleur;

import Vue.AdministrationFrame;
import Vue.ConnexionView;
import dao.PatientDAO;
import dao.AdministrateurDAO;

/**
 * Contrôleur de l'interface d'administration principale.
 * Gère la navigation vers les modules administrateur (lieux, spécialistes, RDV, statistiques...),
 * ainsi que la déconnexion.
 *
 * Ce contrôleur suit le pattern MVC :
 * - Vue : {@link AdministrationFrame}
 * - Modèles accédés via {@link PatientDAO} et {@link AdministrateurDAO} pour la déconnexion
 * - Actions de navigation redirigent vers d'autres contrôleurs (spécialistes, lieux, stats…)
 *
 */
public class AdministrationController {
    private AdministrationFrame view;

    /**
     * Initialise la vue d'administration et ses boutons d'action.
     * Chaque bouton déclenche la redirection vers un contrôleur spécifique
     * (gestion des spécialistes, lieux, rendez-vous, statistiques...).
     */
    public AdministrationController() {
        this.view = new AdministrationFrame();

        // Navigation vers la gestion des spécialistes
        view.addSpecialistesListener(e -> {
            view.dispose();
            new GestionSpecialistesController();
        });

        // Navigation vers la gestion des lieux
        view.addLieuxListener(e -> {
            view.dispose();
            new GestionLieuxController();
        });

        // Navigation vers la consultation des rendez-vous
        view.addRendezVousListener(e -> {
            view.dispose();
            new ConsultationRendezVousController();
        });

        // Navigation vers l'affectation spécialistes-lieux
        view.addSpecialistesLieuListener(e -> {
            view.dispose();
            new SpecialisteLieuController();
        });

        // Navigation vers le module statistiques
        view.addStatsListener(e -> {
            view.dispose();
            new StatistiquesController();
        });

        // Déconnexion : retour à l'écran de connexion
        view.addLogoutListener(e -> {
            view.dispose();
            ConnexionView login = new ConnexionView();
            new ConnexionController(login, new PatientDAO(), new AdministrateurDAO());
            login.setVisible(true);
        });

        view.setVisible(true);
    }
}