package Controleur;

import dao.RendezVousDAO;
import dao.SpecialisteDAO;
import Vue.AdministrationFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministrationController implements ActionListener {
    private AdministrationFrame view;
    private SpecialisteDAO specialisteDAO;
    private RendezVousDAO rendezVousDAO;

    public AdministrationController(AdministrationFrame view, SpecialisteDAO specialisteDAO, RendezVousDAO rendezVousDAO) {
        this.view = view;
        this.specialisteDAO = specialisteDAO;
        this.rendezVousDAO = rendezVousDAO;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Gérer les Spécialistes".equals(command)) {
            // Logique de gestion des spécialistes
            // Ajoutez ici la logique pour afficher et modifier les spécialistes
        } else if ("Gérer les Rendez-vous".equals(command)) {
            // Logique de gestion des rendez-vous
            // Ajoutez ici la logique pour afficher et modifier les rendez-vous
        } else if ("Voir les Rapports".equals(command)) {
            // Logique d'affichage des rapports
            // Ajoutez ici la logique pour générer et afficher les rapports
        }
    }
}

