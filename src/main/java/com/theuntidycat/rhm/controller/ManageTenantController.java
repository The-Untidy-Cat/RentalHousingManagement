/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.controller;

import com.theuntidycat.rhm.database.Oracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class ManageTenantController {
    private final Oracle database = new Oracle();
    private final Connection con = database.getConnection();
    
    public ResultSet getListOfTenant(){
        ResultSet result = null;
        try{
            String strSQL = "SELECT id, name, Home_town, dob, phone_number, id_number, email FROM TENANT ORDER BY id";
            Statement stat = con.createStatement();
            result = stat.executeQuery(strSQL);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return result;
    }
    
    public boolean insertTenant(String ten, String que, String ntns, String sdt, String cmnd, String email){
        try{
            String strSql = "INSERT INTO TENANT (name, Home_Town, dob, Phone_Number, ID_Number, Email) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(strSql);
            ps.setString(1, ten);
            ps.setString(2, que);
            ps.setString(3, ntns);
            ps.setString(4, sdt);
            ps.setString(5, cmnd);
            ps.setString(6, email);
            ps.executeUpdate();   
            return true;
        } catch(SQLException e){
            System.out.println(e);
            return false;
        }  
    }
}
