/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.view.utils;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author SHeroAnh
 */
public class LoadingDialog {

    private JDialog loadingDialog;

    public LoadingDialog() {
        loadingDialog = new JDialog();
        loadingDialog.setLayout(new GridBagLayout());
        loadingDialog.add(new JLabel("Vui lòng chờ..."));
        loadingDialog.setMinimumSize(new Dimension(150, 50));
        loadingDialog.setResizable(false);
        loadingDialog.setModal(false);
        loadingDialog.setUndecorated(true);
        loadingDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        loadingDialog.setLocationRelativeTo(null);
    }

    public void setVisible(boolean status) {
        loadingDialog.setVisible(status);
    }
}
