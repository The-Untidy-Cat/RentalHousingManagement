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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ManageRoomController {
    private final Oracle database = new Oracle();
    private final Connection con = database.getConnection();
    
    public ResultSet getListOfRoom(){
        ResultSet result = null;
        try{
            String strSQL = "SELECT R.id, R.name, capacity, rental_price, T.name, area, S.name FROM ROOM R, ROOM_TYPE T, ROOM_STATUS S WHERE R.type_id = T.id AND R.status_id = S.id ORDER BY R.id";
            Statement stat = con.createStatement();
            result = stat.executeQuery(strSQL);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return result;
    }
    
    public boolean insertRoom(String ten, int succhua, int giathue, int loai, int dientich, int trangthai){
        try{
            String strSql = "INSERT INTO ROOM (name, capacity, rental_price, type_id, area, status_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(strSql);
            ps.setString(1, ten);
            ps.setInt(2, succhua);
            ps.setInt(3, giathue);
            ps.setInt(4, loai);
            ps.setInt(5, dientich);
            ps.setInt(6, trangthai);
            ps.executeUpdate();   
            return true;
        } catch(SQLException e){
            System.out.println(e);
            return false;
        }  
    }

    public boolean updateRoom(String ten, int succhua, int giathue, String loai, int dientich, String trangthai, String id){
        try{
            String strSql2 = "UPDATE ROOM SET name = ?, capacity = ?, rental_price = ?, type_id = ?, area = ?, status_id = ? WHERE id = ?";
            PreparedStatement pre2 = con.prepareStatement(strSql2);
            pre2.setString(1, ten);
            pre2.setInt(2, succhua);
            pre2.setInt(3, giathue);
            pre2.setInt(4, getTypeID(loai));
            pre2.setInt(5, dientich);
            pre2.setInt(6, getStatusID(trangthai));
            pre2.setString(7, id);
            pre2.executeUpdate();   
            return true;
        } catch(SQLException e){
            System.out.println(e);
            return false;
        }  
    }
    public boolean deleteRoom(String id){
        try{
            String strSQL = "DELETE FROM ROOM WHERE id = ?";
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
    public int getTypeID(String type){
        int id = 0;
        try{
            String getID = "SELECT id FROM ROOM_TYPE WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(getID);
            ps.setString(1,type);
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
    public int getStatusID(String status){
        int id = 0;
        try{
            String getID = "SELECT id FROM ROOM_STATUS WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(getID);
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
    public ResultSet queryRoom(String type, String status){
        ResultSet rs = null;
        try{
            String strSQL = "SELECT R.id, R.name, capacity, rental_price, T.name, area, S.name FROM ROOM R, ROOM_STATUS S, ROOM_TYPE T WHERE T.id = R.type_id AND S.id = R.status_id AND T.id = ? AND S.id = ? ORDER BY R.id";
            PreparedStatement ps = con.prepareStatement(strSQL);
            ps.setInt(1,getTypeID(type));
            ps.setInt(2,getStatusID(status));
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return rs;
    }
}  
