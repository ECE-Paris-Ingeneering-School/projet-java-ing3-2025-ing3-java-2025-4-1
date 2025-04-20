
package Vue;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatistiquesFrame extends JFrame {
    private JButton parSpecialiteButton;
    private JButton parLieuButton;
    private JButton parMoisButton;
    private JPanel chartContainer;

    public StatistiquesFrame() {
        setTitle("Statistiques des rendez-vous");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        parSpecialiteButton = new JButton("Par spécialité");
        parLieuButton = new JButton("Par lieu");
        parMoisButton = new JButton("Par mois");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(parSpecialiteButton);
        buttonPanel.add(parLieuButton);
        buttonPanel.add(parMoisButton);

        chartContainer = new JPanel(new BorderLayout());
        chartContainer.setBorder(BorderFactory.createTitledBorder("Graphique"));

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(chartContainer, BorderLayout.CENTER);
    }

    public void setChart(org.jfree.chart.JFreeChart chart) {
        chartContainer.removeAll();
        chartContainer.add(new ChartPanel(chart), BorderLayout.CENTER);
        chartContainer.validate();
    }

    public void addParSpecialiteListener(ActionListener l) {
        parSpecialiteButton.addActionListener(l);
    }

    public void addParLieuListener(ActionListener l) {
        parLieuButton.addActionListener(l);
    }

    public void addParMoisListener(ActionListener l) {
        parMoisButton.addActionListener(l);
    }
}
