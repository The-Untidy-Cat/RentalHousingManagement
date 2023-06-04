package com.theuntidycat.rhm.view.report;

import com.theuntidycat.rhm.controller.ReportController;
import com.theuntidycat.rhm.model.BarChartDataset;
import com.theuntidycat.rhm.model.PieChartDataset;
import com.theuntidycat.rhm.view.utils.LoadingDialog;
import com.theuntidycat.rhm.view.chart.BarChart;
import com.theuntidycat.rhm.view.chart.PieChart;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.renderer.category.StandardBarPainter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author SHeroAnh
 */
public class ReportView extends javax.swing.JPanel {

    private Locale lc = new Locale("nv", "VN");
    private LoadingDialog loading = new LoadingDialog();
    private ReportController rpCtrl = new ReportController();
    private NumberFormat nf = NumberFormat.getCurrencyInstance(lc);

    /**
     * Creates new form ReportView
     */
    public ReportView() {
//        TestPieChart pieChart = new TestPieChart();
        initComponents();
        
    }

    public void loadRevenue() {
        loading.setVisible(true);
        try {
            PieChart revenuePieChart = new PieChart();
            BarChart revenueBarChart = new BarChart();
            if (String.valueOf(cbbMonth.getSelectedItem()) == "Tất cả") {
                ResultSet rsMonthlyRevenue = rpCtrl.getRevenue().getMonthlyRevenueByYear(String.valueOf(cbbYear.getSelectedItem()));
                double sumReceived = rpCtrl.getRevenue().getTotalReceivedByYear(String.valueOf(cbbYear.getSelectedItem()));
                double sumRevenue = rpCtrl.getRevenue().getTotalRevenueByYear(String.valueOf(cbbYear.getSelectedItem()));
                ArrayList<PieChartDataset> dataset = new ArrayList<>();
                ArrayList<BarChartDataset> dataset1 = new ArrayList<>();
                dataset.add(new PieChartDataset("Đã thu", sumReceived));
                dataset.add(new PieChartDataset("Còn thiếu", sumRevenue - sumReceived));
                JLabel lbSumRevenue = new JLabel("Tổng doanh thu: " + nf.format(sumRevenue));
                JLabel lbSumReceived = new JLabel("Đã thu: " + nf.format(sumReceived));

                while (rsMonthlyRevenue.next()) {
                    dataset1.add(new BarChartDataset("Tháng " + rsMonthlyRevenue.getString("MONTH"), Double.valueOf(rsMonthlyRevenue.getString("SUM_RENTAL_PRICE")), "Đồng"));
                }
                revenueBarChart.loadChart("Biểu đồ doanh thu theo tháng năm " + String.valueOf(cbbYear.getSelectedItem()), dataset1);
                revenuePieChart.loadChart("Biểu đồ doanh thu năm " + String.valueOf(cbbYear.getSelectedItem()), dataset);
                revenueBarChart.getRenderer().setSeriesPaint(0, new Color(185, 215, 234, 255));
                revenueBarChart.getRenderer().setBarPainter(new StandardBarPainter());
                revenuePieChart.getPlot().setSectionPaint("Đã thu", new Color(118, 159, 205, 255));
                revenuePieChart.getPlot().setSectionPaint("Còn thiếu", new Color(185, 215, 234, 255));
                revenueChartPanel.removeAll();
                revenueInfoPanel.removeAll();
                revenueInfoPanel.updateUI();
                revenueChartPanel.updateUI();
                revenueChartPanel.add(revenueBarChart);
                revenueChartPanel.add(revenuePieChart);
                revenueInfoPanel.add(lbSumRevenue);
                revenueInfoPanel.add(lbSumReceived);
                revenueInfoPanel.updateUI();
                revenueChartPanel.updateUI();
            } else {
                revenueChartPanel.removeAll();
                revenueInfoPanel.removeAll();
                double sumReceived = rpCtrl.getRevenue().getTotalReceivedByMonth(String.valueOf(cbbMonth.getSelectedItem()), String.valueOf(cbbYear.getSelectedItem()));
                double sumRevenue = rpCtrl.getRevenue().getTotalRevenueByMonth(String.valueOf(cbbMonth.getSelectedItem()), String.valueOf(cbbYear.getSelectedItem()));
                ArrayList<PieChartDataset> dataset = new ArrayList<>();
                dataset.add(new PieChartDataset("Đã thu", sumReceived));
                dataset.add(new PieChartDataset("Còn thiếu", sumRevenue - sumReceived));
                JLabel lbSumRevenue = new JLabel("Tổng doanh thu: " + nf.format(sumRevenue));
                JLabel lbSumReceived = new JLabel("Đã thu: " + nf.format(sumReceived));
                revenuePieChart.loadChart("Biểu đồ doanh thu tháng " + String.valueOf(cbbMonth.getSelectedItem()) + " năm " + String.valueOf(cbbYear.getSelectedItem()), dataset);
                revenuePieChart.getPlot().setSectionPaint("Đã thu", new Color(118, 159, 205, 255));
                revenuePieChart.getPlot().setSectionPaint("Còn thiếu", new Color(185, 215, 234, 255));
                revenueChartPanel.add(revenuePieChart);
                revenueInfoPanel.add(lbSumRevenue);
                revenueInfoPanel.add(lbSumReceived);
                revenueInfoPanel.updateUI();
                revenueChartPanel.updateUI();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loading.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportTabPane = new javax.swing.JTabbedPane();
        overviewTab = new javax.swing.JPanel();
        bodyPanel = new javax.swing.JPanel();
        labelPanel = new javax.swing.JPanel();
        lbUsedRoom = new javax.swing.JLabel();
        lbWaitingTicket = new javax.swing.JLabel();
        lbActiveTenant = new javax.swing.JLabel();
        lbUnpaidInvoice = new javax.swing.JLabel();
        lbDueSoonContract = new javax.swing.JLabel();
        inputPanel = new javax.swing.JPanel();
        lbUsedRoomValue = new javax.swing.JLabel();
        lbWaitingTicketValue = new javax.swing.JLabel();
        lbActiveTenantValue = new javax.swing.JLabel();
        lbUnpaidInvoiceValue = new javax.swing.JLabel();
        lbDueSoonContractValue = new javax.swing.JLabel();
        ovRevenueChartPanel = new javax.swing.JPanel();
        revenueTab = new javax.swing.JPanel();
        searchBar = new javax.swing.JPanel();
        lbMonth = new javax.swing.JLabel();
        cbbMonth = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        cbbYear = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        revenueInfoPanel = new javax.swing.JPanel();
        revenueChartPanel = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(500, 385));

        reportTabPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        overviewTab.setLayout(new java.awt.BorderLayout());

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        bodyPanel.setLayout(flowLayout1);

        labelPanel.setMinimumSize(new java.awt.Dimension(200, 150));
        labelPanel.setPreferredSize(new java.awt.Dimension(160, 150));
        labelPanel.setLayout(new java.awt.GridLayout(5, 1));

        lbUsedRoom.setText("Phòng đang cho thuê");
        labelPanel.add(lbUsedRoom);

        lbWaitingTicket.setText("Phiếu hỗ trợ chờ xử lý");
        labelPanel.add(lbWaitingTicket);

        lbActiveTenant.setText("Khách hàng đang hoạt động");
        labelPanel.add(lbActiveTenant);

        lbUnpaidInvoice.setText("Hoá đơn chưa thanh toán");
        labelPanel.add(lbUnpaidInvoice);

        lbDueSoonContract.setText("Hợp đồng sắp đến hạn");
        labelPanel.add(lbDueSoonContract);

        bodyPanel.add(labelPanel);

        inputPanel.setMinimumSize(new java.awt.Dimension(50, 150));
        inputPanel.setPreferredSize(new java.awt.Dimension(50, 150));
        inputPanel.setLayout(new java.awt.GridLayout(5, 2));

        lbUsedRoomValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbUsedRoomValue.setText("0/0");
        inputPanel.add(lbUsedRoomValue);

        lbWaitingTicketValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbWaitingTicketValue.setText("0");
        inputPanel.add(lbWaitingTicketValue);

        lbActiveTenantValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbActiveTenantValue.setText("0");
        inputPanel.add(lbActiveTenantValue);

        lbUnpaidInvoiceValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbUnpaidInvoiceValue.setText("0");
        inputPanel.add(lbUnpaidInvoiceValue);

        lbDueSoonContractValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDueSoonContractValue.setText("0");
        inputPanel.add(lbDueSoonContractValue);

        bodyPanel.add(inputPanel);

        overviewTab.add(bodyPanel, java.awt.BorderLayout.CENTER);

        ovRevenueChartPanel.setPreferredSize(new java.awt.Dimension(500, 230));
        ovRevenueChartPanel.setLayout(new java.awt.GridLayout(1, 2));
        overviewTab.add(ovRevenueChartPanel, java.awt.BorderLayout.PAGE_END);

        reportTabPane.addTab("Tổng quan", overviewTab);

        searchBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        lbMonth.setText("Tháng");
        searchBar.add(lbMonth);

        cbbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbbMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbMonthItemStateChanged(evt);
            }
        });
        searchBar.add(cbbMonth);
        searchBar.add(jSeparator1);

        jLabel1.setText("Năm");
        searchBar.add(jLabel1);

        cbbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbYearItemStateChanged(evt);
            }
        });
        searchBar.add(cbbYear);
        searchBar.add(jSeparator2);

        revenueInfoPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5));

        revenueChartPanel.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout revenueTabLayout = new javax.swing.GroupLayout(revenueTab);
        revenueTab.setLayout(revenueTabLayout);
        revenueTabLayout.setHorizontalGroup(
            revenueTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchBar, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addComponent(revenueInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(revenueChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        revenueTabLayout.setVerticalGroup(
            revenueTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenueTabLayout.createSequentialGroup()
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(revenueChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(revenueInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        reportTabPane.addTab("Doanh thu", revenueTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reportTabPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reportTabPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbYearItemStateChanged
        DefaultComboBoxModel<String> cbbMonthModel = new DefaultComboBoxModel<>();

        Date now = new Date();
        cbbMonthModel.addElement("Tất cả");
        for (int i = String.valueOf(cbbYear.getSelectedItem()).equals(Integer.toString(Year.now().getValue())) ? now.getMonth() : 11; i >= 0; i--) {
            cbbMonthModel.addElement(Integer.toString(i + 1));
        }
        cbbMonth.setModel(cbbMonthModel);
        loadRevenue();
    }//GEN-LAST:event_cbbYearItemStateChanged

    private void cbbMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbMonthItemStateChanged
        loadRevenue();
    }//GEN-LAST:event_cbbMonthItemStateChanged
    
    public void initOverviewTab() {
        try {
            // init chart
            PieChart revenuePieChart = new PieChart();
            BarChart revenueBarChart = new BarChart();
            ResultSet rsMonthlyRevenue = rpCtrl.getRevenue().getMonthlyRevenueByYear(String.valueOf(cbbYear.getSelectedItem()));
            double sumReceived = rpCtrl.getRevenue().getTotalReceivedByYear(String.valueOf(cbbYear.getSelectedItem()));
            double sumRevenue = rpCtrl.getRevenue().getTotalRevenueByYear(String.valueOf(cbbYear.getSelectedItem()));
            ArrayList<PieChartDataset> dataset = new ArrayList<>();
            ArrayList<BarChartDataset> dataset1 = new ArrayList<>();
            dataset.add(new PieChartDataset("Đã thu", sumReceived));
            dataset.add(new PieChartDataset("Còn thiếu", sumRevenue - sumReceived));
            while (rsMonthlyRevenue.next()) {
                dataset1.add(new BarChartDataset("Tháng " + rsMonthlyRevenue.getString("MONTH"), Double.valueOf(rsMonthlyRevenue.getString("SUM_RENTAL_PRICE")), "Đồng"));
            }
            revenueBarChart.loadChart("Biểu đồ doanh thu theo tháng năm " + String.valueOf(cbbYear.getSelectedItem()), dataset1);
            revenuePieChart.loadChart("Biểu đồ doanh thu năm " + String.valueOf(cbbYear.getSelectedItem()), dataset);
            revenueBarChart.getRenderer().setSeriesPaint(0, new Color(185, 215, 234, 255));
            revenueBarChart.getRenderer().setBarPainter(new StandardBarPainter());
            revenuePieChart.getPlot().setSectionPaint("Đã thu", new Color(118, 159, 205, 255));
            revenuePieChart.getPlot().setSectionPaint("Còn thiếu", new Color(185, 215, 234, 255));
            ovRevenueChartPanel.removeAll();
            ovRevenueChartPanel.updateUI();
            ovRevenueChartPanel.add(revenueBarChart);
            ovRevenueChartPanel.add(revenuePieChart);
            ovRevenueChartPanel.updateUI();
            
            //init data
            lbUsedRoomValue.setText(String.valueOf(rpCtrl.getRoom().getTotalRoomByStatus(1))+"/"+String.valueOf(rpCtrl.getRoom().getTotalRoom()));
            lbWaitingTicketValue.setText(String.valueOf(rpCtrl.getTicket().getTotalTicketByStatus(0)));
            lbActiveTenantValue.setText(String.valueOf(rpCtrl.getTenant().getTotalTenantByStatus(1)));
            lbUnpaidInvoiceValue.setText(String.valueOf(rpCtrl.getInvoice().getTotalInvoiceByStatus(0)));
            lbDueSoonContractValue.setText(String.valueOf(rpCtrl.getContract().getTotalDueSoonContract()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void initRevenueChart() {
        try {
            PieChart revenuePieChart = new PieChart();
            BarChart revenueBarChart = new BarChart();
            ResultSet rsMonthlyRevenue = rpCtrl.getRevenue().getMonthlyRevenueByYear(String.valueOf(cbbYear.getSelectedItem()));
            double sumReceived = rpCtrl.getRevenue().getTotalReceivedByYear(String.valueOf(cbbYear.getSelectedItem()));
            double sumRevenue = rpCtrl.getRevenue().getTotalRevenueByYear(String.valueOf(cbbYear.getSelectedItem()));
            ArrayList<PieChartDataset> dataset = new ArrayList<>();
            ArrayList<BarChartDataset> dataset1 = new ArrayList<>();
            dataset.add(new PieChartDataset("Đã thu", sumReceived));
            dataset.add(new PieChartDataset("Còn thiếu", sumRevenue - sumReceived));
            JLabel lbSumRevenue = new JLabel("Tổng doanh thu: " + nf.format(sumRevenue));
            JLabel lbSumReceived = new JLabel("Đã thu: " + nf.format(sumReceived));
            while (rsMonthlyRevenue.next()) {
                dataset1.add(new BarChartDataset("Tháng " + rsMonthlyRevenue.getString("MONTH"), Double.valueOf(rsMonthlyRevenue.getString("SUM_RENTAL_PRICE")), "Đồng"));
            }
            revenueBarChart.loadChart("Biểu đồ doanh thu theo tháng năm " + String.valueOf(cbbYear.getSelectedItem()), dataset1);
            revenuePieChart.loadChart("Biểu đồ doanh thu năm " + String.valueOf(cbbYear.getSelectedItem()), dataset);
            revenueBarChart.getRenderer().setSeriesPaint(0, new Color(185, 215, 234, 255));
            revenueBarChart.getRenderer().setBarPainter(new StandardBarPainter());
            revenuePieChart.getPlot().setSectionPaint("Đã thu", new Color(118, 159, 205, 255));
            revenuePieChart.getPlot().setSectionPaint("Còn thiếu", new Color(185, 215, 234, 255));
            revenueChartPanel.removeAll();
            revenueInfoPanel.removeAll();
            revenueInfoPanel.updateUI();
            revenueChartPanel.updateUI();
            revenueChartPanel.add(revenueBarChart);
            revenueChartPanel.add(revenuePieChart);
            revenueInfoPanel.add(lbSumRevenue);
            revenueInfoPanel.add(lbSumReceived);
            revenueInfoPanel.updateUI();
            revenueChartPanel.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSearchBar() {
        DefaultComboBoxModel<String> cbbMonthModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> cbbYearModel = new DefaultComboBoxModel<>();
        Date now = new Date();
        for (int i = Year.now().getValue(); i >= 2015; i--) {
            cbbYearModel.addElement(Integer.toString(i));
        }
        cbbYear.setModel(cbbYearModel);
        cbbMonthModel.addElement("Tất cả");
        for (int i = now.getMonth(); i >= 0; i--) {
            cbbMonthModel.addElement(Integer.toString(i + 1));
        }
        cbbMonth.setModel(cbbMonthModel);
    }
    
    public void initRerortView(){
        initSearchBar();
        initOverviewTab();
        initRevenueChart();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JComboBox<String> cbbMonth;
    private javax.swing.JComboBox<String> cbbYear;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JLabel lbActiveTenant;
    private javax.swing.JLabel lbActiveTenantValue;
    private javax.swing.JLabel lbDueSoonContract;
    private javax.swing.JLabel lbDueSoonContractValue;
    private javax.swing.JLabel lbMonth;
    private javax.swing.JLabel lbUnpaidInvoice;
    private javax.swing.JLabel lbUnpaidInvoiceValue;
    private javax.swing.JLabel lbUsedRoom;
    private javax.swing.JLabel lbUsedRoomValue;
    private javax.swing.JLabel lbWaitingTicket;
    private javax.swing.JLabel lbWaitingTicketValue;
    private javax.swing.JPanel ovRevenueChartPanel;
    private javax.swing.JPanel overviewTab;
    private javax.swing.JTabbedPane reportTabPane;
    private javax.swing.JPanel revenueChartPanel;
    private javax.swing.JPanel revenueInfoPanel;
    private javax.swing.JPanel revenueTab;
    private javax.swing.JPanel searchBar;
    // End of variables declaration//GEN-END:variables
}
