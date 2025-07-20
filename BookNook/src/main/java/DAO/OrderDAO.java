package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderItem;

public class OrderDAO {

    public static int createOrder(int userId, double totalAmount) {
        String query = "INSERT INTO Orders (user_id, total_amount) VALUES (?, ?)";
        int orderId = -1;

        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.setDouble(2, totalAmount);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public static void saveOrderItem(int orderId, int itemId, String itemType, int quantity, double price) {
        String query = "INSERT INTO Order_Items (order_id, item_id, item_type, quantity, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, itemId);
            stmt.setString(3, itemType);
            stmt.setInt(4, quantity);
            stmt.setDouble(5, price);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Order> getOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE user_id = ? ORDER BY order_date DESC";

        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getDouble("total_amount"),
                        rs.getString("order_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static List<OrderItem> getOrderItems(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        String query = "SELECT oi.id, oi.order_id, oi.item_id, oi.item_type, oi.quantity, oi.price, " +
                       "COALESCE(b.name, a.name) AS item_name, " +
                       "COALESCE(b.author, a.description) AS additional_info " +
                       "FROM Order_Items oi " +
                       "LEFT JOIN Books b ON oi.item_type = 'book' AND oi.item_id = b.id " +
                       "LEFT JOIN Accessories a ON oi.item_type = 'accessory' AND oi.item_id = a.id " +
                       "WHERE oi.order_id = ?";

        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                items.add(new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("item_id"),
                        rs.getString("item_type"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("item_name"),       
                        rs.getString("additional_info")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    
    
    
    
    
}