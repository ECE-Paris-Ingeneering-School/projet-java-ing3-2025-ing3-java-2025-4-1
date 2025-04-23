
package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdministrationFrame extends JFrame {
    private JButton btnSpecialistes;
    private JButton btnLieux;
    private JButton btnRendezVous;
    private JButton btnStats;
    private JButton btnLogout;
    private JButton btnAffectations;


    public AdministrationFrame() {
        setTitle("Interface Administrateur");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        btnSpecialistes = new JButton("Gérer les spécialistes");
        btnLieux = new JButton("Gérer les lieux");
        btnRendezVous = new JButton("Consulter les rendez-vous");
        btnStats = new JButton("Voir les statistiques");
        btnLogout = new JButton("Déconnexion");
        btnAffectations = new JButton("Gérer les affectations");

        panel.add(btnSpecialistes);
        panel.add(btnLieux);
        panel.add(btnRendezVous);
        panel.add(btnStats);
        panel.add(btnAffectations);
        panel.add(btnLogout);

        add(panel);
    }

    public void addSpecialistesListener(ActionListener listener) {
        btnSpecialistes.addActionListener(listener);
    }

    public void addLieuxListener(ActionListener listener) {
        btnLieux.addActionListener(listener);
    }

    public void addRendezVousListener(ActionListener listener) {
        btnRendezVous.addActionListener(listener);
    }

    public void addStatsListener(ActionListener listener) {
        btnStats.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        btnLogout.addActionListener(listener);
    }

    public void addSpecialistesLieuListener(ActionListener listener) {
        btnAffectations.addActionListener(listener);
    }




}
