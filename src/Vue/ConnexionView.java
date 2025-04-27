package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vue de l'écran de connexion pour les utilisateurs (patients ou administrateurs).
 * Permet de se connecter ou d'accéder à la page d'inscription.
 *
 * Cette classe sépare bien l'interface graphique de la logique métier selon le pattern MVC.
 * Elle fournit des méthodes pour récupérer les données saisies et ajouter des listeners aux boutons.
 *
 * Composants :
 * - Champ email
 * - Champ mot de passe
 * - Bouton de connexion
 * - Bouton d'inscription
 * - Message d'erreur ou de statut
 *
 */
public class ConnexionView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton inscriptionButton;
    private JLabel statusLabel;

    /**
     * Initialise et construit l'interface de connexion.
     */
    public ConnexionView() {
        setTitle("Connexion");
        setSize(420, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        titlePanel.add(titleLabel);

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Se connecter");
        inscriptionButton = new JButton("Créer un compte");

        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        inscriptionButton.setBackground(new Color(230, 230, 230));
        inscriptionButton.setFocusPainted(false);
        inscriptionButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(titleLabel);
        mainPanel.add(labelWithField("Email :", emailField));
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(labelWithField("Mot de passe :", passwordField));
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(loginButton);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(inscriptionButton);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(statusLabel);

        add(mainPanel);
    }

    /**
     * Crée un panel pour un champ de formulaire avec son label associé.
     *
     * @param labelText Le texte du label.
     * @param field Le champ de saisie.
     * @return Le panel contenant le label et le champ.
     */
    private JPanel labelWithField(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(label, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Retourne l'email saisi par l'utilisateur.
     *
     * @return L'email.
     */
    public String getEmail() {
        return emailField.getText();
    }

    /**
     * Retourne le mot de passe saisi par l'utilisateur.
     *
     * @return Le mot de passe.
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Définit un message de statut ou d'erreur en bas de la vue.
     *
     * @param message Le message à afficher.
     */
    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    /**
     * Ajoute un listener pour le bouton "Se connecter".
     *
     * @param listener Le listener.
     */
    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener pour le bouton "Créer un compte".
     *
     * @param listener Le listener.
     */
    public void addInscriptionListener(ActionListener listener) {
        inscriptionButton.addActionListener(listener);
    }
}
