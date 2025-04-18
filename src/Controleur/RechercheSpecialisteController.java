package Controleur;

import Model.Specialiste;
import Vue.RechercheSpecialisteFrame;
import Vue.SpecialisteDetailFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RechercheSpecialisteController implements ActionListener {
    private RechercheSpecialisteFrame view;
    private List<Specialiste> specialistes;

    public RechercheSpecialisteController(RechercheSpecialisteFrame view, List<Specialiste> specialistes) {
        this.view = view;
        this.specialistes = specialistes;

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Specialiste specialiste : specialistes) {
            listModel.addElement(specialiste.getNom() + " - " + specialiste.getSpecialisation());
        }
        view.setListModel(listModel);
        view.addDetailsListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedSpecialiste = view.getSelectedSpecialiste();
        if (selectedSpecialiste != null) {
            for (Specialiste specialiste : specialistes) {
                if ((specialiste.getNom() + " - " + specialiste.getSpecialisation()).equals(selectedSpecialiste)) {
                    new SpecialisteDetailFrame().setVisible(true);
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un spécialiste.");
        }
    }
}

