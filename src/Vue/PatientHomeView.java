package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vue graphique de l'accueil patient après connexion.
 * Permet d'accéder aux fonctionnalités principales :
 * - Prendre un rendez-vous
 * - Consulter/mettre à jour son profil
 * - Voir l'historique et noter des rendez-vous
 * - Se déconnecter
 *
 * Cette classe respecte le modèle MVC en séparant clairement l'affichage et les actions utilisateurs.
 *
 * Composants principaux : JButtons
 *
 */
public class PatientHomeView extends JFrame {
    private JButton rdvButton, profilButton, historiqueButton, logoutButton;

    /**
     * Construit la fenêtre d'accueil pour un patient donné.
     *
     * @param nom Le prénom du patient à afficher dans le titre de la fenêtre.
     */
    public PatientHomeView(String nom) {
        setTitle("Bienvenue " + nom);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        rdvButton = new JButton("Prendre un rendez-vous");
        profilButton = new JButton("Mon profil");
        historiqueButton = new JButton("Historique & Notes");
        logoutButton = new JButton("Se déconnecter");

        panel.add(rdvButton);
        panel.add(profilButton);
        panel.add(historiqueButton);
        panel.add(logoutButton);

        add(panel);
    }

    // Méthodes pour associer les boutons aux actions

    /**
     * Ajoute un listener pour accéder à la prise de rendez-vous.
     *
     * @param listener Le listener à associer.
     */
    public void addRdvListener(ActionListener listener) {
        rdvButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener pour accéder à la gestion du profil.
     *
     * @param listener Le listener à associer.
     */
    public void addProfilListener(ActionListener listener) {
        profilButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener pour accéder à l'historique des rendez-vous.
     *
     * @param listener Le listener à associer.
     */
    public void addHistoriqueListener(ActionListener listener) {
        historiqueButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener pour se déconnecter de l'application.
     *
     * @param listener Le listener à associer.
     */
    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
