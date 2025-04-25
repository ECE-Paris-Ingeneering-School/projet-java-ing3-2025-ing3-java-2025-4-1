package Controleur;

import Model.Lieu;
import Model.Patient;
import Model.RendezVous;
import Model.Specialiste;
import Vue.PriseRdvView;
import dao.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class PriseRdvController {
    private PriseRdvView view;
    private Patient patient;
    private LieuDAO lieuDAO;
    private SpecialisteDAO specialisteDAO;
    private SpecialisteLieuDAO specialisteLieuDAO;
    private RendezVousDAO rendezVousDAO;

    public PriseRdvController(Patient patient) {
        this.patient = patient;
        this.view = new PriseRdvView(patient.getPrenom());

        this.lieuDAO = new LieuDAO();
        this.specialisteDAO = new SpecialisteDAO();
        this.specialisteLieuDAO = new SpecialisteLieuDAO();
        this.rendezVousDAO = new RendezVousDAO();

        List<Lieu> lieux = lieuDAO.findAll();
        view.setLieux(lieux.stream()
                .map(l -> l.getNomEtablissement() + " - " + l.getVille() + " (ID:" + l.getId() + ")")
                .toArray(String[]::new));

        List<Specialiste> allSpecs = specialisteDAO.findAll();
        List<String> specialisations = allSpecs.stream().map(Specialiste::getSpecialisation).distinct().collect(Collectors.toList());
        List<String> qualifications = allSpecs.stream().map(Specialiste::getQualification).distinct().collect(Collectors.toList());

        specialisations.add(0, "Toutes");
        qualifications.add(0, "Toutes");

        view.setSpecialisations(specialisations.toArray(new String[0]));
        view.setQualifications(qualifications.toArray(new String[0]));

        view.setLieuChangeListener(e -> updateSpecialistes());
        view.setSpecQualFilterListener(e -> updateSpecialistes());
        view.setDateChangeListener(e -> updateCreneaux());
        view.setSpecialisteChangeListener(e -> updateCreneaux());

        view.addValiderListener(e -> {
            String sText = view.getSelectedSpecialiste();
            String lText = view.getSelectedLieu();
            String dateTimeStr = view.getSelectedCreneau();

            if (sText == null || lText == null || dateTimeStr == null || dateTimeStr.isEmpty()) {
                view.setStatus("❌ Veuillez remplir tous les champs.");
                return;
            }

            try {
                int idSpec = Integer.parseInt(sText.substring(sText.indexOf("ID:") + 3, sText.indexOf(")")));
                int idLieu = Integer.parseInt(lText.substring(lText.indexOf("ID:") + 3, lText.indexOf(")")));
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr.replace(" ", "T"));

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

    private void updateSpecialistes() {
        String lText = view.getSelectedLieu();
        String spec = view.getSelectedSpecialisation();
        String qual = view.getSelectedQualification();

        if (lText == null || !lText.contains("ID:")) return;

        int idLieu = Integer.parseInt(lText.substring(lText.indexOf("ID:") + 3, lText.indexOf(")")));
        List<Specialiste> specList = specialisteLieuDAO.findSpecialistesByLieu(idLieu);

        if (spec != null && !spec.equals("Toutes")) {
            specList = specList.stream().filter(s -> s.getSpecialisation().equals(spec)).collect(Collectors.toList());
        }
        if (qual != null && !qual.equals("Toutes")) {
            specList = specList.stream().filter(s -> s.getQualification().equals(qual)).collect(Collectors.toList());
        }

        if (specList.isEmpty()) {
            view.setSpecialistes(new String[]{"Aucun spécialiste"});
        } else {
            view.setSpecialistes(specList.stream()
                    .map(s -> s.getPrenom() + " " + s.getNom() + " (ID:" + s.getId() + ")")
                    .toArray(String[]::new));
        }
    }

    private void updateCreneaux() {
        String sText = view.getSelectedSpecialiste();
        String lText = view.getSelectedLieu();

        if (sText == null || lText == null || !sText.contains("ID:")) return;

        int idSpec = Integer.parseInt(sText.substring(sText.indexOf("ID:") + 3, sText.indexOf(")")));
        int idLieu = Integer.parseInt(lText.substring(lText.indexOf("ID:") + 3, lText.indexOf(")")));

        String jour = view.getSelectedJour();
        String mois = view.getSelectedMois();
        String annee = view.getSelectedAnnee();

        if (jour == null || mois == null || annee == null) return;

        String moisNum = mois.substring(0, 2);
        LocalDate date;
        try {
            date = LocalDate.parse(annee + "-" + moisNum + "-" + jour);
        } catch (Exception e) {
            return;
        }

        List<String> dispo = new java.util.ArrayList<>();
        for (int h = 8; h < 19; h++) {
            for (int m = 0; m < 60; m += 15) {
                LocalTime time = LocalTime.of(h, m);
                LocalDateTime dt = LocalDateTime.of(date, time);
                if (rendezVousDAO.isCreneauDisponible(idSpec, idLieu, dt)) {
                    dispo.add(dt.toString().replace("T", " "));
                }
            }
        }
        view.setCreneaux(dispo.toArray(new String[0]));
    }
}
