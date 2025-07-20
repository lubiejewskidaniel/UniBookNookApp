package DAO;

import model.Accessory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccessoryDAO {

    // Gets all accessories from the database
    public List<Accessory> getAllAccessories() {
        List<Accessory> accessories = new ArrayList<>();
        String query = "SELECT * FROM Accessories";
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = JdbcDAO.getConnection(); // Connect to the database
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                // Creating a new accessory object from the result set
                Accessory accessory = new Accessory(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("image"),
                        rs.getInt("reserved_stock")
                );
                accessory.setId(rs.getInt("id")); // Setting the ID manually
                accessories.add(accessory); // Adding to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the error if something goes wrong
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close(); // Always close resources
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return accessories;
    }

    // Gets a single accessory by its ID
    public Accessory getAccessoryById(int id) {
        Accessory accessory = null;
        String query = "SELECT * FROM Accessories WHERE id = ?";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = JdbcDAO.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Creating an accessory object from the database record
                accessory = new Accessory(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("image"),
                        rs.getInt("reserved_stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return accessory;
    }
    
    // Deletes an accessory by its ID
    public boolean deleteAccessory(int accessoryId) {
        String query = "DELETE FROM accessories WHERE id = ?";
        try (Connection connection = JdbcDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accessoryId);
            int rowsAffected = preparedStatement.executeUpdate(); // Checking if something was deleted
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Inserts a new accessory into the database
    public boolean insertAccessory(Accessory accessory) {
        String query = "INSERT INTO accessories (name, description, price, quantity, image, reserved_stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = JdbcDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accessory.getName());
            preparedStatement.setString(2, accessory.getDescription());
            preparedStatement.setDouble(3, accessory.getPrice());
            preparedStatement.setInt(4, accessory.getQuantity());
            preparedStatement.setString(5, accessory.getImage());
            preparedStatement.setInt(6, accessory.getReservedStock());
            return preparedStatement.executeUpdate() > 0; // Returns true if insert was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void updateReservedStock(int accessoryId, int quantityChange) {
        String query = "UPDATE Accessories SET reserved_stock = reserved_stock + ? WHERE id = ?";

        try (Connection connection = JdbcDAO.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantityChange);
            stmt.setInt(2, accessoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating reserved stock: " + e.getMessage());
        }
    }
    
    public void increaseAvailableStock(int accessoryId, int quantity) {
        String sql = "UPDATE accessories SET quantity = quantity + ? WHERE id = ?";
        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, accessoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
