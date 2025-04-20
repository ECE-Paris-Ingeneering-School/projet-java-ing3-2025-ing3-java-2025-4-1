
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
    private JTable table;

    public SpecialisteLieuFrame() {
        setTitle("Affectation Spécialiste ↔ Lieu");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        specialisteCombo = new JComboBox<>();
        lieuCombo = new JComboBox<>();
        affecterButton = new JButton("Affecter");
        supprimerButton = new JButton("Supprimer l'affectation");

        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Nouvelle affectation"));

        topPanel.add(new JLabel("Spécialiste :"));
        topPanel.add(specialisteCombo);
        topPanel.add(new JLabel("Lieu :"));
        topPanel.add(lieuCombo);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(affecterButton);
        buttonPanel.add(supprimerButton);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
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
            return new String[] { idSpec, idLieu };
        }
        return null;
    }
}
