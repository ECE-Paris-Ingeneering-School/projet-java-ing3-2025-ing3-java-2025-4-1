package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConnexionView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton inscriptionButton;
    private JLabel statusLabel;

    public ConnexionView() {
        setTitle("Connexion Patient");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        emailField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Se connecter");
        inscriptionButton = new JButton("Créer un compte");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        add(new JLabel("Email :"));
        add(emailField);
        add(new JLabel("Mot de passe :"));
        add(passwordField);
        add(loginButton);
        add(inscriptionButton);
        add(statusLabel);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addInscriptionListener(ActionListener listener) {
        inscriptionButton.addActionListener(listener);
    }
}
