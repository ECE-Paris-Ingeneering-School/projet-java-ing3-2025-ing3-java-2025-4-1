package Vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministrationFrame extends JFrame {
    private JTextArea infoArea;

    public AdministrationFrame() {
        setTitle("Administration");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton manageSpecialistesButton = new JButton("Gérer les Spécialistes");
        JButton manageRendezVousButton = new JButton("Gérer les Rendez-vous");
        JButton viewReportsButton = new JButton("Voir les Rapports");
        infoArea = new JTextArea();

        panel.add(manageSpecialistesButton);
        panel.add(manageRendezVousButton);
        panel.add(viewReportsButton);
        panel.add(new JScrollPane(infoArea));

        manageSpecialistesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logique de gestion des spécialistes
            }
        });

        manageRendezVousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logique de gestion des rendez-vous
            }
        });

        viewReportsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logique d'affichage des rapports
            }
        });

        add(panel);
    }
}

