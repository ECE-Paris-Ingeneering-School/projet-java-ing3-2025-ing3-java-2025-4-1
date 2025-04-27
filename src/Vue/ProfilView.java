package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vue graphique pour consulter et modifier le profil d'un patient connecté.
 * Permet de mettre à jour :
 * - Nom
 * - Prénom
 * - Email
 * - Mot de passe
 *
 * Fonctionnalités principales :
 * - Mise à jour du profil
 * - Retour vers l'accueil patient
 *
 * Cette classe suit le modèle MVC : elle expose uniquement des getters/setters et des listeners d'action.
 *
 * Composants : JTextFields, JPasswordField, JButtons
 *
 */
public class ProfilView extends JFrame {
    private JTextField nomField, prenomField, emailField;
    private JPasswordField passwordField;
    private JButton updateButton, retourButton;
    private JLabel statusLabel;

    /**
     * Construit la fenêtre de profil pour un patient donné.
     *
     * @param nom Le prénom du patient affiché dans le titre.
     */
    public ProfilView(String nom) {
        setTitle("Mon profil – " + nom);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        panel.add(labelWithField("Nom :", nomField));
        panel.add(labelWithField("Prénom :", prenomField));
        panel.add(labelWithField("Email :", emailField));
        panel.add(labelWithField("Mot de passe :", passwordField));
        panel.add(Box.createVerticalStrut(15));

        updateButton = new JButton("Mettre à jour");
        retourButton = new JButton("Retour");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.add(updateButton);
        btnPanel.add(retourButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        panel.add(btnPanel);
        panel.add(statusLabel);

        add(panel);
    }

    /**
     * Crée un panel contenant un label et un champ de formulaire alignés horizontalement.
     *
     * @param label Le texte du label.
     * @param field Le champ de saisie associé.
     * @return Le panel combiné.
     */
    private JPanel labelWithField(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(100, 25));
        panel.add(lbl, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    // Méthodes pour mettre à jour les champs
    public void setNom(String nom) { nomField.setText(nom); }
    public void setPrenom(String prenom) { prenomField.setText(prenom); }
    public void setEmail(String email) { emailField.setText(email); }
    public void setPassword(String mdp) { passwordField.setText(mdp); }

    // Méthodes pour récupérer les valeurs saisies
    public String getNom() { return nomField.getText(); }
    public String getPrenom() { return prenomField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }

    // Méthodes pour ajouter des listeners sur les boutons

    /**
     * Ajoute un listener pour le bouton de mise à jour du profil.
     *
     * @param listener Le listener à associer.
     */
    public void addUpdateListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener pour le bouton de retour vers l'accueil.
     *
     * @param listener Le listener à associer.
     */
    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }

    /**
     * Définit un message de statut (erreur ou confirmation).
     *
     * @param msg Le message à afficher.
     */
    public void setStatus(String msg) {
        statusLabel.setText(msg);
    }
}
