package com.theuntidycat.rhm.database;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

public class                                                                                                                                   Oracle {
    Dotenv dotenv = Dotenv.load();
    private final String DB_URL = dotenv.get("DB_URL");
    private final String DB_USERNAME = dotenv.get("DB_USERNAME");
    private final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
