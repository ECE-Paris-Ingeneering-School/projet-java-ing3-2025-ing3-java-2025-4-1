
package Vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConsultationRendezVousFrame extends JFrame {
    private JTable table;
    private JButton refreshButton;
    private JButton filterButton;
    private JButton deleteButton;
    private JButton modifierButton;
    private JButton retourButton;
    private JComboBox<String> patientFilter;
    private JComboBox<String> specialisteFilter;
    private JComboBox<String> lieuFilter;
    private JTextField dateTimeField;

    public ConsultationRendezVousFrame() {
        setTitle("Consultation des rendez-vous");
        setSize(950, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        patientFilter = new JComboBox<>();
        specialisteFilter = new JComboBox<>();
        lieuFilter = new JComboBox<>();
        dateTimeField = new JTextField("YYYY-MM-DD HH:MM");

        refreshButton = new JButton("Rafraîchir");
        filterButton = new JButton("Filtrer");
        deleteButton = new JButton("Supprimer");
        modifierButton = new JButton("Modifier");
        retourButton = new JButton("Retour");

        // Panel haut : gauche (retour), droite (rafraîchir)
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(retourButton);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(refreshButton);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);

        // Panel filtres et modification
        JPanel filterPanel = new JPanel(new GridLayout(3, 4, 10, 5));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filtres & Modification"));
        filterPanel.add(new JLabel("Patient :"));
        filterPanel.add(new JLabel("Spécialiste :"));
        filterPanel.add(new JLabel("Lieu :"));
        filterPanel.add(new JLabel("Date/Heure :"));
        filterPanel.add(patientFilter);
        filterPanel.add(specialisteFilter);
        filterPanel.add(lieuFilter);
        filterPanel.add(dateTimeField);
        filterPanel.add(filterButton);
        filterPanel.add(modifierButton);
        filterPanel.add(deleteButton);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(filterPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    public void setPatientOptions(String[] patients) {
        patientFilter.setModel(new DefaultComboBoxModel<>(patients));
    }

    public void setSpecialisteOptions(String[] specialistes) {
        specialisteFilter.setModel(new DefaultComboBoxModel<>(specialistes));
    }

    public void setLieuOptions(String[] lieux) {
        lieuFilter.setModel(new DefaultComboBoxModel<>(lieux));
    }

    public String getSelectedPatient() {
        return (String) patientFilter.getSelectedItem();
    }

    public String getSelectedSpecialiste() {
        return (String) specialisteFilter.getSelectedItem();
    }

    public String getSelectedLieu() {
        return (String) lieuFilter.getSelectedItem();
    }

    public String getDateHeureField() {
        return dateTimeField.getText();
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public String getSelectedRendezVousId() {
        int row = getSelectedRow();
        if (row != -1) {
            return table.getValueAt(row, 0).toString();
        }
        return null;
    }

    public void addRefreshListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public void addFilterListener(ActionListener listener) {
        filterButton.addActionListener(listener);
    }

    public void addDeleteListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addModifierListener(ActionListener listener) {
        modifierButton.addActionListener(listener);
    }

    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }
}
