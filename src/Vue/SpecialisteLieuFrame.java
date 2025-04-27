package Vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vue graphique pour gérer l'affectation des spécialistes aux lieux d'exercice.
 * Permet :
 * - D'affecter un spécialiste à un lieu
 * - De supprimer une affectation existante
 * - De consulter toutes les affectations
 *
 * Cette classe suit strictement le modèle MVC : elle n'implémente aucune logique métier.
 *
 * Composants : JComboBox, JTable, JButton
 *
 */
public class SpecialisteLieuFrame extends JFrame {
    private JComboBox<String> specialisteCombo;
    private JComboBox<String> lieuCombo;
    private JButton affecterButton;
    private JButton supprimerButton;
    private JButton retourButton;
    private JTable table;

    /**
     * Construit la fenêtre d'affectation spécialistes ↔ lieux.
     */
    public SpecialisteLieuFrame() {
        setTitle("Affectation Spécialiste ↔ Lieu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        specialisteCombo = new JComboBox<>();
        lieuCombo = new JComboBox<>();
        affecterButton = new JButton("Affecter");
        supprimerButton = new JButton("Supprimer l'affectation");
        retourButton = new JButton("Retour");

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftTopPanel.add(retourButton);
        topPanel.add(leftTopPanel, BorderLayout.WEST);

        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new GridBagLayout());
        selectionPanel.setBorder(BorderFactory.createTitledBorder("Nouvelle affectation"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        selectionPanel.add(new JLabel("Spécialiste :"), gbc);
        gbc.gridx = 1;
        selectionPanel.add(specialisteCombo, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        selectionPanel.add(new JLabel("Lieu :"), gbc);
        gbc.gridx = 1;
        selectionPanel.add(lieuCombo, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(affecterButton);
        buttonPanel.add(supprimerButton);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Affectations existantes"));

        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(selectionPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Méthodes pour configurer l'affichage

    /**
     * Définit les spécialistes disponibles dans le comboBox.
     *
     * @param noms Liste des spécialistes.
     */
    public void setSpecialistes(String[] noms) {
        specialisteCombo.setModel(new DefaultComboBoxModel<>(noms));
    }

    /**
     * Définit les lieux disponibles dans le comboBox.
     *
     * @param noms Liste des lieux.
     */
    public void setLieux(String[] noms) {
        lieuCombo.setModel(new DefaultComboBoxModel<>(noms));
    }

    // Méthodes pour récupérer les sélections utilisateur
    public String getSelectedSpecialiste() {
        return (String) specialisteCombo.getSelectedItem();
    }
    public String getSelectedLieu() {
        return (String) lieuCombo.getSelectedItem();
    }

    /**
     * Récupère l'affectation sélectionnée dans la table (ID spécialiste, ID lieu).
     *
     * @return Tableau [idSpecialiste, idLieu] ou null si aucune sélection.
     */
    public String[] getSelectedAffectation() {
        int row = getSelectedRow();
        if (row != -1) {
            String idSpec = table.getValueAt(row, 0).toString();
            String idLieu = table.getValueAt(row, 2).toString();
            return new String[]{idSpec, idLieu};
        }
        return null;
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    /**
     * Met à jour le modèle de la table affichant les affectations existantes.
     *
     * @param model Le modèle de données.
     */
    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    // Méthodes pour ajouter des listeners aux boutons

    /**
     * Ajoute un listener pour le bouton "Affecter".
     *
     * @param listener Le listener.
     */
    public void addAffecterListener(ActionListener listener) {
        affecterButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener pour le bouton "Supprimer l'affectation".
     *
     * @param listener Le listener.
     */
    public void addSupprimerListener(ActionListener listener) {
        supprimerButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener pour le bouton "Retour".
     *
     * @param listener Le listener.
     */
    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }
}
