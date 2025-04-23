package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PatientDashboardView extends JFrame {
    private JTextArea rdvTextArea;
    private JButton logoutButton;
    private JButton prendreRdvButton;
    private JButton annulerRdvButton;
    private JComboBox<String> rdvSelector;

    public PatientDashboardView(String nomPatient) {
        setTitle("Bienvenue " + nomPatient);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setLayout(new BorderLayout(10, 10));

        JLabel titre = new JLabel("Mes Rendez-vous", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 22));
        mainPanel.add(titre, BorderLayout.NORTH);

        rdvTextArea = new JTextArea();
        rdvTextArea.setEditable(false);
        rdvTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(rdvTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        prendreRdvButton = new JButton("Prendre un RDV");
        logoutButton = new JButton("Se déconnecter");
        annulerRdvButton = new JButton("Annuler ce RDV");
        rdvSelector = new JComboBox<>();
        rdvSelector.setPreferredSize(new Dimension(300, 25));

        buttonPanel.add(prendreRdvButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(rdvSelector);
        buttonPanel.add(annulerRdvButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }

    public void afficherRendezVous(String texte) {
        rdvTextArea.setText(texte);
    }

    public void setRdvListe(String[] liste) {
        rdvSelector.setModel(new DefaultComboBoxModel<>(liste));
    }

    public String getRdvSelectionne() {
        return (String) rdvSelector.getSelectedItem();
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addPrendreRdvListener(ActionListener listener) {
        prendreRdvButton.addActionListener(listener);
    }

    public void addAnnulerRdvListener(ActionListener listener) {
        annulerRdvButton.addActionListener(listener);
    }
}
