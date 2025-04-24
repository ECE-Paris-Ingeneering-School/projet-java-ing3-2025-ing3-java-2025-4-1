package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HistoriqueView extends JFrame {
    private JList<String> rdvList;
    private JTextArea noteArea;
    private JButton saveNoteButton, retourButton;
    private DefaultListModel<String> listModel;

    public HistoriqueView(String nom) {
        setTitle("📝 Historique de mes RDV – " + nom);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<>();
        rdvList = new JList<>(listModel);
        rdvList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroll = new JScrollPane(rdvList);

        noteArea = new JTextArea(4, 20);
        JScrollPane noteScroll = new JScrollPane(noteArea);
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);

        saveNoteButton = new JButton("💾 Sauvegarder la note");
        retourButton = new JButton("⬅️ Retour");

        JPanel btns = new JPanel();
        btns.add(saveNoteButton);
        btns.add(retourButton);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout(10, 10));
        main.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        main.add(new JLabel("🕓 Rendez-vous passés :"), BorderLayout.NORTH);
        main.add(listScroll, BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout(5, 5));
        south.add(new JLabel("🗒 Note / Commentaire :"), BorderLayout.NORTH);
        south.add(noteScroll, BorderLayout.CENTER);
        south.add(btns, BorderLayout.SOUTH);

        main.add(south, BorderLayout.SOUTH);

        add(main);
    }

    public void setRdvList(String[] items) {
        listModel.clear();
        for (String item : items) listModel.addElement(item);
    }

    public String getSelectedRdv() {
        return rdvList.getSelectedValue();
    }

    public void setNoteText(String note) {
        noteArea.setText(note != null ? note : "");
    }

    public String getNoteText() {
        return noteArea.getText().trim();
    }

    public void addSaveNoteListener(ActionListener listener) {
        saveNoteButton.addActionListener(listener);
    }

    public void addRetourListener(ActionListener listener) {
        retourButton.addActionListener(listener);
    }

    public void addRdvSelectionListener(javax.swing.event.ListSelectionListener listener) {
        rdvList.addListSelectionListener(listener);
    }
}
