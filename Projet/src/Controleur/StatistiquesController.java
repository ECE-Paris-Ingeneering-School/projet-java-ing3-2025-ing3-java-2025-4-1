
package Controleur;

import Vue.StatistiquesFrame;
import dao.RendezVousDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.util.Map;

public class StatistiquesController {
    private StatistiquesFrame view;
    private RendezVousDAO dao;

    public StatistiquesController() {
        this.view = new StatistiquesFrame();
        this.dao = new RendezVousDAO();

        view.addParSpecialiteListener(e -> afficherParSpecialite());
        view.addParLieuListener(e -> afficherParLieu());
        view.addParMoisListener(e -> afficherParMois());

        view.addRetourListener(e -> {
            view.dispose();
            new AdministrationController();
        });


        view.setVisible(true);
    }

    private void afficherParSpecialite() {
        Map<String, Integer> data = dao.countBySpecialite();
        DefaultPieDataset dataset = new DefaultPieDataset();
        data.forEach(dataset::setValue);

        JFreeChart chart = ChartFactory.createPieChart(
                "Nombre de RDV par spécialité", dataset, true, true, false);
        view.setChart(chart);
    }

    private void afficherParLieu() {
        Map<String, Integer> data = dao.countByLieu();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((key, value) -> dataset.addValue(value, "RDV", key));

        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre de RDV par lieu", "Lieu", "Nombre de RDV", dataset);
        view.setChart(chart);
    }

    private void afficherParMois() {
        Map<String, Integer> data = dao.countByMois();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((mois, count) -> dataset.addValue(count, "RDV", mois));

        JFreeChart chart = ChartFactory.createLineChart(
                "Nombre de RDV par mois", "Mois", "Nombre de RDV", dataset);
        view.setChart(chart);
    }
}
