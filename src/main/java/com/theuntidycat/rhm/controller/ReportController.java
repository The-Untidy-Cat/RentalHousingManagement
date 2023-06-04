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
class RootController {

    private final Oracle database = new Oracle();
    private final Connection conn = database.getConnection();

    public class RoomController {

        public int getTotalRoom() {
            ResultSet rs = null;
            String sql = null;
            Statement stmt = null;
            try {
                sql = "SELECT COUNT(*) AS TOTAL_ROOM FROM ROOM";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }

        public int getTotalRoomByStatus(int status) {
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT COUNT(*) AS TOTAL_EMPTY_ROOM FROM ROOM WHERE STATUS_ID = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, status);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }
    }

    public class TicketController {

        public int getTotalTicket() {
            ResultSet rs = null;
            String sql = null;
            Statement stmt = null;
            try {
                sql = "SELECT COUNT(*) FROM TICKET";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }

        public int getTotalTicketByStatus(int status) {
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT COUNT(*) FROM TICKET WHERE STATUS_ID = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, status);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }
    }

    public class InvoiceController {

        public int getTotalInvoice() {
            ResultSet rs = null;
            String sql = null;
            Statement stmt = null;
            try {
                sql = "SELECT COUNT(*) FROM invoice";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }

        public int getTotalInvoiceByStatus(int status) {
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT COUNT(*) FROM invoice WHERE STATUS_ID = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, status);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }

        public double getTotalMoneyInvoiveByYear(String year) {
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT SUM(TOTAL_MONEY) FROM invoice WHERE YEAR = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.valueOf(year));
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }

        public double getTotalMoneyPaidInvoiveByYear(String year) {
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT SUM(MONEY_PAID) FROM invoice WHERE YEAR = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.valueOf(year));
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }

        public double getTotalMoneyInvoiveByMonth(String month, String year) {
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT SUM(TOTAL_MONEY) FROM invoice WHERE YEAR = ? AND MONTH = ? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.valueOf(year));
                pstmt.setInt(2, Integer.valueOf(month));
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }

        public double getTotalMoneyPaidInvoiveByMonth(String month, String year) {
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT SUM(MONEY_PAID) FROM invoice WHERE YEAR = ? AND MONTH = ? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.valueOf(year));
                pstmt.setInt(2, Integer.valueOf(month));
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }

        public ResultSet getMonthlyInvoiceByYear(String year) {
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT SUM(TOTAL_MONEY) AS SUM_MONEY, MONTH FROM invoice WHERE YEAR = ? AND MONTH BETWEEN 1 AND 12 GROUP BY MONTH ORDER BY MONTH ASC";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.valueOf(year));
                rs = pstmt.executeQuery();
            } catch (SQLException e) {
                System.out.println(e);
            }
            return rs;
        }
    
        public ResultSet getRoomInvoiceByMonth(String month, String year){
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT SUM(TOTAL_MONEY) AS SUM_MONEY, ROOM_ID FROM invoice WHERE YEAR = ? AND MONTH = ? GROUP BY ROOM_ID";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.valueOf(year));
                pstmt.setInt(2, Integer.valueOf(month));
                rs = pstmt.executeQuery();
            } catch (SQLException e) {
                System.out.println(e);
            }
            return rs;
        }
    }

    public class TenantController {

        public int getTotalTenant() {
            ResultSet rs = null;
            String sql = null;
            Statement stmt = null;
            try {
                sql = "SELECT COUNT(*) FROM tenant";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }

        public int getTotalTenantByStatus(int status) {
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT COUNT(*) FROM tenant WHERE STATUS_ID = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, status);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }
    }

    public class ContractController {

        public int getTotalDueSoonContract() {
            ResultSet rs = null;
            String sql = null;
            Statement stmt = null;
            try {
                sql = "SELECT COUNT(*) FROM contract HAVING to_date(CONTRACT.END_DATE, 'yyyy-mm-dd') >= trunc(sysdate) AND (to_date(CONTRACT.END_DATE, 'yyyy-mm-dd') - trunc(sysdate)) BETWEEN 1 AND 30 ";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }
    }

    public class RevenueController {

        public double getTotalRevenueByYear(String year) {
            ResultSet rs = null;
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                sql = "SELECT SUM(CAST(months_between(TO_DATE(contract.END_date),TO_DATE(contract.START_date)) AS NUMBER) * contract.price_per_period) AS sum_rental_price FROM contract WHERE to_char(contract.start_date, 'YYYY') = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, year);
                rs = pstmt.executeQuery();
                if (rs.next()) {
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
                if (rs.next()) {
                    sum += rs.getDouble("SUM_RECIVED_MONEY");
                }
                sql = "SELECT SUM(contract.deposit) AS SUM_DEPOSIT FROM contract WHERE to_char(contract.start_date, 'YYYY') = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, year);
                rs = pstmt.executeQuery();
                if (rs.next()) {
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
                if (rs.next()) {
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
                if (rs.next()) {
                    sum += rs.getDouble("SUM_RECIVED_MONEY");
                }
                sql = "SELECT SUM(contract.deposit) AS SUM_DEPOSIT FROM contract WHERE to_char(contract.start_date, 'YYYY') = ? AND to_char(contract.start_date, 'MM') = CAST(LPAD(?,2,0) AS VARCHAR2(2))";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, year);
                pstmt.setString(2, month);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    sum += rs.getDouble("SUM_DEPOSIT");
                }
                return sum;
            } catch (SQLException e) {
                System.out.println(e);
            }
            return 0;
        }
    }
}

/**
 *
 * @author SHeroAnh
 */
public class ReportController {

    private RootController rootController = new RootController();
    private RootController.RevenueController revenueController = rootController.new RevenueController();
    private RootController.TicketController ticketController = rootController.new TicketController();
    private RootController.TenantController tenantController = rootController.new TenantController();
    private RootController.ContractController contractController = rootController.new ContractController();
    private RootController.InvoiceController invoiceController = rootController.new InvoiceController();
    private RootController.RoomController roomController = rootController.new RoomController();

    public RootController.RevenueController getRevenue() {
        return revenueController;
    }

    public RootController.TicketController getTicket() {
        return ticketController;
    }

    public RootController.TenantController getTenant() {
        return tenantController;
    }

    public RootController.ContractController getContract() {
        return contractController;
    }

    public RootController.InvoiceController getInvoice() {
        return invoiceController;
    }

    public RootController.RoomController getRoom() {
        return roomController;
    }
}
