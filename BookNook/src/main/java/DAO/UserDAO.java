package DAO;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Retrieves a user by username and password (used for login authentication)
    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        String sql = "SELECT id, username, password, email, role, phone, balance FROM Users WHERE username = ? AND password = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcDAO.getConnection(); // Get database connection
            stmt = conn.prepareStatement(sql); // Prepare SQL query
            stmt.setString(1, username); // Set username in query
            stmt.setString(2, password); // Set password in query
            rs = stmt.executeQuery(); // Execute query

            if (rs.next()) {
                // Create a User object with data from the database
                user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("phone"),
                    rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return user; // Return the user (or null if not found)
    }

    // Retrieves all users from the database
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, username, password, email, role, phone, balance FROM Users";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcDAO.getConnection(); // Get database connection
            stmt = conn.prepareStatement(sql); // Prepare SQL query
            rs = stmt.executeQuery(); // Execute query

            while (rs.next()) {
                // Create a User object for each row in the result set
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("phone"),
                    rs.getDouble("balance")
                );
                userList.add(user); // Add user to the list
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return userList; // Return the list of users
    }

    // Retrieves a user by their unique ID
    public User getUserById(int id) {
        User user = null;
        String sql = "SELECT id, username, password, email, role, phone, balance FROM Users WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcDAO.getConnection();
            stmt = conn.prepareStatement(sql); 
            stmt.setInt(1, id); 
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("phone"),
                    rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return user;
    }

    // Updates user details in the database
    public void updateUser(User user) {
        String sql = "UPDATE Users SET username = ?, password = ?, email = ?, role = ?, phone = ?, balance = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JdbcDAO.getConnection(); 
            conn.setAutoCommit(false); // Disable auto-commit for transaction safety

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getPhone());
            stmt.setDouble(6, user.getBalance());
            stmt.setInt(7, user.getId());

            int rowsUpdated = stmt.executeUpdate();
            conn.commit(); // Commit transaction

            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.err.println("No user found with ID: " + user.getId());
            }
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            try {
                if (conn != null) conn.rollback(); // Rollback changes if there's an error
            } catch (SQLException rollbackEx) {
                System.err.println("Rollback error: " + rollbackEx.getMessage());
            }
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    // Deletes a user from the database by their ID
    public void deleteUserById(int id) {
        String sql = "DELETE FROM Users WHERE id = ?";

        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // Set ID to delete
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.err.println("No user found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }
    
    public static void updateUserBalance(int userId, double newBalance) {
        String query = "UPDATE Users SET balance = ? WHERE id = ?";

        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newBalance);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}