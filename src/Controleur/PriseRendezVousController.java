package Controleur;

import dao.*;
import Model.*;
import Vue.PriseRendezVousView;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class PriseRendezVousController {
    private PriseRendezVousView view;
    private RendezVousDAO rendezVousDAO;
    private SpecialisteDAO specialisteDAO;
    private LieuDAO lieuDAO;
    private Patient patient;
    private PatientDashboardController dashboard;
    private List<Specialiste> specialistes;
    private List<Lieu> lieux;

    public PriseRendezVousController(Patient patient, PatientDashboardController dashboard) {
        this.patient = patient;
        this.dashboard = dashboard;
        this.view = new PriseRendezVousView();
        this.rendezVousDAO = new RendezVousDAO();
        this.specialisteDAO = new SpecialisteDAO();
        this.lieuDAO = new LieuDAO();

        specialistes = specialisteDAO.findAll();
        lieux = lieuDAO.findAll();

        view.setSpecialistes(specialistes.stream()
                .map(s -> s.getPrenom() + " " + s.getNom() + " (ID:" + s.getId() + ")")
                .toArray(String[]::new));

        view.setLieux(lieux.stream()
                .map(l -> l.getNomEtablissement() + " - " + l.getVille() + " (ID:" + l.getId() + ")")
                .toArray(String[]::new));

        view.addValiderListener(e -> validerRendezVous());

        view.setVisible(true);
    }

    private void validerRendezVous() {
        try {
            int idSpecialiste = extractIdFromCombo(view.getSelectedSpecialiste());
            int idLieu = extractIdFromCombo(view.getSelectedLieu());
            LocalDateTime dateHeure = LocalDateTime.parse(view.getDateHeure().replace(" ", "T"));

            if (!rendezVousDAO.isCreneauDisponible(idSpecialiste, idLieu, dateHeure)) {
                view.setStatus("❌ Créneau déjà réservé. Choisissez une autre date/heure.");
                return;
            }

            RendezVous rdv = new RendezVous(dateHeure, patient.getId(), idSpecialiste, idLieu);

            if (rendezVousDAO.create(rdv)) {
                view.setStatus("✅ Rendez-vous enregistré !");
                view.dispose();
                dashboard.refresh();
            } else {
                view.setStatus("❌ Échec de l'enregistrement.");
            }

        } catch (DateTimeParseException e) {
            view.setStatus("⚠️ Format de date invalide.");
        } catch (Exception e) {
            view.setStatus("❌ Erreur lors de la prise de RDV.");
            e.printStackTrace();
        }
    }

    private int extractIdFromCombo(String str) {
        String idStr = str.substring(str.indexOf("ID:") + 3, str.indexOf(")"));
        return Integer.parseInt(idStr);
    }
}