package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RechercheSpecialisteFrame extends JFrame {
    private JList<String> specialisteList;
    private DefaultListModel<String> listModel;

    public RechercheSpecialisteFrame() {
        setTitle("Recherche de Spécialistes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        specialisteList = new JList<>(listModel);
        specialisteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(specialisteList);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    public void setListModel(DefaultListModel<String> model) {
        this.listModel = model;
        specialisteList.setModel(this.listModel);
    }

    public String getSelectedSpecialiste() {
        return specialisteList.getSelectedValue();
    }

    public void addDetailsListener(ActionListener listener) {
        JButton detailsButton = new JButton("Voir les Détails");
        detailsButton.addActionListener(listener);
        add(detailsButton, BorderLayout.SOUTH);
    }
}
