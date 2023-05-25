/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.theuntidycat.rhm.view;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class ManageContract extends javax.swing.JPanel {
    DefaultTableModel tblModelTT;

    /**
     * Creates new form NewJPanel
     */
    public ManageContract() {
        initComponents();
    }

    public void createTable() {
        tblModelTT = new DefaultTableModel();
        String title[] = { "Mã HĐ", "Tên khách", "SĐT", "Mã phòng", "Tên phòng", "Ngày BĐ", "Ngày KT", "Trạng thái" };
        tblModelTT.setColumnIdentifiers(title);
        tbContract.setModel(tblModelTT);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContractTable = new javax.swing.JPanel();
        ContractButton = new javax.swing.JPanel();
        viewContractDetail = new javax.swing.JButton();
        insertNewContract = new javax.swing.JButton();
        updateContract = new javax.swing.JButton();

        ContractTable.setPreferredSize(new java.awt.Dimension(620, 340));

        tbContract.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
                }));
        jScrollPane2.setViewportView(tbContract);

        javax.swing.GroupLayout ContractTableLayout = new javax.swing.GroupLayout(ContractTable);
        ContractTable.setLayout(ContractTableLayout);
        ContractTableLayout.setHorizontalGroup(
                ContractTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ContractTableLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2)
                                .addContainerGap()));
        ContractTableLayout.setVerticalGroup(
                ContractTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ContractTableLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ContractButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 30, Short.MAX_VALUE)));

        ContractButton.setPreferredSize(new java.awt.Dimension(504, 67));

        viewContractDetail.setText("Xem chi tiết");
        viewContractDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewContractDetailActionPerformed(evt);
            }
        });
        ContractButton.add(viewContractDetail);

        insertNewContract.setText("Thêm");
        insertNewContract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertNewContractActionPerformed(evt);
            }
        });
        ContractButton.add(insertNewContract);

        updateContract.setText("Sửa");
        updateContract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateContractActionPerformed(evt);
            }
        });
        ContractButton.add(updateContract);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ContractTable, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ContractTable, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }// </editor-fold>

    private void viewContractDetailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_viewContractDetailActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_viewContractDetailActionPerformed

    private void insertNewContractActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_insertNewContractActionPerformed
        // TODO add your handling code here:
        ContractInsertDialog contractInsertDialog = new ContractInsertDialog();
        contractInsertDialog.setVisible(true);
    }// GEN-LAST:event_insertNewContractActionPerformed

    private void updateContractActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_updateContractActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_updateContractActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContractButton;
    private javax.swing.JPanel ContractTable;
    private javax.swing.JButton insertNewContract;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbContract;
    private javax.swing.JButton updateContract;
    private javax.swing.JButton viewContractDetail;
    // End of variables declaration//GEN-END:variables
}
