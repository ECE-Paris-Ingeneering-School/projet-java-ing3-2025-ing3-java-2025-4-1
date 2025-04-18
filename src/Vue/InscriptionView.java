package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InscriptionView extends JFrame {
    private JTextField nomField, prenomField, emailField;
    private JPasswordField passwordField;
    private JButton inscriptionButton;
    private JLabel statusLabel;

    public InscriptionView() {
        setTitle("Inscription Patient");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        nomField = new JTextField();
        prenomField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        inscriptionButton = new JButton("S'inscrire");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        add(new JLabel("Nom :"));
        add(nomField);
        add(new JLabel("Prénom :"));
        add(prenomField);
        add(new JLabel("Email :"));
        add(emailField);
        add(new JLabel("Mot de passe :"));
        add(passwordField);
        add(new JLabel(""));
        add(inscriptionButton);
        add(new JLabel(""));
        add(statusLabel);
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
}
