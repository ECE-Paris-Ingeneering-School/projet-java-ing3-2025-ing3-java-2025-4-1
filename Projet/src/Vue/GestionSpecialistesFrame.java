
package Vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

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

    public void setTableModel(DefaultTableModel model) {
        table.setModel(model);
    }

    public int getSelectedSpecialisteId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) table.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    public String getNom() { return nomField.getText(); }
    public String getPrenom() { return prenomField.getText(); }
    public String getSpecialisation() { return specialisationField.getText(); }
    public String getQualification() { return qualificationField.getText(); }

    public void addAjouterListener(ActionListener listener) {
        ajouterButton.addActionListener(listener);
    }

    public void addModifierListener(ActionListener listener) {
        modifierButton.addActionListener(listener);
    }

    public void addSupprimerListener(ActionListener listener) {
        supprimerButton.addActionListener(listener);
    }

    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }

    public void clearFields() {
        nomField.setText("");
        prenomField.setText("");
        specialisationField.setText("");
        qualificationField.setText("");
    }
}
