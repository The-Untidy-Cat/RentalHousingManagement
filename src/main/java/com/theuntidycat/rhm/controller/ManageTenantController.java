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
            String strSQL = "SELECT T.id, T.name, Home_town, TO_CHAR(dob, 'DD/MM/YYYY'), phone_number, id_number, email, S.name FROM TENANT T, TENANT_STATUS S WHERE T.status_id = S.id ORDER BY T.id";
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
            String strSql = "INSERT INTO TENANT (name, Home_Town, dob, Phone_Number, ID_Number, Email) VALUES (?,?,TO_DATE(?,'DD/MM/YYYY'),?,?,?)";
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
    public boolean updateTenant(String sdt, String email, String status, String id){
        try{
            String strSql = "UPDATE TENANT SET Phone_Number = ?, email = ?, status_id = ? WHERE id = ?";
            PreparedStatement pre = con.prepareStatement(strSql);
            pre.setString(1, sdt);
            pre.setString(2, email);
            pre.setInt(3, getStatusID(status));
            pre.setString(4, id);
            pre.executeUpdate();   
            return true;
        } catch(SQLException e){
            System.out.println(e);
            return false;
        }  
    }
    public boolean deleteTenant(String id){
        try{
            String strSQL = "DELETE FROM TENANT WHERE id = ?";
            PreparedStatement pres = con.prepareStatement(strSQL);
            pres.setString(1,  id);
            pres.executeUpdate();
            return true;
        }
        catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    public int getStatusID(String status){
        int id = 0;
        try{
            String getStatus = "SELECT id FROM TENANT_STATUS WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(getStatus);
            ps.setString(1,status);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return id;
    }
    public ResultSet queryTenant(String trangthai){
        ResultSet rs = null;
        try{
            String strSQL = "SELECT T.id, T.name, Home_town, TO_CHAR(dob, 'DD/MM/YYYY'), phone_number, id_number, email, S.name FROM TENANT T, TENANT_STATUS S WHERE T.status_id = S.id AND status_id = ? ORDER BY T.id";
            PreparedStatement ps = con.prepareStatement(strSQL);
            ps.setInt(1,getStatusID(trangthai));
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return rs;
    }
}  
