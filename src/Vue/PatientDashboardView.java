package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

public class PatientDashboardView extends JFrame {
    private JTextArea rdvTextArea;
    private JButton logoutButton;
    private JButton annulerRdvButton;

    private JTabbedPane tabbedPane;
    private JPanel infosPanel;
    private JPanel rdvPanel;
    private JPanel priseRdvPanel;

    // Champs du profil
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton updateProfilButton;

    // Formulaire de prise de RDV
    private JComboBox<String> specialisteCombo;
    private JComboBox<String> lieuCombo;
    private JComboBox<String> jourCombo, moisCombo, anneeCombo;
    private JComboBox<String> heureCombo, minuteCombo;
    private JButton validerRdvButton;
    private JLabel statusRdvLabel;

    private List<String> rdvLignes = new ArrayList<>();
    private ActionListener lieuChangeListener;

    public PatientDashboardView(String nomPatient) {
        setTitle("Espace personnel - " + nomPatient);
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Bouton global de déconnexion (en haut à droite)
        JPanel topPanel = new JPanel(new BorderLayout());
        logoutButton = new JButton("Se déconnecter");
        topPanel.add(logoutButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();

        // Onglet Infos patient
        infosPanel = new JPanel();
        infosPanel.setLayout(new BoxLayout(infosPanel, BoxLayout.Y_AXIS));
        infosPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JLabel profilTitle = new JLabel("Mes informations personnelles", SwingConstants.CENTER);
        profilTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        profilTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosPanel.add(profilTitle);
        infosPanel.add(Box.createVerticalStrut(20));

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        updateProfilButton = new JButton("Mettre à jour mon profil");

        infosPanel.add(labelWithField("Nom :", nomField));
        infosPanel.add(labelWithField("Prénom :", prenomField));
        infosPanel.add(labelWithField("Email :", emailField));
        infosPanel.add(labelWithField("Mot de passe :", passwordField));
        infosPanel.add(Box.createVerticalStrut(20));
        infosPanel.add(updateProfilButton);

        tabbedPane.addTab("👤 Mon profil", infosPanel);

        // Onglet Mes rendez-vous
        rdvPanel = new JPanel(new BorderLayout(10, 10));
        JLabel titre = new JLabel("Mes Rendez-vous", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 22));
        annulerRdvButton = new JButton("❌ Annuler ce rendez-vous");
        annulerRdvButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        rdvTextArea = new JTextArea();
        rdvTextArea.setEditable(false);
        rdvTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        rdvTextArea.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JScrollPane scrollPane = new JScrollPane(rdvTextArea);

        JPanel rdvCenterPanel = new JPanel();
        rdvCenterPanel.setLayout(new BoxLayout(rdvCenterPanel, BoxLayout.Y_AXIS));
        rdvCenterPanel.add(scrollPane);
        rdvCenterPanel.add(Box.createVerticalStrut(10));
        rdvCenterPanel.add(annulerRdvButton);

        rdvPanel.add(titre, BorderLayout.NORTH);
        rdvPanel.add(rdvCenterPanel, BorderLayout.CENTER);

        tabbedPane.addTab("📅 Mes rendez-vous", rdvPanel);

        // Onglet prise de rendez-vous
        priseRdvPanel = new JPanel();
        priseRdvPanel.setLayout(new BoxLayout(priseRdvPanel, BoxLayout.Y_AXIS));
        priseRdvPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JLabel rdvTitle = new JLabel("Prendre un rendez-vous", SwingConstants.CENTER);
        rdvTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        rdvTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        priseRdvPanel.add(rdvTitle);
        priseRdvPanel.add(Box.createVerticalStrut(20));

        specialisteCombo = new JComboBox<>();
        lieuCombo = new JComboBox<>();
        lieuCombo.addActionListener(e -> {
            if (lieuChangeListener != null) {
                lieuChangeListener.actionPerformed(e);
            }
        });

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
        datePanel.add(new JLabel("Date :"));
        datePanel.add(jourCombo);
        datePanel.add(moisCombo);
        datePanel.add(anneeCombo);

        JPanel heurePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        heurePanel.add(new JLabel("Heure :"));
        heurePanel.add(heureCombo);
        heurePanel.add(new JLabel("h"));
        heurePanel.add(minuteCombo);
        heurePanel.add(new JLabel("min"));

        priseRdvPanel.add(labelWithField("Lieu :", lieuCombo));
        priseRdvPanel.add(labelWithField("Spécialiste :", specialisteCombo));
        priseRdvPanel.add(Box.createVerticalStrut(10));
        priseRdvPanel.add(datePanel);
        priseRdvPanel.add(heurePanel);
        priseRdvPanel.add(Box.createVerticalStrut(15));

        validerRdvButton = new JButton("Valider le rendez-vous");
        validerRdvButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusRdvLabel = new JLabel("", SwingConstants.CENTER);
        statusRdvLabel.setForeground(new Color(180, 0, 0));
        statusRdvLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        priseRdvPanel.add(validerRdvButton);
        priseRdvPanel.add(Box.createVerticalStrut(10));
        priseRdvPanel.add(statusRdvLabel);

        tabbedPane.addTab("➕ Prendre un RDV", priseRdvPanel);
        add(tabbedPane, BorderLayout.CENTER);

        rdvTextArea.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int offset = rdvTextArea.viewToModel2D(e.getPoint());
                try {
                    int rowStart = javax.swing.text.Utilities.getRowStart(rdvTextArea, offset);
                    int rowEnd = javax.swing.text.Utilities.getRowEnd(rdvTextArea, offset);
                    rdvTextArea.select(rowStart, rowEnd);
                    String ligneCliquee = rdvTextArea.getSelectedText();
                    rdvTextArea.putClientProperty("ligneSelectionnee", ligneCliquee);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private JPanel labelWithField(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(120, 25));
        panel.add(lbl, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    public void setLieuChangeListener(ActionListener listener) {
        this.lieuChangeListener = listener;
    }

    public void afficherRendezVous(String texte) {
        rdvTextArea.setText(texte);
        rdvLignes = List.of(texte.split("\n"));
    }

    public void addAnnulerRdvButtonListener(ActionListener listener) {
        annulerRdvButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addUpdateProfilListener(ActionListener listener) {
        updateProfilButton.addActionListener(listener);
    }

    public String getNom() { return nomField.getText(); }
    public String getPrenom() { return prenomField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getMotDePasse() { return new String(passwordField.getPassword()); }

    public void setNom(String nom) { nomField.setText(nom); }
    public void setPrenom(String prenom) { prenomField.setText(prenom); }
    public void setEmail(String email) { emailField.setText(email); }
    public void setMotDePasse(String mdp) { passwordField.setText(mdp); }

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

    public String getSelectedDateTime() {
        String jour = (String) jourCombo.getSelectedItem();
        String mois = ((String) moisCombo.getSelectedItem()).substring(0, 2);
        String annee = (String) anneeCombo.getSelectedItem();
        String heure = (String) heureCombo.getSelectedItem();
        String minute = (String) minuteCombo.getSelectedItem();
        return annee + "-" + mois + "-" + jour + "T" + heure + ":" + minute;
    }

    public void setStatusRdv(String message) {
        statusRdvLabel.setText(message);
    }

    public void addValiderRdvListener(ActionListener listener) {
        validerRdvButton.addActionListener(listener);
    }

    public void switchToTab(int index) {
        tabbedPane.setSelectedIndex(index);
    }

    public String getRdvTextSelection() {
        Object ligne = rdvTextArea.getClientProperty("ligneSelectionnee");
        return (ligne instanceof String) ? (String) ligne : null;
    }
}