import Controleur.ConnexionController;
import dao.PatientDAO;
import Vue.ConnexionView;

public class Main {
    public static void main(String[] args) {
        ConnexionView view = new ConnexionView();
        PatientDAO dao = new PatientDAO();
        new ConnexionController(view, dao);

        view.setVisible(true);
    }
}
