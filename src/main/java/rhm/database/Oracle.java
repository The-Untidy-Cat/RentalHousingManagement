package rhm.database;

import java.sql.*;

public class                                                                                                                                   Oracle {
    // private final String DB_URL = "jdbc:oracle:thin:@localhost:1521/orcl";
    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521/xe";
    private final String DB_USERNAME = "SHEROANH";
    private final String DB_PASSWORD = "Tuananh2003";

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
