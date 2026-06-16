package csms.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    static final String url = "jdbc:mysql://localhost/";
    static final String dbName = "csms";
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String username = "root";
    static final String password = "";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url + dbName, username, password);
            return conn;
        } catch (ClassNotFoundException | SQLException sqle) {
            return null;
        }
    }

}
