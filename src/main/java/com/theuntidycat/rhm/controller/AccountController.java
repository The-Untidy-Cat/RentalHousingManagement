package com.theuntidycat.rhm.controller;

import java.sql.*;

import com.theuntidycat.rhm.database.Oracle;
import com.theuntidycat.rhm.model.User;

public class AccountController {

    private final Oracle database = new Oracle();
    private final Connection conn = database.getConnection();

    public boolean verifyUser(String username, String password) {
        int size = 0;
        try {
            System.out.println(username + ' ' + password);
            String sql = "SELECT COUNT(*) FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                System.out.println("Recordcount: " + rs.getInt(1));
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (size == 1) {
            return true;
        } else {
            return false;
        }
    }
    public User getUser(String username, String password) {
        User user = new User();
        try {
            String sql = "SELECT ID, USERNAME, DISPLAY_NAME FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                user.id = rs.getString(1);
                user.username = rs.getString(2);
                user.displayName = rs.getString(3);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return user;
    }
    
    public boolean changePassword(String id, String password) {
        User user = new User();
        try {
            String sql = "UPDATE ACCOUNT SET PASSWORD = ? WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setString(2, id);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
