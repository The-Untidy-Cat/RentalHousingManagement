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
            String sql = "SELECT INV.ID, MONTH || '/' || YEAR, R.NAME, INV.TOTAL_MONEY, S.NAME FROM INVOICE INV, INVOICE_STATUS S, CONTRACT CO, ROOM R WHERE INV.STATUS_ID = S.ID AND INV.CONTRACT_ID = CO.ID AND R.ID = CO.ROOM_ID ORDER BY INV.ID DESC";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch(SQLException e){
            System.out.println("Error at ManageInvoiceController/getInvoices");
        }
        return rs;
    }
    
    public boolean insertInvoice(String name, String mm, String yy){
        try{
            String Co_id = null;
            String getID = "SELECT CO.ID FROM ROOM R JOIN CONTRACT CO ON CO.ROOM_ID = R.ID WHERE R.NAME = ? AND CO.STATUS_ID = 1";
            PreparedStatement psta = conn.prepareStatement(getID);
            psta.setString(1,name);
            ResultSet rs = psta.executeQuery();
            if(rs.next()){
                Co_id = rs.getString(1);}
            
            String SQLins = "INSERT INTO INVOICE(CONTRACT_ID, MONTH, YEAR) VALUES (?,?,?)";
            PreparedStatement pIns = conn.prepareStatement(SQLins);
            pIns.setString(1, Co_id);
            pIns.setString(2, mm);
            pIns.setString(3, yy);
            int n = pIns.executeUpdate();
            return true;
            
        } catch(SQLException e){
            System.out.println("Error at InvoiceController/insertInvoice\nError is: " + e);
            return false;
        }        
    }    
    
}
