package Vue;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vue graphique pour afficher les statistiques des rendez-vous sous forme de graphiques.
 * Permet de visualiser :
 * - Le nombre de rendez-vous par spécialité
 * - Le nombre de rendez-vous par lieu
 * - L'évolution du nombre de rendez-vous par mois
 *
 * Composants principaux : JPanel, JButton, ChartPanel
 *
 */
public class StatistiquesFrame extends JFrame {
    private JButton parSpecialiteButton;
    private JButton parLieuButton;
    private JButton parMoisButton;
    private JButton retourButton;
    private JPanel chartContainer;

    /**
     * Construit la fenêtre d'affichage des statistiques.
     */
    public StatistiquesFrame() {
        setTitle("Statistiques des rendez-vous");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        parSpecialiteButton = new JButton("Par spécialité");
        parLieuButton = new JButton("Par lieu");
        parMoisButton = new JButton("Par mois");
        retourButton = new JButton("Retour");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(parSpecialiteButton);
        buttonPanel.add(parLieuButton);
        buttonPanel.add(parMoisButton);

        chartContainer = new JPanel(new BorderLayout());
        chartContainer.setBorder(BorderFactory.createTitledBorder("Graphique"));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(retourButton);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(chartContainer, BorderLayout.CENTER);
    }

    /**
     * Met à jour le graphique affiché dans la fenêtre.
     *
     * @param chart Le graphique {@link JFreeChart} à afficher.
     */
    public void setChart(JFreeChart chart) {
        chartContainer.removeAll();
        chartContainer.add(new ChartPanel(chart), BorderLayout.CENTER);
        chartContainer.validate();
    }

    // Méthodes pour ajouter des ActionListeners sur les boutons

    /**
     * Ajoute un listener pour afficher les statistiques par spécialité.
     *
     * @param l Le listener.
     */
    public void addParSpecialiteListener(ActionListener l) {
        parSpecialiteButton.addActionListener(l);
    }

    /**
     * Ajoute un listener pour afficher les statistiques par lieu.
     *
     * @param l Le listener.
     */
    public void addParLieuListener(ActionListener l) {
        parLieuButton.addActionListener(l);
    }

    /**
     * Ajoute un listener pour afficher les statistiques par mois.
     *
     * @param l Le listener.
     */
    public void addParMoisListener(ActionListener l) {
        parMoisButton.addActionListener(l);
    }

    /**
     * Ajoute un listener pour retourner à l'accueil administrateur.
     *
     * @param l Le listener.
     */
    public void addRetourListener(ActionListener l) {
        retourButton.addActionListener(l);
    }
}
