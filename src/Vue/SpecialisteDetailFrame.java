package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SpecialisteDetailFrame extends JFrame {
    private JLabel profileLabel;
    private JLabel nameLabel;
    private JLabel specialisationLabel;
    private JLabel disponibilitesLabel;
    private JLabel tarifsLabel;
    private JLabel publicReceuLabel;
    private JButton prendreRendezVousButton;

    public SpecialisteDetailFrame() {
        setTitle("Détails du Spécialiste");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Utilisation de GridBagLayout pour une disposition flexible
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // En-tête avec photo et informations
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(52, 152, 219));

        ImageIcon profileIcon = new ImageIcon("path_to_profile_image.jpg"); // Remplacez par le chemin de l'image
        profileLabel = new JLabel(profileIcon);
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);

        headerPanel.add(profileLabel);
        headerPanel.add(nameLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(headerPanel, gbc);

        // Section des informations
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 1, 5, 15));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Informations"));

        specialisationLabel = new JLabel();
        disponibilitesLabel = new JLabel();
        tarifsLabel = new JLabel();
        publicReceuLabel = new JLabel();

        infoPanel.add(specialisationLabel);
        infoPanel.add(disponibilitesLabel);
        infoPanel.add(tarifsLabel);
        infoPanel.add(publicReceuLabel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(infoPanel, gbc);

        // Section des tarifs et paiements
        JPanel tarifsPanel = new JPanel();
        tarifsPanel.setLayout(new BoxLayout(tarifsPanel, BoxLayout.Y_AXIS));
        tarifsPanel.setBorder(BorderFactory.createTitledBorder("Tarifs et Paiements"));

        JLabel tarifsDetailsLabel = new JLabel("Détails des tarifs et paiements...");
        tarifsPanel.add(tarifsDetailsLabel);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(tarifsPanel, gbc);

        // Bouton pour prendre rendez-vous
        prendreRendezVousButton = new JButton("Prendre Rendez-vous");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(prendreRendezVousButton, gbc);
    }

    public void setDetails(String nom, String specialisation, String disponibilites, String tarifs, String publicReceu) {
        nameLabel.setText(nom);
        specialisationLabel.setText("Spécialisation: " + specialisation);
        disponibilitesLabel.setText("Disponibilités: " + disponibilites);
        tarifsLabel.setText("Tarifs: " + tarifs);
        publicReceuLabel.setText("Public reçu: " + publicReceu);
    }

    public void addPrendreRendezVousListener(ActionListener listener) {
        prendreRendezVousButton.addActionListener(listener);
    }
}
