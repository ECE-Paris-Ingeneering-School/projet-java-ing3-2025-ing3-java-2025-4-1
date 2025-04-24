package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PatientHomeView extends JFrame {
    private JButton rdvButton, profilButton, historiqueButton, logoutButton;

    public PatientHomeView(String nom) {
        setTitle("Bienvenue " + nom);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        rdvButton = new JButton("📅 Prendre un rendez-vous");
        profilButton = new JButton("🧍‍♂️ Mon profil");
        historiqueButton = new JButton("📝 Historique & Notes");
        logoutButton = new JButton("🚪 Se déconnecter");

        panel.add(rdvButton);
        panel.add(profilButton);
        panel.add(historiqueButton);
        panel.add(logoutButton);

        add(panel);
    }

    public void addRdvListener(ActionListener listener) {
        rdvButton.addActionListener(listener);
    }

    public void addProfilListener(ActionListener listener) {
        profilButton.addActionListener(listener);
    }

    public void addHistoriqueListener(ActionListener listener) {
        historiqueButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
