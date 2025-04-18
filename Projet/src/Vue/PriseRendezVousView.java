package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PriseRendezVousView extends JFrame {
    private JComboBox<String> specialisteCombo;
    private JComboBox<String> lieuCombo;
    private JTextField dateTimeField;
    private JButton validerButton;
    private JLabel statusLabel;

    public PriseRendezVousView() {
        setTitle("Prise de rendez-vous");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        specialisteCombo = new JComboBox<>();
        lieuCombo = new JComboBox<>();
        dateTimeField = new JTextField("2025-04-30 10:30");
        validerButton = new JButton("Valider le rendez-vous");
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(new Color(180, 0, 0));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(labelWithField("Spécialiste :", specialisteCombo));
        mainPanel.add(labelWithField("Lieu :", lieuCombo));
        mainPanel.add(labelWithField("Date et heure (YYYY-MM-DD HH:MM) :", dateTimeField));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(validerButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(statusLabel);

        add(mainPanel);
    }

    private JPanel labelWithField(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    public void setSpecialistes(String[] noms) {
        specialisteCombo.setModel(new DefaultComboBoxModel<>(noms));
    }

    public void setLieux(String[] noms) {
        lieuCombo.setModel(new DefaultComboBoxModel<>(noms));
    }

    public String getSelectedSpecialiste() {
        return (String) specialisteCombo.getSelectedItem();
    }

    public String getSelectedLieu() {
        return (String) lieuCombo.getSelectedItem();
    }

    public String getDateHeure() {
        return dateTimeField.getText();
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    public void addValiderListener(ActionListener listener) {
        validerButton.addActionListener(listener);
    }
}