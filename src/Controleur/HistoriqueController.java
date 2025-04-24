package Controleur;

import Vue.HistoriqueView;
import dao.*;
import Model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.List;

public class HistoriqueController {
    private HistoriqueView view;
    private RendezVousDAO rendezVousDAO;
    private SpecialisteDAO specialisteDAO;
    private LieuDAO lieuDAO;
    private Patient patient;

    private List<RendezVous> rdvList;

    public HistoriqueController(Patient patient) {
        this.patient = patient;
        this.view = new HistoriqueView(patient.getPrenom());
        this.rendezVousDAO = new RendezVousDAO();
        this.specialisteDAO = new SpecialisteDAO();
        this.lieuDAO = new LieuDAO();

        loadHistorique();
        view.addRetourListener(e -> {
            view.dispose(); // ferme la fenêtre actuelle
            new PatientHomeController(patient); // rouvre le dashboard
        });

        view.addNoterListener(e -> {
            int selectedRow = view.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "❌ Sélectionnez un rendez-vous.");
                return;
            }

            RendezVous rdv = rdvList.get(selectedRow);

            Integer[] notes = {0, 1, 2, 3, 4, 5};
            Integer nouvelleNote = (Integer) JOptionPane.showInputDialog(
                    view,
                    "Attribuez une note à ce rendez-vous :",
                    "Notation",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    notes,
                    rdv.getNote()
            );

            if (nouvelleNote != null) {
                rdv.setNote(nouvelleNote);
                if (rendezVousDAO.update(rdv)) {
                    JOptionPane.showMessageDialog(view, "✅ Note mise à jour !");
                    loadHistorique();
                } else {
                    JOptionPane.showMessageDialog(view, "❌ Échec de la mise à jour.");
                }
            }
        });

        view.setVisible(true);
    }

    private void loadHistorique() {
        List<RendezVous> tous = rendezVousDAO.findByPatientId(patient.getId());
        rdvList = tous.stream()
                .filter(rdv -> rdv.getDateHeure().isBefore(LocalDateTime.now()))
                .toList();

        String[] columns = {"Date", "Spécialiste", "Lieu", "Note"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (RendezVous rdv : rdvList) {
            Specialiste s = specialisteDAO.findById(rdv.getIdSpecialiste());
            String specialisteNom = (s != null) ? s.getPrenom() + " " + s.getNom() : "Inconnu";

            Lieu l = lieuDAO.findById(rdv.getIdLieu());
            String lieuNom = (l != null) ? l.getNomEtablissement() + " - " + l.getVille() : "Inconnu";

            String[] row = {
                    rdv.getDateHeure().toString(),
                    specialisteNom,
                    lieuNom,
                    String.valueOf(rdv.getNote())
            };

            model.addRow(row);
        }

        view.setTableModel(model);
    }
}
