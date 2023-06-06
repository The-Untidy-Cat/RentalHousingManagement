package com.theuntidycat.rhm.view.report;

import com.theuntidycat.rhm.controller.ManageInvoiceController;
import com.theuntidycat.rhm.controller.ReportController;
import com.theuntidycat.rhm.model.BarChartDataset;
import com.theuntidycat.rhm.model.PieChartDataset;
import com.theuntidycat.rhm.view.utils.LoadingDialog;
import com.theuntidycat.rhm.view.chart.BarChart;
import com.theuntidycat.rhm.view.chart.PieChart;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
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
    private DefaultTableModel tbDueSoonContractModel;

    /**
     * Creates new form ReportView
     */
    public ReportView() {
//        TestPieChart pieChart = new TestPieChart();
        initComponents();

    }

    public void createDueSoonContractTable() {
        tbDueSoonContractModel = new DefaultTableModel();
        String title[] = {"Mã HĐ", "Ngày BĐ", "Ngày KT", "Giá thuê", "Đặt cọc","Mã khách", "Mã phòng","Trạng thái"};
        tbDueSoonContractModel.setColumnIdentifiers(title);
        tbDueSoonContract.setModel(tbDueSoonContractModel);
        int Money_unpaid;
        try {
            ResultSet rs = rpCtrl.getContract().getDueSoonContractTable();
            tbDueSoonContractModel.setRowCount(0);
            while (rs.next()) {
                Money_unpaid = rs.getInt(4) - rs.getInt(6);
                String unpaid = String.valueOf(Money_unpaid);
                String arr[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), unpaid};
                tbDueSoonContractModel.addRow(arr);
            }
            lbTotalDueSoonContractValue.setText(String.valueOf(rpCtrl.getContract().getTotalDueSoonContract()));
        } catch (SQLException e) {
            System.out.println("Error at ManageInvoice/updateTable");
        }
    }

    public void loadContract() {
        createDueSoonContractTable();
    }

    public void loadRevenue() {
        loading.setVisible(true);
        try {
            PieChart revenuePieChart = new PieChart();
            BarChart revenueBarChart = new BarChart();
            if (String.valueOf(r_cbbMonth.getSelectedItem()) == "Tất cả") {
                ResultSet rsMonthlyRevenue = rpCtrl.getRevenue().getMonthlyRevenueByYear(String.valueOf(r_cbbYear.getSelectedItem()));
                double sumReceived = rpCtrl.getRevenue().getTotalReceivedByYear(String.valueOf(r_cbbYear.getSelectedItem()));
                double sumRevenue = rpCtrl.getRevenue().getTotalRevenueByYear(String.valueOf(r_cbbYear.getSelectedItem()));
                ArrayList<PieChartDataset> dataset = new ArrayList<>();
                ArrayList<BarChartDataset> dataset1 = new ArrayList<>();
                dataset.add(new PieChartDataset("Đã thu", sumReceived));
                dataset.add(new PieChartDataset("Còn thiếu", sumRevenue - sumReceived));
                JLabel lbSumRevenue = new JLabel("Tổng doanh thu: " + nf.format(sumRevenue));
                JLabel lbSumReceived = new JLabel("Đã thu: " + nf.format(sumReceived));

                while (rsMonthlyRevenue.next()) {
                    dataset1.add(new BarChartDataset("Tháng " + rsMonthlyRevenue.getString("MONTH"), Double.valueOf(rsMonthlyRevenue.getString("SUM_RENTAL_PRICE")), "Đồng"));
                }
                revenueBarChart.loadChart("Biểu đồ doanh thu theo tháng năm " + String.valueOf(r_cbbYear.getSelectedItem()), dataset1);
                revenuePieChart.loadChart("Biểu đồ doanh thu năm " + String.valueOf(r_cbbYear.getSelectedItem()), dataset);
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
                double sumReceived = rpCtrl.getRevenue().getTotalReceivedByMonth(String.valueOf(r_cbbMonth.getSelectedItem()), String.valueOf(r_cbbYear.getSelectedItem()));
                double sumRevenue = rpCtrl.getRevenue().getTotalRevenueByMonth(String.valueOf(r_cbbMonth.getSelectedItem()), String.valueOf(r_cbbYear.getSelectedItem()));
                ArrayList<PieChartDataset> dataset = new ArrayList<>();
                dataset.add(new PieChartDataset("Đã thu", sumReceived));
                dataset.add(new PieChartDataset("Còn thiếu", sumRevenue - sumReceived));
                JLabel lbSumRevenue = new JLabel("Tổng doanh thu: " + nf.format(sumRevenue));
                JLabel lbSumReceived = new JLabel("Đã thu: " + nf.format(sumReceived));
                revenuePieChart.loadChart("Biểu đồ doanh thu tháng " + String.valueOf(r_cbbMonth.getSelectedItem()) + " năm " + String.valueOf(r_cbbYear.getSelectedItem()), dataset);
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

    public void loadInvoice() {
        loading.setVisible(true);
        try {
            PieChart invoicePieChart = new PieChart();
            BarChart invoiceBarChart = new BarChart();
            if (String.valueOf(i_cbbMonth.getSelectedItem()) == "Tất cả") {
                ResultSet rsMonthlyInvoice = rpCtrl.getInvoice().getMonthlyInvoiceByYear(String.valueOf(i_cbbYear.getSelectedItem()));
                double sumReceived = rpCtrl.getInvoice().getTotalMoneyPaidInvoiveByYear(String.valueOf(i_cbbYear.getSelectedItem()));
                double sumInvoice = rpCtrl.getInvoice().getTotalMoneyInvoiveByYear(String.valueOf(i_cbbYear.getSelectedItem()));
                ArrayList<PieChartDataset> dataset = new ArrayList<>();
                ArrayList<BarChartDataset> dataset1 = new ArrayList<>();
                dataset.add(new PieChartDataset("Đã thu", sumReceived));
                dataset.add(new PieChartDataset("Còn thiếu", sumInvoice - sumReceived));
                JLabel lbSumInvoice = new JLabel("Tổng: " + nf.format(sumInvoice));
                JLabel lbSumReceived = new JLabel("Đã thu: " + nf.format(sumReceived));

                while (rsMonthlyInvoice.next()) {
                    dataset1.add(new BarChartDataset(rsMonthlyInvoice.getString("MONTH"), Double.valueOf(rsMonthlyInvoice.getString("SUM_MONEY")), "Đồng"));
                }
                invoiceBarChart.loadChart("Biểu đồ thống kê giá trị hoá đơn theo tháng trong năm " + String.valueOf(i_cbbYear.getSelectedItem()), dataset1);
                invoicePieChart.loadChart("Biểu đồ thống kê giá trị hoá đơn năm " + String.valueOf(i_cbbYear.getSelectedItem()), dataset);
                invoiceBarChart.getRenderer().setSeriesPaint(0, new Color(185, 215, 234, 255));
                invoiceBarChart.getRenderer().setBarPainter(new StandardBarPainter());
                invoicePieChart.getPlot().setSectionPaint("Đã thu", new Color(118, 159, 205, 255));
                invoicePieChart.getPlot().setSectionPaint("Còn thiếu", new Color(185, 215, 234, 255));
                invoiceChartPanel.removeAll();
                invoiceInfoPanel.removeAll();
                invoiceInfoPanel.updateUI();
                invoiceChartPanel.updateUI();
                invoiceChartPanel.add(invoiceBarChart);
                invoiceChartPanel.add(invoicePieChart);
                invoiceInfoPanel.add(lbSumInvoice);
                invoiceInfoPanel.add(lbSumReceived);
                invoiceInfoPanel.updateUI();
                invoiceChartPanel.updateUI();
            } else {
                ResultSet rsMonthlyInvoice = rpCtrl.getInvoice().getRoomInvoiceByMonth(String.valueOf(i_cbbMonth.getSelectedItem()), String.valueOf(i_cbbYear.getSelectedItem()));
                double sumReceived = rpCtrl.getInvoice().getTotalMoneyPaidInvoiveByMonth(String.valueOf(i_cbbMonth.getSelectedItem()), String.valueOf(i_cbbYear.getSelectedItem()));
                double sumInvoice = rpCtrl.getInvoice().getTotalMoneyInvoiveByMonth(String.valueOf(i_cbbMonth.getSelectedItem()), String.valueOf(i_cbbYear.getSelectedItem()));
                ArrayList<PieChartDataset> dataset = new ArrayList<>();
                ArrayList<BarChartDataset> dataset1 = new ArrayList<>();
                dataset.add(new PieChartDataset("Đã thu", sumReceived));
                dataset.add(new PieChartDataset("Còn thiếu", sumInvoice - sumReceived));
                JLabel lbSumInvoice = new JLabel("Tổng: " + nf.format(sumInvoice));
                JLabel lbSumReceived = new JLabel("Đã thu: " + nf.format(sumReceived));

                while (rsMonthlyInvoice.next()) {
                    dataset1.add(new BarChartDataset(rsMonthlyInvoice.getString("ROOM_ID"), Double.valueOf(rsMonthlyInvoice.getString("SUM_MONEY")), "Đồng"));
                }
                invoicePieChart.loadChart("Biểu đồ thống kê giá trị hoá đơn tháng " + String.valueOf(i_cbbMonth.getSelectedItem()) + " năm " + String.valueOf(i_cbbYear.getSelectedItem()), dataset);
                invoiceBarChart.loadChart("Biểu đồ thống kê giá trị hoá đơn theo phòng tháng " + String.valueOf(i_cbbMonth.getSelectedItem()) + "/" + String.valueOf(i_cbbYear.getSelectedItem()), dataset1);
                invoiceBarChart.getRenderer().setSeriesPaint(0, new Color(185, 215, 234, 255));
                invoiceBarChart.getRenderer().setBarPainter(new StandardBarPainter());
                invoicePieChart.getPlot().setSectionPaint("Đã thu", new Color(118, 159, 205, 255));
                invoicePieChart.getPlot().setSectionPaint("Còn thiếu", new Color(185, 215, 234, 255));
                invoiceChartPanel.removeAll();
                invoiceInfoPanel.removeAll();
                invoiceInfoPanel.updateUI();
                invoiceChartPanel.updateUI();
                invoiceChartPanel.add(invoiceBarChart);
                invoiceChartPanel.add(invoicePieChart);
                invoiceInfoPanel.add(lbSumInvoice);
                invoiceInfoPanel.add(lbSumReceived);
                invoiceInfoPanel.updateUI();
                invoiceChartPanel.updateUI();
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
        r_searchBar = new javax.swing.JPanel();
        r_lbMonth = new javax.swing.JLabel();
        r_cbbMonth = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        r_lbYear = new javax.swing.JLabel();
        r_cbbYear = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        revenueInfoPanel = new javax.swing.JPanel();
        revenueChartPanel = new javax.swing.JPanel();
        invoiceTab = new javax.swing.JPanel();
        i_searchBar = new javax.swing.JPanel();
        i_lbMonth = new javax.swing.JLabel();
        i_cbbMonth = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        i_lbYear = new javax.swing.JLabel();
        i_cbbYear = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        invoiceInfoPanel = new javax.swing.JPanel();
        invoiceChartPanel = new javax.swing.JPanel();
        contractTab = new javax.swing.JPanel();
        c_headerPanel = new javax.swing.JPanel();
        lbDueSoonContractList = new javax.swing.JLabel();
        btRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDueSoonContract = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbTotalDueSoonContract = new javax.swing.JLabel();
        lbTotalDueSoonContractValue = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(500, 385));

        reportTabPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        overviewTab.setMinimumSize(new java.awt.Dimension(200, 200));

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

        ovRevenueChartPanel.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        javax.swing.GroupLayout overviewTabLayout = new javax.swing.GroupLayout(overviewTab);
        overviewTab.setLayout(overviewTabLayout);
        overviewTabLayout.setHorizontalGroup(
            overviewTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addComponent(ovRevenueChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        overviewTabLayout.setVerticalGroup(
            overviewTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewTabLayout.createSequentialGroup()
                .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ovRevenueChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reportTabPane.addTab("Tổng quan", overviewTab);

        r_searchBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        r_lbMonth.setText("Tháng");
        r_searchBar.add(r_lbMonth);

        r_cbbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        r_cbbMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                r_cbbMonthItemStateChanged(evt);
            }
        });
        r_searchBar.add(r_cbbMonth);
        r_searchBar.add(jSeparator1);

        r_lbYear.setText("Năm");
        r_searchBar.add(r_lbYear);

        r_cbbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        r_cbbYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                r_cbbYearItemStateChanged(evt);
            }
        });
        r_searchBar.add(r_cbbYear);
        r_searchBar.add(jSeparator2);

        revenueInfoPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5));

        revenueChartPanel.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout revenueTabLayout = new javax.swing.GroupLayout(revenueTab);
        revenueTab.setLayout(revenueTabLayout);
        revenueTabLayout.setHorizontalGroup(
            revenueTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(r_searchBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(revenueInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(revenueChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        revenueTabLayout.setVerticalGroup(
            revenueTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenueTabLayout.createSequentialGroup()
                .addComponent(r_searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(revenueChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(revenueInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        reportTabPane.addTab("Doanh thu", revenueTab);

        i_searchBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        i_lbMonth.setText("Tháng");
        i_searchBar.add(i_lbMonth);

        i_cbbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        i_cbbMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                i_cbbMonthItemStateChanged(evt);
            }
        });
        i_searchBar.add(i_cbbMonth);
        i_searchBar.add(jSeparator3);

        i_lbYear.setText("Năm");
        i_searchBar.add(i_lbYear);

        i_cbbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        i_cbbYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                i_cbbYearItemStateChanged(evt);
            }
        });
        i_searchBar.add(i_cbbYear);
        i_searchBar.add(jSeparator4);

        invoiceInfoPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5));

        invoiceChartPanel.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout invoiceTabLayout = new javax.swing.GroupLayout(invoiceTab);
        invoiceTab.setLayout(invoiceTabLayout);
        invoiceTabLayout.setHorizontalGroup(
            invoiceTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(i_searchBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(invoiceInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(invoiceChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        invoiceTabLayout.setVerticalGroup(
            invoiceTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invoiceTabLayout.createSequentialGroup()
                .addComponent(i_searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(invoiceChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invoiceInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        reportTabPane.addTab("Hoá đơn", invoiceTab);

        contractTab.setLayout(new java.awt.BorderLayout());

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10);
        flowLayout2.setAlignOnBaseline(true);
        c_headerPanel.setLayout(flowLayout2);

        lbDueSoonContractList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDueSoonContractList.setText("Danh sách hợp đồng sắp đến hạn");
        c_headerPanel.add(lbDueSoonContractList);

        btRefresh.setText("Làm mới");
        btRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshActionPerformed(evt);
            }
        });
        c_headerPanel.add(btRefresh);

        contractTab.add(c_headerPanel, java.awt.BorderLayout.PAGE_START);

        tbDueSoonContract.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbDueSoonContract);

        contractTab.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        lbTotalDueSoonContract.setText("Tổng:");
        jPanel1.add(lbTotalDueSoonContract);

        lbTotalDueSoonContractValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTotalDueSoonContractValue.setText("0");
        jPanel1.add(lbTotalDueSoonContractValue);

        contractTab.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        reportTabPane.addTab("Hợp đồng", contractTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reportTabPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reportTabPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 434, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void r_cbbYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_r_cbbYearItemStateChanged
        DefaultComboBoxModel<String> cbbMonthModel = new DefaultComboBoxModel<>();

        Date now = new Date();
        cbbMonthModel.addElement("Tất cả");
        for (int i = String.valueOf(r_cbbYear.getSelectedItem()).equals(Integer.toString(Year.now().getValue())) ? now.getMonth() : 11; i >= 0; i--) {
            cbbMonthModel.addElement(Integer.toString(i + 1));
        }
        r_cbbMonth.setModel(cbbMonthModel);
        loadRevenue();
    }//GEN-LAST:event_r_cbbYearItemStateChanged

    private void r_cbbMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_r_cbbMonthItemStateChanged
        loadRevenue();
    }//GEN-LAST:event_r_cbbMonthItemStateChanged

    private void i_cbbMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_i_cbbMonthItemStateChanged
        loadInvoice();
    }//GEN-LAST:event_i_cbbMonthItemStateChanged

    private void i_cbbYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_i_cbbYearItemStateChanged
        DefaultComboBoxModel<String> cbbMonthModel = new DefaultComboBoxModel<>();

        Date now = new Date();
        cbbMonthModel.addElement("Tất cả");
        for (int i = String.valueOf(i_cbbYear.getSelectedItem()).equals(Integer.toString(Year.now().getValue())) ? now.getMonth() : 11; i >= 0; i--) {
            cbbMonthModel.addElement(Integer.toString(i + 1));
        }
        i_cbbMonth.setModel(cbbMonthModel);
        loadInvoice();
    }//GEN-LAST:event_i_cbbYearItemStateChanged

    private void btRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshActionPerformed
        loadContract();
    }//GEN-LAST:event_btRefreshActionPerformed

    public void initOverviewTab() {
        try {
            // init chart
            PieChart revenuePieChart = new PieChart();
            BarChart revenueBarChart = new BarChart();
            ResultSet rsMonthlyRevenue = rpCtrl.getRevenue().getMonthlyRevenueByYear(String.valueOf(r_cbbYear.getSelectedItem()));
            double sumReceived = rpCtrl.getRevenue().getTotalReceivedByYear(String.valueOf(r_cbbYear.getSelectedItem()));
            double sumRevenue = rpCtrl.getRevenue().getTotalRevenueByYear(String.valueOf(r_cbbYear.getSelectedItem()));
            ArrayList<PieChartDataset> dataset = new ArrayList<>();
            ArrayList<BarChartDataset> dataset1 = new ArrayList<>();
            dataset.add(new PieChartDataset("Đã thu", sumReceived));
            dataset.add(new PieChartDataset("Còn thiếu", sumRevenue - sumReceived));
            while (rsMonthlyRevenue.next()) {
                dataset1.add(new BarChartDataset("Tháng " + rsMonthlyRevenue.getString("MONTH"), Double.valueOf(rsMonthlyRevenue.getString("SUM_RENTAL_PRICE")), "Đồng"));
            }
            revenueBarChart.loadChart("Biểu đồ doanh thu theo tháng năm " + String.valueOf(r_cbbYear.getSelectedItem()), dataset1);
            revenuePieChart.loadChart("Biểu đồ doanh thu năm " + String.valueOf(r_cbbYear.getSelectedItem()), dataset);
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
            lbUsedRoomValue.setText(String.valueOf(rpCtrl.getRoom().getTotalRoomByStatus(2)) + "/" + String.valueOf(rpCtrl.getRoom().getTotalRoom()));
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
            ResultSet rsMonthlyRevenue = rpCtrl.getRevenue().getMonthlyRevenueByYear(String.valueOf(r_cbbYear.getSelectedItem()));
            double sumReceived = rpCtrl.getRevenue().getTotalReceivedByYear(String.valueOf(r_cbbYear.getSelectedItem()));
            double sumRevenue = rpCtrl.getRevenue().getTotalRevenueByYear(String.valueOf(r_cbbYear.getSelectedItem()));
            ArrayList<PieChartDataset> dataset = new ArrayList<>();
            ArrayList<BarChartDataset> dataset1 = new ArrayList<>();
            dataset.add(new PieChartDataset("Đã thu", sumReceived));
            dataset.add(new PieChartDataset("Còn thiếu", sumRevenue - sumReceived));
            JLabel lbSumRevenue = new JLabel("Tổng doanh thu: " + nf.format(sumRevenue));
            JLabel lbSumReceived = new JLabel("Đã thu: " + nf.format(sumReceived));
            while (rsMonthlyRevenue.next()) {
                dataset1.add(new BarChartDataset("Tháng " + rsMonthlyRevenue.getString("MONTH"), Double.valueOf(rsMonthlyRevenue.getString("SUM_RENTAL_PRICE")), "Đồng"));
            }
            revenueBarChart.loadChart("Biểu đồ doanh thu theo tháng năm " + String.valueOf(r_cbbYear.getSelectedItem()), dataset1);
            revenuePieChart.loadChart("Biểu đồ doanh thu năm " + String.valueOf(r_cbbYear.getSelectedItem()), dataset);
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

    public void initInvoiceChart() {
        Date now = new Date();
        try {
            PieChart invoicePieChart = new PieChart();
            BarChart invoiceBarChart = new BarChart();
            ResultSet rsMonthlyInvoice = rpCtrl.getInvoice().getRoomInvoiceByMonth(String.valueOf(i_cbbMonth.getSelectedItem()), String.valueOf(i_cbbYear.getSelectedItem()));
                double sumReceived = rpCtrl.getInvoice().getTotalMoneyPaidInvoiveByMonth(String.valueOf(i_cbbMonth.getSelectedItem()), String.valueOf(i_cbbYear.getSelectedItem()));
                double sumInvoice = rpCtrl.getInvoice().getTotalMoneyInvoiveByMonth(String.valueOf(i_cbbMonth.getSelectedItem()), String.valueOf(i_cbbYear.getSelectedItem()));
            ArrayList<PieChartDataset> dataset = new ArrayList<>();
            ArrayList<BarChartDataset> dataset1 = new ArrayList<>();
            dataset.add(new PieChartDataset("Đã thu", sumReceived));
            dataset.add(new PieChartDataset("Còn thiếu", sumInvoice - sumReceived));
            JLabel lbSumInvoice = new JLabel("Tổng: " + nf.format(sumInvoice));
            JLabel lbSumReceived = new JLabel("Đã thu: " + nf.format(sumReceived));

            while (rsMonthlyInvoice.next()) {
                dataset1.add(new BarChartDataset(rsMonthlyInvoice.getString("ROOM_ID"), Double.valueOf(rsMonthlyInvoice.getString("SUM_MONEY")), "Đồng"));
            }
            invoicePieChart.loadChart("Biểu đồ thống kê giá trị hoá đơn tháng " + String.valueOf(i_cbbMonth.getSelectedItem()) + " năm " + String.valueOf(i_cbbYear.getSelectedItem()), dataset);
            invoiceBarChart.loadChart("Biểu đồ thống kê giá trị hoá đơn theo phòng tháng " + String.valueOf(i_cbbMonth.getSelectedItem()) + "/" + String.valueOf(i_cbbYear.getSelectedItem()), dataset1);
            invoiceBarChart.getRenderer().setSeriesPaint(0, new Color(185, 215, 234, 255));
            invoiceBarChart.getRenderer().setBarPainter(new StandardBarPainter());
            invoicePieChart.getPlot().setSectionPaint("Đã thu", new Color(118, 159, 205, 255));
            invoicePieChart.getPlot().setSectionPaint("Còn thiếu", new Color(185, 215, 234, 255));
            invoiceChartPanel.removeAll();
            invoiceInfoPanel.removeAll();
            invoiceInfoPanel.updateUI();
            invoiceChartPanel.updateUI();
            invoiceChartPanel.add(invoiceBarChart);
            invoiceChartPanel.add(invoicePieChart);
            invoiceInfoPanel.add(lbSumInvoice);
            invoiceInfoPanel.add(lbSumReceived);
            invoiceInfoPanel.updateUI();
            invoiceChartPanel.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initRevenueSearchBar() {
        DefaultComboBoxModel<String> cbbMonthModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> cbbYearModel = new DefaultComboBoxModel<>();
        Date now = new Date();
        for (int i = Year.now().getValue(); i >= 2015; i--) {
            cbbYearModel.addElement(Integer.toString(i));
        }
        r_cbbYear.setModel(cbbYearModel);
        cbbMonthModel.addElement("Tất cả");
        for (int i = now.getMonth(); i >= 0; i--) {
            cbbMonthModel.addElement(Integer.toString(i + 1));
        }
        r_cbbMonth.setModel(cbbMonthModel);
    }
    
    public void initInvoiceSearchBar() {
        DefaultComboBoxModel<String> cbbMonthModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> cbbYearModel = new DefaultComboBoxModel<>();
        Date now = new Date();
        for (int i = Year.now().getValue(); i >= 2015; i--) {
            cbbYearModel.addElement(Integer.toString(i));
        }
        i_cbbYear.setModel(cbbYearModel);
        
        for (int i = now.getMonth(); i >= 0; i--) {
            cbbMonthModel.addElement(Integer.toString(i + 1));
        }
        cbbMonthModel.addElement("Tất cả");
        i_cbbMonth.setModel(cbbMonthModel);
    }

    public void initRerortView() {
        initRevenueSearchBar();
        initInvoiceSearchBar();
        initOverviewTab();
        initRevenueChart();
        initInvoiceChart();
        loadContract();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btRefresh;
    private javax.swing.JPanel c_headerPanel;
    private javax.swing.JPanel contractTab;
    private javax.swing.JComboBox<String> i_cbbMonth;
    private javax.swing.JComboBox<String> i_cbbYear;
    private javax.swing.JLabel i_lbMonth;
    private javax.swing.JLabel i_lbYear;
    private javax.swing.JPanel i_searchBar;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JPanel invoiceChartPanel;
    private javax.swing.JPanel invoiceInfoPanel;
    private javax.swing.JPanel invoiceTab;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JLabel lbActiveTenant;
    private javax.swing.JLabel lbActiveTenantValue;
    private javax.swing.JLabel lbDueSoonContract;
    private javax.swing.JLabel lbDueSoonContractList;
    private javax.swing.JLabel lbDueSoonContractValue;
    private javax.swing.JLabel lbTotalDueSoonContract;
    private javax.swing.JLabel lbTotalDueSoonContractValue;
    private javax.swing.JLabel lbUnpaidInvoice;
    private javax.swing.JLabel lbUnpaidInvoiceValue;
    private javax.swing.JLabel lbUsedRoom;
    private javax.swing.JLabel lbUsedRoomValue;
    private javax.swing.JLabel lbWaitingTicket;
    private javax.swing.JLabel lbWaitingTicketValue;
    private javax.swing.JPanel ovRevenueChartPanel;
    private javax.swing.JPanel overviewTab;
    private javax.swing.JComboBox<String> r_cbbMonth;
    private javax.swing.JComboBox<String> r_cbbYear;
    private javax.swing.JLabel r_lbMonth;
    private javax.swing.JLabel r_lbYear;
    private javax.swing.JPanel r_searchBar;
    private javax.swing.JTabbedPane reportTabPane;
    private javax.swing.JPanel revenueChartPanel;
    private javax.swing.JPanel revenueInfoPanel;
    private javax.swing.JPanel revenueTab;
    private javax.swing.JTable tbDueSoonContract;
    // End of variables declaration//GEN-END:variables
}
