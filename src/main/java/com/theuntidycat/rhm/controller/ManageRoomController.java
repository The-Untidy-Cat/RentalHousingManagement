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
            String room_type = null;
            String getTypeID = "SELECT ID FROM ROOM_TYPE WHERE NAME = ?";
            PreparedStatement ps = con.prepareStatement(getTypeID);
            ps.setString(1,loai);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                room_type = rs.getString(1);
            }
            
            String room_status = null;
            String getStatusID = "SELECT ID FROM ROOM_STATUS WHERE NAME = ?";
            ps = con.prepareStatement(getStatusID);
            ps.setString(1,trangthai);
            rs = ps.executeQuery();
            if(rs.next()){
                room_status = rs.getString(1);
            }
            String strSql2 = "UPDATE ROOM SET name = ?, capacity = ?, rental_price = ?, type_id = ?, area = ?, status_id = ? WHERE id = ?";
            PreparedStatement pre2 = con.prepareStatement(strSql2);
            pre2.setString(1, ten);
            pre2.setInt(2, succhua);
            pre2.setInt(3, giathue);
            pre2.setInt(4, Integer.parseInt(room_type));
            pre2.setInt(5, dientich);
            pre2.setInt(6, Integer.parseInt(room_status));
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
   
}  
