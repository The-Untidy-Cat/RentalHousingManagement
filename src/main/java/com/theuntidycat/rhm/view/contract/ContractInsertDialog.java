/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.theuntidycat.rhm.view.contract;

import com.theuntidycat.rhm.controller.ManageContractController;
import com.theuntidycat.rhm.database.Oracle;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author minhngoc
 */
public class ContractInsertDialog extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public ContractInsertDialog() {
        initComponents();
        txtRoomID.setEditable(false);
        Date date = new Date();
        dateStartDate.setDate(date);
        loadCBBRoomName();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    ArrayList<String> listRoomID = new ArrayList<String>();
    ArrayList<String> listRoomName = new ArrayList<String>();
    public void loadCBBRoomName()
    {
        try
        {
            ManageContractController controller = new ManageContractController();
            ResultSet rs = controller.getCBBRoom();
     
            while(rs.next())
            {
                listRoomID.add(rs.getString("ID"));
                listRoomName.add(rs.getString("NAME"));
            }
            
            cbbRoomName.setModel(new DefaultComboBoxModel<String>(listRoomName.toArray(new String[0])));
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("Error at ContractInsertDialog loadCBBRoom");
        }
    }
    
    public void checkDate()
    {
        if(dateStartDate.getDate().compareTo(dateEndDate.getDate()) > 0)
        {
            JOptionPane.showMessageDialog(null, "Contract end Date must after Contract start date", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateStartDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dateEndDate = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDeposit = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenantID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbbRoomName = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtRoomID = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        contractCancelButton = new javax.swing.JButton();
        contractSubmitButton = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Contract Insert Dialog");
        setAlwaysOnTop(true);

        jPanel2.setLayout(new java.awt.GridLayout(7, 2, 10, 10));

        jLabel1.setText("Ngày bắt đầu");
        jPanel2.add(jLabel1);
        jPanel2.add(dateStartDate);

        jLabel2.setText("Ngày kết thúc");
        jPanel2.add(jLabel2);
        jPanel2.add(dateEndDate);

        jLabel6.setText("Giá thuê hàng tháng");
        jPanel2.add(jLabel6);

        txtPrice.setPreferredSize(new java.awt.Dimension(70, 30));
        jPanel2.add(txtPrice);

        jLabel9.setText("Tiền đặt cọc");
        jLabel9.setPreferredSize(new java.awt.Dimension(30, 16));
        jPanel2.add(jLabel9);

        txtDeposit.setPreferredSize(new java.awt.Dimension(70, 30));
        jPanel2.add(txtDeposit);

        jLabel5.setText("Mã khách thuê");
        jPanel2.add(jLabel5);
        jPanel2.add(txtTenantID);

        jLabel3.setText("Tên Phòng");
        jPanel2.add(jLabel3);

        cbbRoomName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbRoomName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbRoomNameActionPerformed(evt);
            }
        });
        jPanel2.add(cbbRoomName);

        jLabel4.setText("Mã Phòng");
        jPanel2.add(jLabel4);

        txtRoomID.setPreferredSize(new java.awt.Dimension(70, 30));
        jPanel2.add(txtRoomID);

        contractCancelButton.setText("Cancel");
        contractCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contractCancelButtonActionPerformed(evt);
            }
        });
        jPanel3.add(contractCancelButton);

        contractSubmitButton.setText("Submit");
        contractSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contractSubmitButtonActionPerformed(evt);
            }
        });
        jPanel3.add(contractSubmitButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void contractCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contractCancelButtonActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Ban co muon huy?", "Thong bao", JOptionPane.YES_NO_OPTION);
        if(ret == JOptionPane.YES_OPTION)
            setVisible(false);
    }//GEN-LAST:event_contractCancelButtonActionPerformed

    private void contractSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contractSubmitButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            ManageContractController controller = new ManageContractController();
            checkDate();
            DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
            String start_date = df.format(dateStartDate.getDate());
            String end_date = df.format(dateEndDate.getDate());
            float price = Float.parseFloat(txtPrice.getText());
            float deposit = Float.parseFloat(txtDeposit.getText());
            controller.insertContract(start_date, end_date, price, deposit, txtTenantID.getText(), txtRoomID.getText());
            JOptionPane.showMessageDialog(this, "Insert new contract successfully");
        }
        catch(Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Cannot Submit");
        }
        
        
    }//GEN-LAST:event_contractSubmitButtonActionPerformed

    private void cbbRoomNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbRoomNameActionPerformed
        // TODO add your handling code here:
        try
        {
            ManageContractController controller = new ManageContractController();
            ResultSet rs = controller.cbbRoomIDAction(cbbRoomName.getSelectedItem().toString());
            
            rs.next();
            txtRoomID.setText(rs.getString("ID"));
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("Error in ContractInsertDialog cbbRoomNameAction");
        }
    }//GEN-LAST:event_cbbRoomNameActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbRoomName;
    private javax.swing.JButton contractCancelButton;
    private javax.swing.JButton contractSubmitButton;
    private com.toedter.calendar.JDateChooser dateEndDate;
    private com.toedter.calendar.JDateChooser dateStartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtDeposit;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRoomID;
    private javax.swing.JTextField txtTenantID;
    // End of variables declaration//GEN-END:variables
}