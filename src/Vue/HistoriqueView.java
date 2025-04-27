package Vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vue graphique pour consulter l'historique des rendez-vous passés d'un patient.
 * Permet également de noter un rendez-vous après sa réalisation.
 *
 * Fonctionnalités principales :
 * - Affichage de la liste des rendez-vous passés
 * - Sélection d'un rendez-vous pour l'évaluer (note de 0 à 5)
 * - Retour vers l'accueil patient
 *
 * Cette classe suit le modèle MVC : elle n'implémente aucune logique métier.
 *
 * Composants : JTable, JButtons
 *
 */
public class HistoriqueView extends JFrame {
    private JTable table;
    private JButton noterButton;
    private JButton retourButton;

    /**
     * Construit la fenêtre d'historique pour un patient donné.
     *
     * @param nom Le prénom du patient affiché dans le titre de la fenêtre.
     */
    public HistoriqueView(String nom) {
        setTitle("Historique des RDV – " + nom);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        noterButton = new JButton("Noter ce RDV");
        retourButton = new JButton("Retour");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(noterButton);
        buttonPanel.add(retourButton);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        mainPanel.add(new JLabel("Liste des rendez-vous passés :"), BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    /**
     * Met à jour la table d'affichage avec un nouveau modèle de données.
     *
     * @param model Le modèle à afficher dans la JTable.
     */
    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    /**
     * Retourne l'indice de la ligne sélectionnée dans la JTable.
     *
     * @return L'indice de la ligne sélectionnée, ou -1 si aucune sélection.
     */
    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    /**
     * Ajoute un listener pour le bouton de notation d'un rendez-vous.
     *
     * @param listener Le listener à attacher.
     */
    public void addNoterListener(ActionListener listener) {
        noterButton.addActionListener(listener);
    }

    /**
     * Ajoute un listener pour le bouton de retour vers l'accueil patient.
     *
     * @param listener Le listener à attacher.
     */
    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }
}
