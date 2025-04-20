
package Controleur;

import Vue.AdministrationFrame;
import Vue.ConnexionView;
import dao.PatientDAO;
import dao.AdministrateurDAO;

public class AdministrationController {
    private AdministrationFrame view;

    public AdministrationController() {
        this.view = new AdministrationFrame();

        // Action : Gérer les spécialistes
        view.addSpecialistesListener(e -> {
            new GestionSpecialistesController();
        });

        // Action : Gérer les lieux
        view.addLieuxListener(e -> {
            new GestionLieuxController();
        });

        // Action : Consulter les RDV
        view.addRendezVousListener(e -> {
            new ConsultationRendezVousController();
        });

        // Action : Statistiques
        view.addStatsListener(e -> {
                    new StatistiquesController();
                });

        // Action:
        view.addSpecialistesLieuListener(e -> {
            new SpecialisteLieuController();
        });

        // Action : Déconnexion
        view.addLogoutListener(e -> {
            view.dispose();
            ConnexionView login = new ConnexionView();
            new ConnexionController(login, new PatientDAO(), new AdministrateurDAO());
            login.setVisible(true);
        });




        view.setVisible(true);
    }
}
