package controller;

import DAO.JdbcDAO;
import model.Order;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AdminOrdersServlet") // New endpoint for admin orders
public class AdminOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user");

        // Ensure only admins can access this
        if (admin == null || !"ADMIN".equalsIgnoreCase(admin.getRole())) {
            response.sendRedirect("LoginServlet");
            return;
        }

        // Fetch all orders
        List<Order> allOrders = getAllOrders();
        request.setAttribute("allOrders", allOrders);

        // Forward to JSP page
        request.getRequestDispatcher("/WEB-INF/views/adminOrders.jsp").forward(request, response);
    }

    private List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        
        String query = "SELECT Orders.id, Orders.user_id, Users.username AS username, Orders.total_amount, Orders.order_date " +
               "FROM Orders " +
               "JOIN Users ON Orders.user_id = Users.id " +
               "ORDER BY Orders.order_date DESC";
        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

           while (rs.next()) {
    Order order = new Order(
        rs.getInt("id"),      
        rs.getInt("user_id"), 
        rs.getDouble("total_amount"),
        rs.getString("order_date")
    );

    // Set user name separately using setter
    order.setUsername(rs.getString("username"));

    orders.add(order);
}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
}