package Controleur;

import Model.Lieu;
import Model.RendezVous;
import Model.Specialiste;
import Vue.PriseRdvView;
import Model.Patient;
import dao.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

public class PriseRdvController {
    private PriseRdvView view;
    private Patient patient;
    private LieuDAO lieuDAO;
    private SpecialisteDAO specialisteDAO;
    private RendezVousDAO rendezVousDAO;

    public PriseRdvController(Patient patient) {
        this.patient = patient;
        this.view = new PriseRdvView(patient.getPrenom());

        this.lieuDAO = new LieuDAO();
        this.specialisteDAO = new SpecialisteDAO();
        this.rendezVousDAO = new RendezVousDAO();

        // Charger données
        List<Lieu> lieux = lieuDAO.findAll();
        List<Specialiste> specialistes = specialisteDAO.findAll();

        view.setLieux(lieux.stream()
                .map(l -> l.getNomEtablissement() + " - " + l.getVille() + " (ID:" + l.getId() + ")")
                .toArray(String[]::new));

        view.setSpecialistes(specialistes.stream()
                .map(s -> s.getPrenom() + " " + s.getNom() + " (ID:" + s.getId() + ")")
                .toArray(String[]::new));

        view.addValiderListener(e -> {
            String lText = view.getSelectedLieu();
            String sText = view.getSelectedSpecialiste();
            String dt = view.getSelectedDateTime();

            if (lText == null || sText == null || dt.isEmpty()) {
                view.setStatus("❌ Veuillez remplir tous les champs.");
                return;
            }

            try {
                int idSpec = Integer.parseInt(sText.substring(sText.indexOf("ID:") + 3, sText.indexOf(")")));
                int idLieu = Integer.parseInt(lText.substring(lText.indexOf("ID:") + 3, lText.indexOf(")")));
                LocalDateTime dateTime = LocalDateTime.parse(dt.replace(" ", "T"));

                if (!rendezVousDAO.isCreneauDisponible(idSpec, idLieu, dateTime)) {
                    view.setStatus("❌ Ce créneau est déjà réservé.");
                    return;
                }

                RendezVous rdv = new RendezVous(dateTime, patient.getId(), idSpec, idLieu);
                if (rendezVousDAO.create(rdv)) {
                    JOptionPane.showMessageDialog(view, "✅ Rendez-vous pris !");
                    view.dispose();
                    new PatientHomeController(patient);
                } else {
                    view.setStatus("❌ Erreur lors de la prise de rendez-vous.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.setStatus("⚠️ Format invalide.");
            }
        });

        view.addRetourListener(e -> {
            view.dispose();
            new PatientHomeController(patient);
        });

        view.setVisible(true);
    }
}
