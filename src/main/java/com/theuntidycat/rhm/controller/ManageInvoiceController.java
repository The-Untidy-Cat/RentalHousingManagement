/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.controller;

import com.theuntidycat.rhm.database.Oracle;
import javax.mail.Authenticator;

import java.sql.*;


import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

import java.util.Date;
import java.util.Properties;

/**
 *
 * @author TTMC
 */
public class ManageInvoiceController {

    private final Oracle database = new Oracle();
    private final Connection conn = database.getConnection();

    public ResultSet getInvoices() {
        ResultSet rs = null;
        try {
            String sql = "SELECT INV.ID, MONTH || '/' || YEAR, R.NAME, INV.TOTAL_MONEY, S.NAME, MONEY_PAID FROM INVOICE INV, INVOICE_STATUS S, ROOM R WHERE INV.STATUS_ID = S.ID AND INV.ROOM_ID = R.ID ORDER BY INV.ID DESC";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error at ManageInvoiceController/getInvoices");
        }
        return rs;
    }

    public String getRoomID(String name) {
        String id = null;
        try {
            String getID = "SELECT ID FROM ROOM WHERE NAME = ?";
            PreparedStatement psta = conn.prepareStatement(getID);
            psta.setString(1, name);
            ResultSet rs1 = psta.executeQuery();
            if (rs1.next()) {
                id = rs1.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at ManageInvoiceController/filterInvoices/getRoomID\nError: " + e);
        }
        return id;
    }

    public ResultSet filterInvoices(String room, String per) {
        String id = null;
        if (!room.equals("Tất cả")) {
            id = getRoomID(room);
        }
        String[] ky = null;
        if (!per.equals("Tất cả")) {
            ky = per.split("/");
        }

        ResultSet rs = null;
        try {
            if (!room.equals("Tất cả") && per.equals("Tất cả")) {
                String sql = "SELECT INV.ID, MONTH || '/' || YEAR, R.NAME, INV.TOTAL_MONEY, S.NAME FROM INVOICE INV, INVOICE_STATUS S, ROOM R WHERE INV.STATUS_ID = S.ID AND INV.ROOM_ID = R.ID AND ROOM_ID =? ORDER BY INV.ID DESC";
                PreparedStatement pstat = conn.prepareStatement(sql);
                pstat.setString(1, id);
                rs = pstat.executeQuery();
            } else if (room.equals("Tất cả") && !per.equals("Tất cả")) {
                String sql = "SELECT INV.ID, MONTH || '/' || YEAR, R.NAME, INV.TOTAL_MONEY, S.NAME FROM INVOICE INV, INVOICE_STATUS S, ROOM R WHERE INV.STATUS_ID = S.ID AND INV.ROOM_ID = R.ID AND INV.MONTH = ? AND INV.YEAR = ? ORDER BY INV.ID DESC";
                PreparedStatement pstat = conn.prepareStatement(sql);
                pstat.setString(1, ky[0]);
                pstat.setString(2, ky[1]);
                rs = pstat.executeQuery();
            } else {
                String sql = "SELECT INV.ID, MONTH || '/' || YEAR, R.NAME, INV.TOTAL_MONEY, S.NAME FROM INVOICE INV, INVOICE_STATUS S, ROOM R WHERE INV.STATUS_ID = S.ID AND INV.ROOM_ID = R.ID AND ROOM_ID = ? AND INV.MONTH = ? AND INV.YEAR = ? ORDER BY INV.ID DESC";
                PreparedStatement pstat = conn.prepareStatement(sql);
                pstat.setString(1, id);
                pstat.setString(2, ky[0]);
                pstat.setString(3, ky[1]);
                rs = pstat.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("Error at ManageInvoiceController/filterInvoices");
        }
        return rs;
    }

    public boolean insertInvoice(String name, String mm, String yy)  {
        String id = getRoomID(name);
        try {
            String SQLins = "INSERT INTO INVOICE(ROOM_ID, MONTH, YEAR) VALUES (?,?,?)";
            PreparedStatement pIns = conn.prepareStatement(SQLins);
            pIns.setString(1, id);
            pIns.setString(2, mm);
            pIns.setString(3, yy);
            int n = pIns.executeUpdate();
            String sql="SELECT tenant.email FROM invoice JOIN contract ON invoice.room_id = contract.room_id JOIN DETAIL_contract ON contract.id = DETAIL_CONTRACT.contract_id JOIN tenant ON tenant.id = contract.tenant_id WHERE INVOICE.ROOM_ID = ? and (invoice.year = ? and invoice.month = ?) and invoice.month between extract(MONTH FROM contract.start_date) and EXTRACT(MONTH FROM contract.end_date) and invoice.YEAR BETWEEN extract(YEAR FROM contract.start_date) and EXTRACT(YEAR FROM contract.end_date)";
            pIns = conn.prepareStatement(sql);
            pIns.setString(1, id);
            pIns.setString(2, yy);
            pIns.setString(3, mm);
            
            ResultSet rs = pIns.executeQuery();
            SendEmail sendEmail = new SendEmail();
            while(rs.next()){
                System.out.println(rs.getString(1));
                sendEmail.notificationInvoice(rs.getString(1), "Thông báo hoá đơn kỳ tháng "+ mm + "/"+yy, "Đã có thông tin hoá đơn tháng "+ mm + "/"+yy + ". Quý khách vui lòng mở app để xem chi tiết. Xin cảm ơn!");
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error at InvoiceController/insertInvoice\nError is: " + e);
            return false;
        }   
    }

    public boolean deleteInvoice(String id) {
        try {
            String deSQL = "DELETE FROM INVOICE WHERE ID = ?";
            PreparedStatement pStat = conn.prepareStatement(deSQL);
            pStat.setString(1, id);
            int n = pStat.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error at InvoiceController/deleteInvoice\nError is: " + e);
            return false;
        }
    }

    public boolean updateInvoice(String name, String mm, String yy, String id, String S_id) {
        String r_id = getRoomID(name);
        try {
            String upSQL = "UPDATE INVOICE SET ROOM_ID = ?, MONTH = ?, YEAR = ?, STATUS_ID = ? WHERE ID = ?";
            PreparedStatement pStat = conn.prepareStatement(upSQL);
            pStat.setString(1, r_id);
            pStat.setString(2, mm);
            pStat.setString(3, yy);
            pStat.setString(4, S_id);
            pStat.setString(5, id);
            int n = pStat.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error at InvoiceController/updateInvoice\nError is: " + e);
            return false;
        }
    }

    public ResultSet getRooms() {
        ResultSet rs = null;
        try {
            String sql = "SELECT DISTINCT R.NAME FROM INVOICE INV join ROOM R on INV.ROOM_ID = R.ID";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error at ManageInvoiceController/getRooms\n" + e);
        }
        return rs;
    }

    public ResultSet getPeriod() {
        ResultSet rs = null;
        try {
            String sql = "SELECT DISTINCT MONTH || '/' || YEAR as period FROM INVOICE order by period";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error at ManageInvoiceController/getPeriod\n" + e);
        }
        return rs;
    }

    public ResultSet getRooms_Contract() {
        ResultSet rs = null;
        try {
            String sql = "SELECT DISTINCT R.NAME FROM CONTRACT CO join ROOM R on CO.ROOM_ID = R.ID WHERE CO.STATUS_ID = 1";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error at ManageInvoiceController/getRooms\n" + e);
        }
        return rs;
    }

    public ResultSet getDetailInvoices(String id) {
        ResultSet rs = null;
        try {
            String sql = "SELECT T.NAME, QUANTITY, UNIT_PRICE, T.UNIT, SUM_MONEY FROM DETAIL_INVOICE D JOIN DETAIL_INVOICE_TYPE T ON D.TYPE_ID = T.ID WHERE D.INVOICE_ID = ? ORDER BY D.TYPE_ID";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, id);
            rs = stat.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error at ManageInvoiceController/getDetialInvoices");
        }
        return rs;
    }

    public ResultSet getTypeInv() {
        ResultSet rs = null;
        try {
            String sql = "SELECT NAME FROM DETAIL_INVOICE_TYPE";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error at ManageInvoiceController/getRooms\n" + e);
        }
        return rs;
    }

    public String getTypeID(String name) {
        String id = null;
        try {
            String getID = "SELECT ID FROM DETAIL_INVOICE_TYPE WHERE NAME = ?";
            PreparedStatement psta = conn.prepareStatement(getID);
            psta.setString(1, name);
            ResultSet rs1 = psta.executeQuery();
            if (rs1.next()) {
                id = rs1.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at ManageInvoiceController/getTypeID\nError: " + e);
        }
        return id;
    }

    public boolean InsertDetailInv(String id, String loai, String sl, String dg) {
        String type = getTypeID(loai);
        try {
            String SQLins = "INSERT INTO DETAIL_INVOICE(INVOICE_ID, TYPE_ID, QUANTITY, UNIT_PRICE) VALUES (?,?,?,?)";
            PreparedStatement pIns = conn.prepareStatement(SQLins);
            pIns.setString(1, id);
            pIns.setString(2, type);
            pIns.setString(3, sl);
            pIns.setString(4, dg);
            int n = pIns.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error at InvoiceController/insertInvoice\nError is: " + e);
            return false;
        }
    }

    public boolean updateDetail(String id, String loai, String sl, String dg) {
        String t_id = getTypeID(loai);
        try {
            String upSQL = "UPDATE DETAIL_INVOICE SET QUANTITY = ?, UNIT_PRICE = ? WHERE INVOICE_ID = ? AND TYPE_ID = ?";
            PreparedStatement pStat = conn.prepareStatement(upSQL);
            pStat.setString(1, sl);
            pStat.setString(2, dg);
            pStat.setString(3, id);
            pStat.setString(4, t_id);

            int n = pStat.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error at InvoiceController/updateDetailInvoice\nError is: " + e);
            return false;
        }
    }

    public boolean deleteDetailInvoice(String id, String loai) {
        String t_id = getTypeID(loai);
        try {
            String deSQL = "DELETE FROM DETAIL_INVOICE WHERE INVOICE_ID = ? AND TYPE_ID = ?";
            PreparedStatement pStat = conn.prepareStatement(deSQL);
            pStat.setString(1, id);
            pStat.setString(2, t_id);
            int n = pStat.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error at InvoiceController/deleteDetailInvoice\nError is: " + e);
            return false;
        }
    }
  }
