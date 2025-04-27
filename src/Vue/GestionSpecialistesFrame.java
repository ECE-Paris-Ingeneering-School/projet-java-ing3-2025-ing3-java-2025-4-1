package Vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vue graphique pour la gestion des spécialistes (ajouter, modifier, supprimer).
 * Cette interface est destinée aux administrateurs pour gérer les médecins et praticiens disponibles.
 *
 * Fonctionnalités principales :
 * - Affichage de la liste des spécialistes existants
 * - Ajout d'un nouveau spécialiste
 * - Modification des informations d'un spécialiste
 * - Suppression d'un spécialiste
 *
 * Cette classe respecte le modèle MVC en séparant strictement l'affichage de la logique métier.
 *
 * Composants : JTable, JTextFields, JButtons
 *
 */
public class GestionSpecialistesFrame extends JFrame {
    private JTable table;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField specialisationField;
    private JTextField qualificationField;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JButton retourButton;

    /**
     * Construit la fenêtre d'administration pour la gestion des spécialistes.
     */
    public GestionSpecialistesFrame() {
        setTitle("Gestion des spécialistes");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter / Modifier un spécialiste"));

        nomField = new JTextField();
        prenomField = new JTextField();
        specialisationField = new JTextField();
        qualificationField = new JTextField();
        ajouterButton = new JButton("Ajouter");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");
        retourButton = new JButton("Retour");

        formPanel.add(new JLabel("Nom :"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom :"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Spécialisation :"));
        formPanel.add(specialisationField);
        formPanel.add(new JLabel("Qualification :"));
        formPanel.add(qualificationField);
        formPanel.add(ajouterButton);
        formPanel.add(modifierButton);
        formPanel.add(new JLabel());
        formPanel.add(supprimerButton);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(retourButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.SOUTH);
    }

    /**
     * Met à jour le modèle de la JTable affichant les spécialistes.
     *
     * @param model Le modèle de données à afficher.
     */
    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    /**
     * Retourne l'identifiant du spécialiste sélectionné.
     *
     * @return L'identifiant ou -1 si aucune sélection.
     */
    public int getSelectedSpecialisteId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) table.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    /** @return Le nom saisi. */
    public String getNom() { return nomField.getText(); }

    /** @return Le prénom saisi. */
    public String getPrenom() { return prenomField.getText(); }

    /** @return La spécialisation saisie. */
    public String getSpecialisation() { return specialisationField.getText(); }

    /** @return La qualification saisie. */
    public String getQualification() { return qualificationField.getText(); }

    // Méthodes pour lier les actions aux boutons

    /**
     * Ajoute un listener au bouton "Ajouter".
     *
     * @param listener Le listener à associer.
     */
    public void addAjouterListener(ActionListener listener) {
        ajouterButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener au bouton "Modifier".
     *
     * @param listener Le listener à associer.
     */
    public void addModifierListener(ActionListener listener) {
        modifierButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener au bouton "Supprimer".
     *
     * @param listener Le listener à associer.
     */
    public void addSupprimerListener(ActionListener listener) {
        supprimerButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener au bouton "Retour".
     *
     * @param listener Le listener à associer.
     */
    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }

    /**
     * Vide tous les champs du formulaire.
     */
    public void clearFields() {
        nomField.setText("");
        prenomField.setText("");
        specialisationField.setText("");
        qualificationField.setText("");
    }
}
