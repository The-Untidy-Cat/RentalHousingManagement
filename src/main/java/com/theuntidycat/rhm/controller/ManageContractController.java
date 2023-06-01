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
}
