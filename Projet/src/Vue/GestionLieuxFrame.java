
package Vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class GestionLieuxFrame extends JFrame {
    private JTable table;
    private JTextField nomField;
    private JTextField adresseField;
    private JTextField villeField;
    private JTextField codePostalField;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;

    public GestionLieuxFrame() {
        setTitle("Gestion des lieux");
        setSize(750, 500);
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
        formPanel.add(new JLabel());
        formPanel.add(supprimerButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
    }

    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    public int getSelectedLieuId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) table.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    public String getNom() { return nomField.getText(); }
    public String getAdresse() { return adresseField.getText(); }
    public String getVille() { return villeField.getText(); }
    public String getCodePostal() { return codePostalField.getText(); }

    public void clearFields() {
        nomField.setText("");
        adresseField.setText("");
        villeField.setText("");
        codePostalField.setText("");
    }

    public void addAjouterListener(ActionListener l) { ajouterButton.addActionListener(l); }
    public void addModifierListener(ActionListener l) { modifierButton.addActionListener(l); }
    public void addSupprimerListener(ActionListener l) { supprimerButton.addActionListener(l); }
}
