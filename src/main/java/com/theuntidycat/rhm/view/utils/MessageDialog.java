/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.view.utils;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author SHeroAnh
 * @param <T>
 * @param <JFrame>
 */
public class MessageDialog<T extends JPanel, JFrame> {

    private JOptionPane messagePane;
    private JDialog messageDialog;

    public MessageDialog() {
        messagePane = new JOptionPane();
        messageDialog = messagePane.createDialog(null, null);
    }

    public MessageDialog(T parent, String title, String message, int messageType, int optionType) {
        messagePane = new JOptionPane(message, messageType, optionType);
        messageDialog = messagePane.createDialog(parent, title);
    }

    public void setVisible(boolean status) {
        messageDialog.setVisible(status);
    }

    public JOptionPane getPane() {
        return messagePane;
    }

    public void createDiaglog(T parent, String title) {
        messageDialog = messagePane.createDialog(parent, title);
    }

    public int getSelection() {
        int returnValue = JOptionPane.CLOSED_OPTION;

        Object selectedValue = messagePane.getValue();
        if (selectedValue != null) {
            Object options[] = messagePane.getOptions();
            if (options == null) {
                if (selectedValue instanceof Integer) {
                    returnValue = ((Integer) selectedValue).intValue();
                }
            } else {
                for (int i = 0, n = options.length; i < n; i++) {
                    if (options[i].equals(selectedValue)) {
                        returnValue = i;
                        break; // out of for loop
                    }
                }
            }
        }
        return returnValue;
    }
}
