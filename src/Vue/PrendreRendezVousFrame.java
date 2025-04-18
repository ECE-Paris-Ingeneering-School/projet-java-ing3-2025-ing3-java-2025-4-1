package Vue;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PrendreRendezVousFrame extends JFrame {
    private JComboBox<String> horaireComboBox;
    private JButton confirmerButton;

    public PrendreRendezVousFrame() {
        setTitle("Prendre Rendez-vous");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel selectHoraireLabel = new JLabel("Sélectionnez un horaire:");
        horaireComboBox = new JComboBox<>(new String[]{"10:00 AM", "11:00 AM", "12:00 PM"});
        confirmerButton = new JButton("Confirmer le Rendez-vous");

        panel.add(selectHoraireLabel);
        panel.add(horaireComboBox);
        panel.add(confirmerButton);

        add(panel);
    }

    public String getSelectedHoraire() {
        return (String) horaireComboBox.getSelectedItem();
    }

    public void addConfirmerRendezVousListener(ActionListener listener) {
        confirmerButton.addActionListener(listener);
    }
}

