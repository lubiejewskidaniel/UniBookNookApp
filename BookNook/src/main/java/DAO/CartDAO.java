package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO {

    // Increase reserved_stock when adding to cart
    public static void increaseReservedStock(int productId, String type, int quantity) {
        String tableName = type.equals("book") ? "Books" : "Accessories";
        String query = "UPDATE " + tableName + " SET reserved_stock = reserved_stock + ? WHERE id = ?";

        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Decrease reserved_stock when removing from cart
    public static void decreaseReservedStock(int productId, String type, int quantity) {
        String tableName = type.equals("book") ? "Books" : "Accessories";
        String query = "UPDATE " + tableName + " SET reserved_stock = reserved_stock - ? WHERE id = ? AND reserved_stock >= ?";

        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get available stock (quantity - reserved_stock) for UI display
    public static int getAvailableStock(int productId, String type) {
        String tableName = type.equals("book") ? "Books" : "Accessories";
        String query = "SELECT quantity, reserved_stock FROM " + tableName + " WHERE id = ?";

        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                int reservedStock = rs.getInt("reserved_stock");
                return quantity - reservedStock; // Available stock
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // Update quantity after checkout (reduce by reserved stock and reset reserved stock)
    public static void finalizePurchase(int productId, String type) {
        String tableName = type.equals("book") ? "Books" : "Accessories";
        String query = "UPDATE " + tableName + " SET quantity = quantity - reserved_stock, reserved_stock = 0 WHERE id = ?";

        try (Connection conn = JdbcDAO.getConnection()) {
            conn.setAutoCommit(false);  // Rozpoczęcie transakcji

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, productId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit();  // Confirms transaction
                } else {
                    conn.rollback();  // if not changed - cancell changes
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}