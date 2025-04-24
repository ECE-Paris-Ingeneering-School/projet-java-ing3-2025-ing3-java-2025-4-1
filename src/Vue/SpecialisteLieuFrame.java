
package Vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class SpecialisteLieuFrame extends JFrame {
    private JComboBox<String> specialisteCombo;
    private JComboBox<String> lieuCombo;
    private JButton affecterButton;
    private JButton supprimerButton;
    private JButton retourButton;
    private JTable table;

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

        // Panel du haut (retour)
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftTopPanel.add(retourButton);
        topPanel.add(leftTopPanel, BorderLayout.WEST);

        // Panel de sélection
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

        // Panel des boutons action
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(affecterButton);
        buttonPanel.add(supprimerButton);

        // Table
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Affectations existantes"));

        // Layout global
        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(selectionPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
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

    public void addAffecterListener(ActionListener listener) {
        affecterButton.addActionListener(listener);
    }

    public void addSupprimerListener(ActionListener listener) {
        supprimerButton.addActionListener(listener);
    }

    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }

    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public String[] getSelectedAffectation() {
        int row = getSelectedRow();
        if (row != -1) {
            String idSpec = table.getValueAt(row, 0).toString();
            String idLieu = table.getValueAt(row, 2).toString();
            return new String[]{idSpec, idLieu};
        }
        return null;
    }
}
