
package Controleur;

import Vue.AdministrationFrame;
import Vue.ConnexionView;
import dao.PatientDAO;
import dao.AdministrateurDAO;

public class AdministrationController {
    private AdministrationFrame view;

    public AdministrationController() {
        this.view = new AdministrationFrame();



        view.addSpecialistesListener(e -> {
            view.dispose();
            new GestionSpecialistesController();
        });

        view.addLieuxListener(e -> {
            view.dispose();
            new GestionLieuxController();
        });

        view.addRendezVousListener(e -> {
            view.dispose();
            new ConsultationRendezVousController();
        });

        view.addSpecialistesLieuListener(e -> {
            view.dispose();
            new SpecialisteLieuController();
        });

        view.addStatsListener(e -> {
            view.dispose();
            new StatistiquesController();
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
