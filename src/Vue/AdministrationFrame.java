package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vue principale de l'espace administrateur.
 * Permet de naviguer vers :
 * - La gestion des spécialistes
 * - La gestion des lieux
 * - La consultation/modification des rendez-vous
 * - Les statistiques
 * - La gestion des affectations spécialistes-lieux
 * - La déconnexion
 *
 * Cette classe respecte l'architecture MVC en étant indépendante de la logique métier.
 * Les événements sont capturés via des ActionListener passés par les contrôleurs.
 *
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
     * Construit l'interface administrateur avec tous les boutons de navigation.
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
     * Met à jour le label de bienvenue avec le prénom et nom de l'utilisateur.
     *
     * @param nom Nom de l'administrateur.
     * @param prenom Prénom de l'administrateur.
     */
    public void setNomPrenom(String nom, String prenom) {
        bienvenueLabel.setText("Bienvenue " + prenom + " " + nom);
    }

    /**
     * Ajoute un listener pour le bouton "Gérer les spécialistes".
     *
     * @param l Listener à ajouter.
     */
    public void addSpecialistesListener(ActionListener l) {
        specialistesButton.addActionListener(l);
    }

    /**
     * Ajoute un listener pour le bouton "Gérer les lieux".
     *
     * @param l Listener à ajouter.
     */
    public void addLieuxListener(ActionListener l) {
        lieuxButton.addActionListener(l);
    }

    /**
     * Ajoute un listener pour le bouton "Consulter les rendez-vous".
     *
     * @param l Listener à ajouter.
     */
    public void addRendezVousListener(ActionListener l) {
        rdvButton.addActionListener(l);
    }

    /**
     * Ajoute un listener pour le bouton "Voir les statistiques".
     *
     * @param l Listener à ajouter.
     */
    public void addStatsListener(ActionListener l) {
        statsButton.addActionListener(l);
    }

    /**
     * Ajoute un listener pour le bouton "Gérer les affectations".
     *
     * @param l Listener à ajouter.
     */
    public void addSpecialistesLieuListener(ActionListener l) {
        affectationsButton.addActionListener(l);
    }

    /**
     * Ajoute un listener pour le bouton "Déconnexion".
     *
     * @param l Listener à ajouter.
     */
    public void addLogoutListener(ActionListener l) {
        logoutButton.addActionListener(l);
    }
}
