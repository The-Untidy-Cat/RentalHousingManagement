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
    
    public ResultSet getContractTable()
    {
        ResultSet rs = null;
        try
        {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT CONTRACT.ID, START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID, CONTRACT_STATUS.NAME FROM CONTRACT JOIN CONTRACT_STATUS ON CONTRACT.STATUS_ID = CONTRACT_STATUS.ID ORDER BY CONTRACT.ID");
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("Error in ContractController getContractTable");
        }
        return rs;
    }
         
    
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
            System.out.println("Error in ContractController getCBBRoom");
        }
        return rs;
    }
    
    public ResultSet cbbRoomIDAction(String room_name)
    {
        ResultSet rs = null;
        try
        {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID FROM ROOM WHERE NAME = '"+room_name+"'");
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("Error in ContractController cbbRoomIDAction");
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
            prestmt.executeUpdate();
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    public ResultSet loadNewDataContractTable(String contract_id)
    {
        ResultSet rs = null;
        try
        {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT CONTRACT.ID, START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID, CONTRACT_STATUS.NAME FROM CONTRACT JOIN CONTRACT_STATUS ON CONTRACT.STATUS_ID = CONTRACT_STATUS.ID WHERE CONTRACT.ID = '"+contract_id+"'");
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("Error in ContractController loadNewDataContractTable");
        }
        return rs;
    }
    
    public ResultSet getRoomName(String room_id)
    {
        ResultSet rs = null;
        try
        {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT NAME FROM ROOM WHERE ID = '"+room_id+"'");
        }   
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("Error in ContractController getRoomName");
        }
        return rs;
    }
    
    public ResultSet getRepCCCD(String rep_id)
    {
        ResultSet rs = null;
        try
        {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ID_NUMBER FROM TENANT WHERE ID = '"+rep_id+"'");
        }   
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("Error in ContractController getRepCCCD");
        }
        return rs;
    }
}
