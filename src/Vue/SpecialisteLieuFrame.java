
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
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Components
        specialisteCombo = new JComboBox<>();
        lieuCombo = new JComboBox<>();
        affecterButton = new JButton("Affecter");
        supprimerButton = new JButton("Supprimer l'affectation");
        retourButton = new JButton("Retour");

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Affectations existantes"));

        // Top panel (Retour button)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(retourButton);

        // Left panel (Form)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Nouvelle affectation"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Spécialiste :"), gbc);
        gbc.gridx = 1;
        formPanel.add(specialisteCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Lieu :"), gbc);
        gbc.gridx = 1;
        formPanel.add(lieuCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(affecterButton, gbc);

        gbc.gridy = 3;
        formPanel.add(supprimerButton, gbc);

        // Split layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, scrollPane);
        splitPane.setResizeWeight(0.35);
        splitPane.setDividerLocation(300);

        // Main layout
        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
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
