package csms.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String url = "jdbc:mysql://localhost/";
    private static String dbName = "csms";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "";
    private static boolean initialized = false;

    // Load connection details from config.properties if available
    private static void loadConfig() {
        java.io.File configFile = new java.io.File("config.properties");
        if (configFile.exists()) {
            try (java.io.FileInputStream in = new java.io.FileInputStream(configFile)) {
                java.util.Properties pro = new java.util.Properties();
                pro.load(in);
                if (pro.containsKey("db_url")) {
                    url = pro.getProperty("db_url");
                }
                if (pro.containsKey("db_name")) {
                    dbName = pro.getProperty("db_name");
                }
                if (pro.containsKey("db_username")) {
                    username = pro.getProperty("db_username");
                }
                if (pro.containsKey("db_password")) {
                    password = pro.getProperty("db_password");
                }
            } catch (Exception e) {
                System.err.println("Error loading db config: " + e.getMessage());
            }
        }
    }

    public static synchronized Connection openConnection() {
        loadConfig();
        Connection conn = null;
        try {
            Class.forName(driver);
            // Try connecting to the database
            conn = DriverManager.getConnection(url + dbName + "?allowMultiQueries=true&useSSL=false&serverTimezone=UTC", username, password);
            
            // If connected, verify if we need to initialize tables
            if (!initialized) {
                if (!tablesExist(conn)) {
                    initializeDatabase(conn);
                }
                initialized = true;
            }
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found: " + e.getMessage());
            return null;
        } catch (SQLException sqle) {
            // If connection to database failed, try connecting to the server instead to create the db
            System.out.println("Connection to db failed, attempting to bootstrap database...");
            try {
                Connection serverConn = DriverManager.getConnection(url + "?allowMultiQueries=true&useSSL=false&serverTimezone=UTC", username, password);
                try (java.sql.Statement stmt = serverConn.createStatement()) {
                    stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + dbName + "` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;");
                }
                serverConn.close();
                
                // Reconnect to the newly created database
                conn = DriverManager.getConnection(url + dbName + "?allowMultiQueries=true&useSSL=false&serverTimezone=UTC", username, password);
                initializeDatabase(conn);
                initialized = true;
                return conn;
            } catch (SQLException ex) {
                System.err.println("Failed to bootstrap database: " + ex.getMessage());
                return null;
            }
        }
    }

    private static boolean tablesExist(Connection conn) {
        try {
            // Check if student table exists as a quick test
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT 1 FROM student LIMIT 1");
            rs.close();
            stmt.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private static void initializeDatabase(Connection conn) {
        System.out.println("Initializing database schema and mock data...");
        try {
            // Execute schema.sql
            executeSqlScript(conn, "/csms/databaseconnection/schema.sql");
            // Execute data.sql
            executeSqlScript(conn, "/csms/databaseconnection/data.sql");
            System.out.println("Database initialization completed successfully.");
        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void executeSqlScript(Connection conn, String resourcePath) throws Exception {
        java.io.InputStream is = DBConnection.class.getResourceAsStream(resourcePath);
        if (is == null) {
            throw new Exception("SQL script not found in resources: " + resourcePath);
        }
        
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(is, "UTF-8"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            java.sql.Statement stmt = conn.createStatement();
            
            // Disable foreign key checks for seeding
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
            
            while ((line = reader.readLine()) != null) {
                // Ignore comments and empty lines
                if (line.trim().startsWith("--") || line.trim().startsWith("/*") || line.trim().isEmpty()) {
                    continue;
                }
                sb.append(line).append("\n");
                
                // If statement ends with a semicolon, execute it
                if (line.trim().endsWith(";")) {
                    String sql = sb.toString().trim();
                    // Remove trailing semicolon for execute
                    if (sql.endsWith(";")) {
                        sql = sql.substring(0, sql.length() - 1);
                    }
                    if (!sql.isEmpty()) {
                        try {
                            stmt.execute(sql);
                        } catch (SQLException e) {
                            System.err.println("Error executing SQL: " + sql);
                            System.err.println("Error details: " + e.getMessage());
                            // Throw schema errors
                            if (resourcePath.contains("schema")) {
                                throw e;
                            }
                        }
                    }
                    sb.setLength(0);
                }
            }
            
            // Re-enable foreign key checks
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1;");
            stmt.close();
        }
    }
}
