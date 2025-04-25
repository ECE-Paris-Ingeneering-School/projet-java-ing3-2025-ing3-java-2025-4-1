package Vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class HistoriqueView extends JFrame {
    private JTable table;
    private JButton noterButton;
    private JButton retourButton;

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

    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public void addNoterListener(ActionListener listener) {
        noterButton.addActionListener(listener);
    }

    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }
}
