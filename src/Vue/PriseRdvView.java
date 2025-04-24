package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PriseRdvView extends JFrame {
    private JComboBox<String> specialisteCombo, lieuCombo;
    private JComboBox<String> jourCombo, moisCombo, anneeCombo, heureCombo, minuteCombo;
    private JButton validerButton, retourButton;
    private JLabel statusLabel;

    public PriseRdvView(String nomPatient) {
        setTitle("Prendre un rendez-vous – " + nomPatient);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Lieu et spécialiste
        specialisteCombo = new JComboBox<>();
        lieuCombo = new JComboBox<>();

        panel.add(labelWithField("🏥 Lieu :", lieuCombo));
        panel.add(labelWithField("👨‍⚕️ Spécialiste :", specialisteCombo));

        // Date
        String[] jours = new String[31];
        for (int i = 0; i < 31; i++) jours[i] = String.format("%02d", i + 1);

        String[] mois = {"01 - Jan", "02 - Fév", "03 - Mar", "04 - Avr", "05 - Mai", "06 - Juin",
                "07 - Juil", "08 - Août", "09 - Sep", "10 - Oct", "11 - Nov", "12 - Déc"};
        String[] annees = {"2024", "2025", "2026"};

        jourCombo = new JComboBox<>(jours);
        moisCombo = new JComboBox<>(mois);
        anneeCombo = new JComboBox<>(annees);

        String[] heures = new String[11];
        for (int i = 0; i < 11; i++) heures[i] = String.format("%02d", i + 8);
        String[] minutes = {"00", "15", "30", "45"};

        heureCombo = new JComboBox<>(heures);
        minuteCombo = new JComboBox<>(minutes);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(new JLabel("📅 Date :"));
        datePanel.add(jourCombo);
        datePanel.add(moisCombo);
        datePanel.add(anneeCombo);

        JPanel heurePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        heurePanel.add(new JLabel("⏰ Heure :"));
        heurePanel.add(heureCombo);
        heurePanel.add(new JLabel("h"));
        heurePanel.add(minuteCombo);

        panel.add(datePanel);
        panel.add(heurePanel);

        // Boutons
        validerButton = new JButton("✅ Valider le RDV");
        retourButton = new JButton("⬅️ Retour");
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel btns = new JPanel();
        btns.add(validerButton);
        btns.add(retourButton);

        panel.add(Box.createVerticalStrut(10));
        panel.add(btns);
        panel.add(statusLabel);

        add(panel);
    }

    private JPanel labelWithField(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    public void setSpecialistes(String[] items) {
        specialisteCombo.setModel(new DefaultComboBoxModel<>(items));
    }

    public void setLieux(String[] items) {
        lieuCombo.setModel(new DefaultComboBoxModel<>(items));
    }

    public String getSelectedSpecialiste() {
        return (String) specialisteCombo.getSelectedItem();
    }

    public String getSelectedLieu() {
        return (String) lieuCombo.getSelectedItem();
    }

    public String getSelectedDateTime() {
        String jour = (String) jourCombo.getSelectedItem();
        String mois = ((String) moisCombo.getSelectedItem()).substring(0, 2);
        String annee = (String) anneeCombo.getSelectedItem();
        String heure = (String) heureCombo.getSelectedItem();
        String minute = (String) minuteCombo.getSelectedItem();
        return annee + "-" + mois + "-" + jour + "T" + heure + ":" + minute;
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    public void addValiderListener(ActionListener listener) {
        validerButton.addActionListener(listener);
    }

    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }
}
