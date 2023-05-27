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
            String sql = "SELECT INV.ID, MONTH || '/' || YEAR, R.NAME, INV.TOTAL_MONEY, S.NAME FROM INVOICE INV, INVOICE_STATUS S, CONTRACT CO, ROOM R WHERE INV.STATUS_ID = S.ID AND INV.CONTRACT_ID = CO.ID AND R.ID = CO.ROOM_ID";
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch(SQLException e){
            System.out.println("Error at ManageInvoiceController/getInvoices");
        }
        return rs;
    }
    
    /*public int deleteInvoice(){
        try{
            
        } catch(SQLException e){
            System.out.println("Error at ManageInvoiceController/deleteInvoice");
        }
    }*/    
    
}
