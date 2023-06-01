/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.controller;

import com.theuntidycat.rhm.database.Oracle;
import com.theuntidycat.rhm.model.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author minhngoc
 */
public class ManageContractController 
{
    private final Oracle database = new Oracle();
    private final Connection con = database.getConnection();
    
    public ResultSet getCBBRoom()
    {
        ResultSet rs = null;
        try
        {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID, NAME FROM ROOM");
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("Error in Contract_GetCBBRoom");
        }
        return rs;
    }
    
    public boolean insertContract(String start_date, String end_date, float price, float deposit, String tenant_id, String room_id)
    {
        try
        {
            PreparedStatement prestmt = con.prepareStatement("INSERT INTO CONTRACT(START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID, STATUS_ID) VALUES (?,?,?,?,?,?,1)");
            prestmt.setString(1, start_date);
            prestmt.setString(2, end_date);
            prestmt.setFloat(3, price);
            prestmt.setFloat(4, deposit);
            prestmt.setString(5, tenant_id);
            prestmt.setString(6, room_id);
            
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }
}