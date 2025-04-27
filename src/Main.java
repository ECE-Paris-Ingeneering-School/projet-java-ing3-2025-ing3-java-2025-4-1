import Controleur.ConnexionController;
import dao.PatientDAO;
import Vue.ConnexionView;
import dao.AdministrateurDAO;


public class Main {
    public static void main(String[] args) {
        ConnexionView view = new ConnexionView();
        PatientDAO patientDAO = new PatientDAO();
        AdministrateurDAO adminDAO = new AdministrateurDAO();

        new ConnexionController(view, patientDAO, adminDAO);
        view.setVisible(true);

    }
}
