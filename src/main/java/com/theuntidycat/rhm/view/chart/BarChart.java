/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.view.chart;

import com.theuntidycat.rhm.model.BarChartDataset;
import com.theuntidycat.rhm.model.PieChartDataset;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author SHeroAnh
 */
public class BarChart extends javax.swing.JPanel {

    private JFreeChart barChart;
    private ChartPanel chartPanel;

    private JFreeChart createChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation) {
        TextTitle titleChart = new TextTitle(title, new Font("Segoe UI", Font.BOLD, 15));
        JFreeChart chart = ChartFactory.createBarChart(
                "", categoryAxisLabel, valueAxisLabel, dataset, orientation, true, true, true);
        chart.setTitle(titleChart);
        return chart;
    }

    private CategoryDataset createDataset(ArrayList<BarChartDataset> data) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < data.size(); i++) {
            dataset.addValue(data.get(i).value, data.get(i).unit, data.get(i).key);
        }
        return dataset;
    }

    public BarChart() {
        initComponent();
    }

    public void initComponent() {
        ArrayList<BarChartDataset> data = new ArrayList<>();
        data.add(new BarChartDataset("Key 1", 15, "unit"));
        barChart = createChart("", null, null, createDataset(data), PlotOrientation.VERTICAL);
        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(785, 440));
        chartPanel.setMouseWheelEnabled(true);
        setLayout(new java.awt.BorderLayout());
        add(chartPanel, java.awt.BorderLayout.CENTER);
    }

    public void loadChart(String title, ArrayList<BarChartDataset> dataset) {
        barChart = createChart(title, null, null, createDataset(dataset), PlotOrientation.VERTICAL);
        barChart.getPlot().setBackgroundPaint(UIManager.getColor("Panel.background"));
        barChart.setBackgroundPaint(UIManager.getColor("Panel.background"));
        barChart.getPlot().setOutlinePaint(null);
        chartPanel = new ChartPanel(barChart);
        add(chartPanel, java.awt.BorderLayout.CENTER);
        
    }
}
