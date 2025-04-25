package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PriseRdvView extends JFrame {
    private JComboBox<String> specialisteCombo, lieuCombo;
    private JComboBox<String> specialisationCombo, qualificationCombo;
    private JComboBox<String> jourCombo, moisCombo, anneeCombo;
    private JComboBox<String> creneauxCombo;
    private JButton validerButton, retourButton;
    private JLabel statusLabel;

    public PriseRdvView(String nomPatient) {
        setTitle("Prendre un rendez-vous – " + nomPatient);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        lieuCombo = new JComboBox<>();
        specialisationCombo = new JComboBox<>();
        qualificationCombo = new JComboBox<>();
        specialisteCombo = new JComboBox<>();
        creneauxCombo = new JComboBox<>();

        panel.add(labelWithField("Lieu :", lieuCombo));
        panel.add(labelWithField("Spécialisation :", specialisationCombo));
        panel.add(labelWithField("Qualification :", qualificationCombo));
        panel.add(labelWithField("Spécialiste :", specialisteCombo));

        String[] jours = new String[31];
        for (int i = 0; i < 31; i++) jours[i] = String.format("%02d", i + 1);
        String[] mois = {"01 - Jan", "02 - Fév", "03 - Mar", "04 - Avr", "05 - Mai", "06 - Juin",
                "07 - Juil", "08 - Août", "09 - Sep", "10 - Oct", "11 - Nov", "12 - Déc"};
        String[] annees = {"2024", "2025", "2026"};

        jourCombo = new JComboBox<>(jours);
        moisCombo = new JComboBox<>(mois);
        anneeCombo = new JComboBox<>(annees);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(new JLabel("Date :"));
        datePanel.add(jourCombo);
        datePanel.add(moisCombo);
        datePanel.add(anneeCombo);

        panel.add(datePanel);
        panel.add(labelWithField("Créneau disponible :", creneauxCombo));

        validerButton = new JButton("Valider le RDV");
        retourButton = new JButton("Retour");
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

    public void setSpecialisations(String[] items) {
        specialisationCombo.setModel(new DefaultComboBoxModel<>(items));
    }

    public void setQualifications(String[] items) {
        qualificationCombo.setModel(new DefaultComboBoxModel<>(items));
    }

    public void setCreneaux(String[] items) {
        creneauxCombo.setModel(new DefaultComboBoxModel<>(items));
    }

    public String getSelectedSpecialiste() {
        return (String) specialisteCombo.getSelectedItem();
    }

    public String getSelectedLieu() {
        return (String) lieuCombo.getSelectedItem();
    }

    public String getSelectedSpecialisation() {
        return (String) specialisationCombo.getSelectedItem();
    }

    public String getSelectedQualification() {
        return (String) qualificationCombo.getSelectedItem();
    }

    public String getSelectedDate() {
        String jour = (String) jourCombo.getSelectedItem();
        String mois = ((String) moisCombo.getSelectedItem()).substring(0, 2);
        String annee = (String) anneeCombo.getSelectedItem();
        return annee + "-" + mois + "-" + jour;
    }

    public String getSelectedCreneau() {
        return (String) creneauxCombo.getSelectedItem();
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    public void setLieuChangeListener(ActionListener listener) {
        lieuCombo.addActionListener(listener);
    }

    public void setSpecialisationChangeListener(ActionListener listener) {
        specialisationCombo.addActionListener(listener);
    }

    public void setQualificationChangeListener(ActionListener listener) {
        qualificationCombo.addActionListener(listener);
    }

    public void setDateChangeListener(ActionListener listener) {
        jourCombo.addActionListener(listener);
        moisCombo.addActionListener(listener);
        anneeCombo.addActionListener(listener);
    }

    public void setSpecialisteChangeListener(ActionListener listener) {
        specialisteCombo.addActionListener(listener);
    }

    public void addValiderListener(ActionListener listener) {
        validerButton.addActionListener(listener);
    }

    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }

    public void setSpecQualFilterListener(ActionListener listener) {
        specialisationCombo.addActionListener(listener);
        qualificationCombo.addActionListener(listener);
    }

    public String getSelectedJour() {
        return (String) jourCombo.getSelectedItem();
    }

    public String getSelectedMois() {
        return (String) moisCombo.getSelectedItem();
    }

    public String getSelectedAnnee() {
        return (String) anneeCombo.getSelectedItem();
    }
}
