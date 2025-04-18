package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PatientDashboardView extends JFrame {
    private JTextArea rdvTextArea;
    private JButton logoutButton;

    public PatientDashboardView(String nomPatient) {
        setTitle("Bienvenue " + nomPatient);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        rdvTextArea = new JTextArea();
        rdvTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(rdvTextArea);

        logoutButton = new JButton("Se déconnecter");

        add(scroll, BorderLayout.CENTER);
        add(logoutButton, BorderLayout.SOUTH);
    }

    public void afficherRendezVous(String rdvInfos) {
        rdvTextArea.setText(rdvInfos);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
