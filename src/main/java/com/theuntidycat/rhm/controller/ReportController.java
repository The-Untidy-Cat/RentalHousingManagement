/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.controller;

import com.theuntidycat.rhm.database.Oracle;
import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author SHeroAnh
 */
public class ReportController {

    private final Oracle database = new Oracle();
    private final Connection conn = database.getConnection();

    public double getTotalRevenueByYear(String year) {
        ResultSet rs = null;
        String sql = null;
        PreparedStatement pstmt = null;
        try {
            sql = "SELECT SUM(CAST(months_between(TO_DATE(contract.END_date),TO_DATE(contract.START_date)) AS NUMBER) * contract.price_per_period) AS sum_rental_price FROM contract WHERE to_char(contract.start_date, 'YYYY') = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, year);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getDouble("SUM_RENTAL_PRICE");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    public ResultSet getMonthlyRevenueByYear(String year) {
        ResultSet rs = null;
        String sql = null;
        PreparedStatement pstmt = null;
        try {
            sql = "SELECT SUM(CAST(months_between(TO_DATE(contract.END_date),TO_DATE(contract.START_date)) AS NUMBER) * contract.price_per_period) AS sum_rental_price, to_char(contract.START_date,'mm') AS MONTH FROM contract  WHERE to_char(contract.start_date, 'YYYY') = ? group by to_char(contract.START_date,'mm') ORDER BY MONTH ASC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, year);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rs;
    }
    public double getTotalReceivedByYear(String year) {
        ResultSet rs = null;
        String sql = null;
        PreparedStatement pstmt = null;
        try {
            double sum = 0;
            sql = "SELECT SUM(DETAIL_INVOICE.SUM_MONEY) AS SUM_RECIVED_MONEY FROM DETAIL_INVOICE JOIN INVOICE ON DETAIL_INVOICE.INVOICE_ID = INVOICE.ID WHERE ((INVOICE.YEAR = ? AND INVOICE.MONTH BETWEEN 2 AND 12) OR (INVOICE.YEAR = ? + 1 AND INVOICE.MONTH = 1)) AND DETAIL_INVOICE.TYPE_ID = 0 AND INVOICE.STATUS_ID = 1";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, year);
            pstmt.setString(2, year);
            rs = pstmt.executeQuery();
            if (rs.next()){
                sum += rs.getDouble("SUM_RECIVED_MONEY");
            }
            sql = "SELECT SUM(contract.deposit) AS SUM_DEPOSIT FROM contract WHERE to_char(contract.start_date, 'YYYY') = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, year);
            rs = pstmt.executeQuery();
            if (rs.next()){
                sum += rs.getDouble("SUM_DEPOSIT");
            }
            return sum;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    public double getTotalRevenueByMonth(String month, String year) {
        ResultSet rs = null;
        String sql = null;
        PreparedStatement pstmt = null;
        try {
            sql = "SELECT SUM(CAST(months_between(TO_DATE(contract.END_date),TO_DATE(contract.START_date)) AS NUMBER) * contract.price_per_period) AS sum_rental_price FROM contract WHERE to_char(contract.start_date, 'YYYY') = ? AND to_char(contract.start_date, 'MM') = CAST(LPAD(?,2,0) AS VARCHAR2(2))";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, year);
            pstmt.setString(2, month);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getDouble("SUM_RENTAL_PRICE");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    public double getTotalReceivedByMonth(String month, String year) {
        ResultSet rs = null;
        String sql = null;
        PreparedStatement pstmt = null;
        try {
            double sum = 0;
            sql = "SELECT SUM(DETAIL_INVOICE.SUM_MONEY) AS SUM_RECIVED_MONEY FROM DETAIL_INVOICE JOIN INVOICE ON DETAIL_INVOICE.INVOICE_ID = INVOICE.ID WHERE INVOICE.YEAR = ? AND INVOICE.MONTH = ? AND INVOICE.STATUS_ID = 1";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, year);
            pstmt.setString(2, month);
            rs = pstmt.executeQuery();
            if (rs.next()){
                sum += rs.getDouble("SUM_RECIVED_MONEY");
            }
            sql = "SELECT SUM(contract.deposit) AS SUM_DEPOSIT FROM contract WHERE to_char(contract.start_date, 'YYYY') = ? AND to_char(contract.start_date, 'MM') = CAST(LPAD(?,2,0) AS VARCHAR2(2))";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, year);
            pstmt.setString(2, month);
            rs = pstmt.executeQuery();
            if (rs.next()){
                sum += rs.getDouble("SUM_DEPOSIT");
            }
            return sum;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
}
