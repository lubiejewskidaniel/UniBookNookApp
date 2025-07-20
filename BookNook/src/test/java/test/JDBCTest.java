package test;

import DAO.JdbcDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for testing the JDBC connection and database queries.
 * It ensures that the database is accessible and that book data can be retrieved correctly.
 */
public class JDBCTest {
    private static Connection connection; // Holds the database connection for testing

    /**
     * This method runs before all tests.
     * It establishes a connection to the database to ensure that the database is accessible.
     */
    @BeforeAll
    public static void setUp() {
        try {
            // Initialize the database connection before executing tests
            connection = JdbcDAO.getConnection();
        } catch (SQLException ex) {
            // Log an error message if the connection fails
            Logger.getLogger(JDBCTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Validate that the connection was successfully established
        assertNotNull(connection, "Connection to the database should be established.");
        System.out.println("Connection established successfully for tests.");
    }

    /**
     * This method runs after all tests are completed.
     * It closes the database connection to free up resources.
     */
    @AfterAll
    public static void tearDown() {
        // Close the database connection after all tests are done
        JdbcDAO.closeConnection(connection);
        System.out.println("Database connection closed.");
    }

    /**
     * This test checks if books can be successfully retrieved from the database.
     * It validates that the result set is not null and that at least one book exists in the database.
     */
    @Test
    public void testRetrieveBooks() {
        String query = "SELECT * FROM books"; // SQL query to fetch all books

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Ensure the result set is not null (meaning the query executed correctly)
            assertNotNull(resultSet, "ResultSet should not be null.");

            // Count the number of books retrieved from the database
            int count = 0;
            while (resultSet.next()) {
                count++;

                // Retrieve book details from the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category");

                // Log each book's details for debugging purposes
                System.out.println("ID: " + id + ", Title: " + name + ", Author: " + author + 
                                   ", Category: " + category + ", Price: " + price);

                // Validate that important fields are not null
                assertNotNull(name, "Book name should not be null.");
                assertNotNull(author, "Book author should not be null.");
            }

            // Ensure at least one book exists in the database
            assertTrue(count > 0, "There should be at least one book in the database.");

        } catch (SQLException e) {
            // If the query fails, fail the test with an error message
            fail("Failed to execute query or retrieve books: " + e.getMessage());
        }
    }
}