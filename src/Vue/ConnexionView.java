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
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel avec marges
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Connexion Patient");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Se connecter");
        inscriptionButton = new JButton("Créer un compte");
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(titleLabel);
        mainPanel.add(labelWithField("Email :", emailField));
        mainPanel.add(labelWithField("Mot de passe :", passwordField));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(loginButton);
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
