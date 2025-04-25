
package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Interface graphique principale pour l'administrateur.
 * Permet d'accéder aux différentes fonctionnalités de gestion : spécialistes, lieux, rendez-vous, statistiques et affectations.
 */
public class AdministrationFrame extends JFrame {
    private JButton specialistesButton;
    private JButton lieuxButton;
    private JButton rdvButton;
    private JButton statsButton;
    private JButton affectationsButton;
    private JButton logoutButton;
    private JLabel bienvenueLabel;

    /**
     * Constructeur de la fenêtre d'administration.
     */
    public AdministrationFrame() {
        setTitle("Interface Administrateur");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        bienvenueLabel = new JLabel("Bienvenue Administrateur", SwingConstants.CENTER);
        bienvenueLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        bienvenueLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        specialistesButton = new JButton("Gérer les spécialistes");
        lieuxButton = new JButton("Gérer les lieux");
        rdvButton = new JButton("Consulter les rendez-vous");
        statsButton = new JButton("Voir les statistiques");
        affectationsButton = new JButton("Gérer les affectations");
        logoutButton = new JButton("Déconnexion");

        JPanel gridPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));
        gridPanel.add(specialistesButton);
        gridPanel.add(lieuxButton);
        gridPanel.add(rdvButton);
        gridPanel.add(statsButton);
        gridPanel.add(affectationsButton);
        gridPanel.add(logoutButton);

        setLayout(new BorderLayout(10, 10));
        add(bienvenueLabel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
    }

    /**
     * Permet de changer dynamiquement le texte de bienvenue.
     * @param nom Nom de l'administrateur
     * @param prenom Prénom de l'administrateur
     */
    public void setNomPrenom(String nom, String prenom) {
        bienvenueLabel.setText("Bienvenue " + prenom + " " + nom);
    }

    public void addSpecialistesListener(ActionListener l) {
        specialistesButton.addActionListener(l);
    }

    public void addLieuxListener(ActionListener l) {
        lieuxButton.addActionListener(l);
    }

    public void addRendezVousListener(ActionListener l) {
        rdvButton.addActionListener(l);
    }

    public void addStatsListener(ActionListener l) {
        statsButton.addActionListener(l);
    }

    public void addSpecialistesLieuListener(ActionListener l) {
        affectationsButton.addActionListener(l);
    }

    public void addLogoutListener(ActionListener l) {
        logoutButton.addActionListener(l);
    }
}
