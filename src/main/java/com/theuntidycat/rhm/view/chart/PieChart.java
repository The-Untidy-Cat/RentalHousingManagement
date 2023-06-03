/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.view.chart;

import com.theuntidycat.rhm.model.PieChartDataset;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author SHeroAnh
 */
public class PieChart extends javax.swing.JPanel {

    private JFreeChart pieChart;
    private ChartPanel chartPanel;

    private JFreeChart createChart(String title, PieDataset dataset) {
        TextTitle titleChart = new TextTitle(title, new Font("Segoe UI", Font.BOLD, 15));
        JFreeChart chart = ChartFactory.createPieChart(
                "", dataset, true, true, true);
        chart.setTitle(titleChart);
        return chart;
    }

    private PieDataset createDataset(ArrayList<PieChartDataset> data) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < data.size(); i++) {
            dataset.setValue(String.valueOf(data.get(i).key), new Double(data.get(i).value));
        }

        return dataset;
    }

    public PieChart() {
        initComponent();
    }

    public void initComponent() {
        ArrayList<PieChartDataset> data = new ArrayList<>();
        data.add(new PieChartDataset("Key 1", 15));
        pieChart = createChart("", createDataset(data));
        chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(785, 440));
        chartPanel.setMouseWheelEnabled(true);
        setLayout(new java.awt.BorderLayout());
        add(chartPanel, java.awt.BorderLayout.CENTER);
    }

    public void loadChart(String title, ArrayList<PieChartDataset> dataset) {
        pieChart = createChart(title, createDataset(dataset));
        pieChart.getPlot().setBackgroundPaint(UIManager.getColor("Panel.background"));
        pieChart.setBackgroundPaint(UIManager.getColor("Panel.background"));
        pieChart.getPlot().setOutlinePaint(null);
        chartPanel = new ChartPanel(pieChart);
        add(chartPanel, java.awt.BorderLayout.CENTER);
    }
    
    public PiePlot getPlot(){
        return (PiePlot) pieChart.getPlot();
    }
}
