package Controleur;

import Vue.StatistiquesFrame;
import dao.RendezVousDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.util.Map;

/**
 * Contrôleur responsable de l'affichage des statistiques graphiques
 * dans l'interface d'administration.
 * Permet d'afficher des graphiques selon différentes dimensions :
 * - Par spécialité (camembert)
 * - Par lieu (barres)
 * - Par mois (courbe)
 *
 * Vue : {@link StatistiquesFrame}
 * DAO : {@link RendezVousDAO}
 * Utilisation de la librairie : {@link JFreeChart}
 *
 * Ce module est accessible aux administrateurs et s'intègre dans l'architecture MVC.
 *
 */
public class StatistiquesController {
    private StatistiquesFrame view;
    private RendezVousDAO dao;

    /**
     * Initialise la vue des statistiques et configure les actions utilisateurs.
     */
    public StatistiquesController() {
        this.view = new StatistiquesFrame();
        this.dao = new RendezVousDAO();

        // Actions pour changer de type de graphique
        view.addParSpecialiteListener(e -> afficherParSpecialite());
        view.addParLieuListener(e -> afficherParLieu());
        view.addParMoisListener(e -> afficherParMois());

        // Retour à l'accueil administration
        view.addRetourListener(e -> {
            view.dispose();
            new AdministrationController();
        });

        view.setVisible(true);
    }

    /**
     * Affiche un camembert représentant la répartition des rendez-vous par spécialité.
     */
    private void afficherParSpecialite() {
        Map<String, Integer> data = dao.countBySpecialite();
        DefaultPieDataset dataset = new DefaultPieDataset();
        data.forEach(dataset::setValue);

        JFreeChart chart = ChartFactory.createPieChart(
                "Nombre de RDV par spécialité", dataset, true, true, false);
        view.setChart(chart);
    }

    /**
     * Affiche un graphique en barres représentant la répartition des rendez-vous par lieu.
     */
    private void afficherParLieu() {
        Map<String, Integer> data = dao.countByLieu();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((key, value) -> dataset.addValue(value, "RDV", key));

        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre de RDV par lieu", "Lieu", "Nombre de RDV", dataset);
        view.setChart(chart);
    }

    /**
     * Affiche une courbe représentant l'évolution du nombre de rendez-vous par mois.
     */
    private void afficherParMois() {
        Map<String, Integer> data = dao.countByMois();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((mois, count) -> dataset.addValue(count, "RDV", mois));

        JFreeChart chart = ChartFactory.createLineChart(
                "Nombre de RDV par mois", "Mois", "Nombre de RDV", dataset);
        view.setChart(chart);
    }
}
