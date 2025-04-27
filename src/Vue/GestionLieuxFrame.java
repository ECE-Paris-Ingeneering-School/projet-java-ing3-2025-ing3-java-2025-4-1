package Vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vue graphique pour la gestion des lieux (ajouter, modifier, supprimer).
 * Accessible par les administrateurs pour maintenir la liste des établissements et lieux.
 *
 * Fonctionnalités principales :
 * - Affichage de la liste des lieux existants
 * - Ajout d'un nouveau lieu
 * - Modification d'un lieu existant
 * - Suppression d'un lieu
 *
 * Cette vue respecte le modèle MVC : elle expose uniquement des méthodes pour
 * mettre à jour l'affichage et lier des événements sans logique métier.
 *
 * Composants principaux : JTable, JTextFields, JButtons
 *
 */
public class GestionLieuxFrame extends JFrame {
    private JTable table;
    private JTextField nomField;
    private JTextField adresseField;
    private JTextField villeField;
    private JTextField codePostalField;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JButton retourButton;

    /**
     * Construit la fenêtre d'administration pour la gestion des lieux.
     */
    public GestionLieuxFrame() {
        setTitle("Gestion des lieux");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter / Modifier un lieu"));

        nomField = new JTextField();
        adresseField = new JTextField();
        villeField = new JTextField();
        codePostalField = new JTextField();
        ajouterButton = new JButton("Ajouter");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");
        retourButton = new JButton("Retour");

        formPanel.add(new JLabel("Nom de l'établissement :"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Adresse :"));
        formPanel.add(adresseField);
        formPanel.add(new JLabel("Ville :"));
        formPanel.add(villeField);
        formPanel.add(new JLabel("Code postal :"));
        formPanel.add(codePostalField);
        formPanel.add(ajouterButton);
        formPanel.add(modifierButton);
        formPanel.add(supprimerButton);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(retourButton);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
    }

    // Méthodes pour manipuler l'affichage et récupérer les données

    /**
     * Met à jour le modèle de la table affichant les lieux.
     *
     * @param model Le modèle de données à afficher.
     */
    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    /**
     * Retourne l'identifiant du lieu sélectionné dans la table.
     *
     * @return L'identifiant sélectionné ou -1 si aucune sélection.
     */
    public int getSelectedLieuId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) table.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    /** @return Le texte saisi dans le champ du nom. */
    public String getNom() { return nomField.getText(); }

    /** @return Le texte saisi dans le champ de l'adresse. */
    public String getAdresse() { return adresseField.getText(); }

    /** @return Le texte saisi dans le champ de la ville. */
    public String getVille() { return villeField.getText(); }

    /** @return Le texte saisi dans le champ du code postal. */
    public String getCodePostal() { return codePostalField.getText(); }

    /**
     * Vide tous les champs du formulaire.
     */
    public void clearFields() {
        nomField.setText("");
        adresseField.setText("");
        villeField.setText("");
        codePostalField.setText("");
    }

    // Méthodes pour lier les boutons aux actions

    /**
     * Ajoute un listener au bouton "Ajouter".
     *
     * @param l L'ActionListener à attacher.
     */
    public void addAjouterListener(ActionListener l) { ajouterButton.addActionListener(l); }

    /**
     * Ajoute un listener au bouton "Modifier".
     *
     * @param l L'ActionListener à attacher.
     */
    public void addModifierListener(ActionListener l) { modifierButton.addActionListener(l); }

    /**
     * Ajoute un listener au bouton "Supprimer".
     *
     * @param l L'ActionListener à attacher.
     */
    public void addSupprimerListener(ActionListener l) { supprimerButton.addActionListener(l); }

    /**
     * Ajoute un listener au bouton "Retour".
     *
     * @param l L'ActionListener à attacher.
     */
    public void addRetourListener(ActionListener l) { retourButton.addActionListener(l); }
}
