package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProfilView extends JFrame {
    private JTextField nomField, prenomField, emailField;
    private JPasswordField passwordField;
    private JButton updateButton, retourButton;
    private JLabel statusLabel;

    public ProfilView(String nom) {
        setTitle("🧍‍♂️ Mon profil – " + nom);
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

        updateButton = new JButton("💾 Mettre à jour");
        retourButton = new JButton("⬅️ Retour");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.add(updateButton);
        btnPanel.add(retourButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        panel.add(btnPanel);
        panel.add(statusLabel);

        add(panel);
    }

    private JPanel labelWithField(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(100, 25));
        panel.add(lbl, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    public void setNom(String nom) { nomField.setText(nom); }
    public void setPrenom(String prenom) { prenomField.setText(prenom); }
    public void setEmail(String email) { emailField.setText(email); }
    public void setPassword(String mdp) { passwordField.setText(mdp); }

    public String getNom() { return nomField.getText(); }
    public String getPrenom() { return prenomField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }

    public void addUpdateListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }

    public void setStatus(String msg) {
        statusLabel.setText(msg);
    }
}
