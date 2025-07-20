package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDAO {

    // Path to the SQLite database file
    private static final String DB_PATH = "C:/Users/lubie/Desktop/z linuxa/remo/JavaBookShopProject/BookNook/src/main/resources/databases/booknook.db";

    // Private constructor to prevent instantiation of this class
    // (since we only need static methods, we don't want objects of JdbcDAO)
    private JdbcDAO() {
        throw new UnsupportedOperationException("Utility class - no instances allowed");
    }

    // Method to establish a connection with the SQLite database
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // Load the SQLite JDBC driver (ensures the driver is available)
            Class.forName("org.sqlite.JDBC");

            // Create a connection to the database using the provided file path
            conn = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);

            // Ensure the connection allows writing (some databases might default to read-only)
            conn.setReadOnly(false);

            // Print confirmation message (useful for debugging)
            System.out.println("Connected to the database: " + DB_PATH);
        } catch (ClassNotFoundException e) {
            // If the driver isn't found, it means dependencies are missing
            throw new SQLException("SQLite driver not found! Check your project dependencies.", e);
        } catch (SQLException e) {
            // Catch any other SQL errors and throw a new exception with details
            throw new SQLException("Error connecting to the database: " + e.getMessage(), e);
        }
        return conn; // Return the active database connection
    }

    // Method to close a database connection when it's no longer needed
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close(); // Close the connection
                System.out.println("Database connection closed successfully.");
            } catch (SQLException e) {
                System.err.println("Error while closing the database connection: " + e.getMessage());
            }
        }
    }
}