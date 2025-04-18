package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InscriptionView extends JFrame {
    private JTextField nomField, prenomField, emailField;
    private JPasswordField passwordField;
    private JButton inscriptionButton;
    private JLabel statusLabel;
    private JButton retourConnexionButton;

    public InscriptionView() {
        setTitle("Inscription Patient");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Créer un compte");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        inscriptionButton = new JButton("S'inscrire");
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(new Color(180, 0, 0));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        retourConnexionButton = new JButton("↩️ Retour à la connexion");
        retourConnexionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(retourConnexionButton);


        mainPanel.add(titleLabel);
        mainPanel.add(labelWithField("Nom :", nomField));
        mainPanel.add(labelWithField("Prénom :", prenomField));
        mainPanel.add(labelWithField("Email :", emailField));
        mainPanel.add(labelWithField("Mot de passe :", passwordField));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(inscriptionButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(statusLabel);

        add(mainPanel);
    }

    private JPanel labelWithField(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(label, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    public String getNom() { return nomField.getText(); }
    public String getPrenom() { return prenomField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    public void addInscriptionListener(ActionListener listener) {
        inscriptionButton.addActionListener(listener);
    }

    public void addRetourConnexionListener(ActionListener listener) {
        retourConnexionButton.addActionListener(listener);
    }

}
