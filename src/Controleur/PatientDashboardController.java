package Controleur;

import dao.*;
import Model.*;
import Vue.PatientDashboardView;
import Vue.ConnexionView;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDashboardController {
    private PatientDashboardView view;
    private RendezVousDAO rendezVousDAO;
    private SpecialisteDAO specialisteDAO;
    private SpecialisteLieuDAO specialisteLieuDAO;
    private LieuDAO lieuDAO;
    private Patient patient;

    public PatientDashboardController(Patient patient) {
        this.patient = patient;
        this.view = new PatientDashboardView(patient.getPrenom());

        this.rendezVousDAO = new RendezVousDAO();
        this.specialisteDAO = new SpecialisteDAO();
        this.lieuDAO = new LieuDAO();
        this.specialisteLieuDAO = new SpecialisteLieuDAO();

        refresh();

        view.addAnnulerRdvButtonListener(e -> {
            String selection = view.getRdvTextSelection();
            if (selection == null || !selection.contains("[")) {
                JOptionPane.showMessageDialog(view, "❌ Clique d'abord sur un rendez-vous.");
                return;
            }

            try {
                int id = Integer.parseInt(selection.substring(selection.indexOf("[") + 1, selection.indexOf("]")));

                JLabel message = new JLabel("<html><body style='text-align:center;'>" +
                        "⚠️ <b>Êtes-vous sûr de vouloir supprimer ce rendez-vous ?</b><br><br>" +
                        "Cette action est irréversible." +
                        "</body></html>");
                message.setFont(new Font("SansSerif", Font.PLAIN, 14));
                int confirm = JOptionPane.showConfirmDialog(view, message, "🗑️ Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    if (rendezVousDAO.delete(id)) {
                        JOptionPane.showMessageDialog(view, "✅ Rendez-vous annulé.");
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(view, "❌ Échec de l'annulation.");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "⚠️ Erreur lors de la suppression du RDV.");
                ex.printStackTrace();
            }
        });

        view.addLogoutListener(e -> {
            view.dispose();
            ConnexionView loginView = new ConnexionView();
            new ConnexionController(loginView, new PatientDAO(), new AdministrateurDAO());
            loginView.setVisible(true);
        });

        List<Lieu> lieux = lieuDAO.findAll();
        view.setLieux(lieux.stream()
                .map(l -> l.getNomEtablissement() + " - " + l.getVille() + " (ID:" + l.getId() + ")")
                .toArray(String[]::new));

        view.setLieuChangeListener(e -> {
            String lText = view.getSelectedLieu();
            if (lText == null || !lText.contains("ID:")) return;

            int idLieu = Integer.parseInt(lText.substring(lText.indexOf("ID:") + 3, lText.indexOf(")")));

            List<Specialiste> specialistesAssocies = specialisteLieuDAO.findSpecialistesByLieu(idLieu);

            String[] noms = specialistesAssocies.stream()
                    .map(s -> s.getPrenom() + " " + s.getNom() + " (ID:" + s.getId() + ")")
                    .toArray(String[]::new);

            view.setSpecialistes(noms);
        });

        view.addValiderRdvListener(e -> {
            try {
                String sText = view.getSelectedSpecialiste();
                String lText = view.getSelectedLieu();
                String dateText = view.getSelectedDateTime();

                if (sText == null || lText == null || dateText.isEmpty()) {
                    view.setStatusRdv("❌ Veuillez remplir tous les champs.");
                    return;
                }

                int idSpecialiste = Integer.parseInt(sText.substring(sText.indexOf("ID:") + 3, sText.indexOf(")")));
                int idLieu = Integer.parseInt(lText.substring(lText.indexOf("ID:") + 3, lText.indexOf(")")));
                java.time.LocalDateTime dateHeure = java.time.LocalDateTime.parse(dateText.replace(" ", "T"));

                if (!rendezVousDAO.isCreneauDisponible(idSpecialiste, idLieu, dateHeure)) {
                    view.setStatusRdv("❌ Ce créneau est déjà réservé.");
                    return;
                }

                RendezVous rdv = new RendezVous(dateHeure, patient.getId(), idSpecialiste, idLieu);
                if (rendezVousDAO.create(rdv)) {
                    view.setStatusRdv("✅ Rendez-vous confirmé !");
                    refresh();
                    view.switchToTab(1);
                } else {
                    view.setStatusRdv("❌ Échec lors de la prise de rendez-vous.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.setStatusRdv("⚠️ Erreur dans les champs. Format date : YYYY-MM-DD HH:MM");
            }
        });

        view.setVisible(true);
    }

    public void refresh() {
        List<RendezVous> rdvs = rendezVousDAO.findByPatientId(patient.getId());
        StringBuilder builder = new StringBuilder();

        if (rdvs.isEmpty()) {
            builder.append("Aucun rendez-vous trouvé.");
        } else {
            for (RendezVous rdv : rdvs) {
                String date = rdv.getDateHeure().toString();

                Specialiste s = specialisteDAO.findById(rdv.getIdSpecialiste());
                String specialisteNom = (s != null) ? s.getPrenom() + " " + s.getNom() : "Inconnu";

                Lieu l = lieuDAO.findById(rdv.getIdLieu());
                String lieuNom = (l != null) ? l.getNomEtablissement() + " - " + l.getVille() : "Inconnu";

                builder.append("[").append(rdv.getId()).append("] ")
                        .append("📅 ").append(date)
                        .append(" – 👨‍⚕️ ").append(specialisteNom)
                        .append(" – 🏥 ").append(lieuNom)
                        .append("\n");
            }
        }

        view.afficherRendezVous(builder.toString());
    }
}