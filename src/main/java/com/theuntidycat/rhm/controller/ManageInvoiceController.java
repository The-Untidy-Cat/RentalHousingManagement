/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.controller;

import com.theuntidycat.rhm.database.Oracle;
import java.sql.*;

/**
 *
 * @author TTMC
 */
public class ManageInvoiceController {
    private final Oracle database = new Oracle();
    private final Connection conn = database.getConnection();
    
    public ResultSet getInvoices(){
        ResultSet rs = null;
        try{
            String sql = "SELECT INV.ID, MONTH || '/' || YEAR, R.NAME, INV.TOTAL_MONEY, S.NAME FROM INVOICE INV, INVOICE_STATUS S, ROOM R WHERE INV.STATUS_ID = S.ID AND INV.ROOM_ID = R.ID ORDER BY INV.ID DESC";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch(SQLException e){
            System.out.println("Error at ManageInvoiceController/getInvoices");
        }
        return rs;
    }
    
    public boolean insertInvoice(String name, String mm, String yy){
        try{
            String id = null;
            String getID = "SELECT ID FROM ROOM WHERE NAME = ?";
            PreparedStatement psta = conn.prepareStatement(getID);
            psta.setString(1,name);
            ResultSet rs = psta.executeQuery();
            if(rs.next()){
                id = rs.getString(1);
            }
            
            String SQLins = "INSERT INTO INVOICE(ROOM_ID, MONTH, YEAR) VALUES (?,?,?)";
            PreparedStatement pIns = conn.prepareStatement(SQLins);
            pIns.setString(1, id);
            pIns.setString(2, mm);
            pIns.setString(3, yy);
            int n = pIns.executeUpdate();
            return true;
            
        } catch(SQLException e){
            System.out.println("Error at InvoiceController/insertInvoice\nError is: " + e);
            return false;
        }        
    }   
    
    public boolean deleteInvoice(String id){
        try{
            String deSQL = "DELETE FROM INVOICE WHERE ID = ?";
            PreparedStatement pStat = conn.prepareStatement(deSQL);
            pStat.setString(1,id);
            int n = pStat.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Error at InvoiceController/deleteInvoice\nError is: " + e);
            return false;
        }
    }
    
    public boolean updateInvoice(String name, String mm, String yy, String id){
        try{
            String r_id = null;
            String getRoomid = "SELECT ID FROM ROOM WHERE NAME = ?";
            PreparedStatement pstat = conn.prepareStatement(getRoomid);
            pstat.setString(1, name);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                r_id = rs.getString(1);
            }
            
            String upSQL = "UPDATE INVOICE SET ROOM_ID = ?, MONTH = ?, YEAR = ? WHERE ID = ?";
            PreparedStatement pStat = conn.prepareStatement(upSQL);
            pStat.setString(1,r_id);
            pStat.setString(2,mm);
            pStat.setString(3,yy);
            pStat.setString(4,id);
            int n = pStat.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Error at InvoiceController/updateInvoice\nError is: " + e);
            return false;
        }
    }
    
    public ResultSet getRooms(){
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT R.NAME FROM INVOICE INV join ROOM R on INV.ROOM_ID = R.ID";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch(SQLException e){
            System.out.println("Error at ManageInvoiceController/getRooms\n" + e);
        }
        return rs;
    }
    
    public ResultSet getPeriod(){
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT MONTH || '/' || YEAR as period FROM INVOICE order by period";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch(SQLException e){
            System.out.println("Error at ManageInvoiceController/getPeriod\n" + e);
        }
        return rs;
    }
    
    public ResultSet getRooms_Contract(){
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT R.NAME FROM CONTRACT CO join ROOM R on CO.ROOM_ID = R.ID AND CO.STATUS_ID = 1";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch(SQLException e){
            System.out.println("Error at ManageInvoiceController/getRooms\n" + e);
        }
        return rs;
    }
    
}
