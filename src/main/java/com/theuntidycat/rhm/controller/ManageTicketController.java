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
public class ManageTicketController {
    private final Oracle database = new Oracle();
    private final Connection con = database.getConnection();
    
    public ResultSet getListOfTicket(){
        ResultSet result = null;
        try{
            String strSQL = "SELECT T.id, room_id, tenant_id, TO_CHAR(incident_time, 'dd/mm/yyyy'), S.name T FROM SUPPORT_TICKET T, SUPPORT_TICKET_STATUS S WHERE T.status_id = S.id ORDER BY T.id";
            Statement stat = con.createStatement();
            result = stat.executeQuery(strSQL);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return result;
    }
    public String getDescription(String id){
        String des = null;
        try{
            String strSql = "SELECT description FROM SUPPORT_TICKET WHERE id = ?";
            PreparedStatement pre = con.prepareStatement(strSql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                des = rs.getString(1);
            }
        } catch(SQLException e){
            System.out.println(e);
        } 
        return des;
    }
    public int getStatusID(String status){
        int id = 0;
        try{
            String getStatus = "SELECT id FROM SUPPORT_TICKET_STATUS WHERE name = ?";
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
    public boolean updateTicket(String status, String id){
        try{
            String strSql = "UPDATE SUPPORT_TICKET SET status_id = ? WHERE id = ?";
            PreparedStatement pre = con.prepareStatement(strSql);
            pre.setInt(1, getStatusID(status));
            pre.setString(2, id);
            pre.executeUpdate();   
            return true;
        } catch(SQLException e){
            System.out.println(e);
            return false;
        }  
    }
    public int getMonth(String date){
        int id = 0;
        try{
            String strSql = "SELECT EXTRACT(MONTH FROM incident_time) FROM support_ticket WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(strSql);
            ps.setString(1,date);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }  
        } catch(SQLException e){
            System.out.println(e);
        }  
        return id;
    }
    public int getYear(String date){
        int id = 0;
        try{
            String strSql = "SELECT EXTRACT(YEAR FROM incident_time) FROM support_ticket WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(strSql);
            ps.setString(1,date);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }  
        } catch(SQLException e){
            System.out.println(e);
        }  
        return id;
    }
    public ResultSet queryTicket(String status, String date){
        ResultSet rs = null;
        try{
            String strSQL = "SELECT T.id, room_id, tenant_id, TO_CHAR(incident_time, 'dd/mm/yyyy'), S.name T FROM SUPPORT_TICKET T, SUPPORT_TICKET_STATUS S WHERE T.status_id = S.id AND status_id = ? AND EXTRACT(MONTH FROM incident_time) = ? AND EXTRACT(YEAR FROM incident_time) = ? ORDER BY T.id";
            PreparedStatement ps = con.prepareStatement(strSQL);
            ps.setInt(1, getStatusID(status));
            ps.setInt(2,getMonth(date));
            ps.setInt(3,getYear(date));
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return rs;
    }
}
