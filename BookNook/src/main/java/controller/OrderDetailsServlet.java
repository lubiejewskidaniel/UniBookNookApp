package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderItem;
import DAO.OrderDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/orderDetails")
public class OrderDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdParam = request.getParameter("orderId");

        if (orderIdParam != null) {
            try {
                int orderId = Integer.parseInt(orderIdParam);

                // Fetch order items from database
                List<OrderItem> orderItems = OrderDAO.getOrderItems(orderId);
                request.setAttribute("orderItems", orderItems);

                // Forward to orderDetails.jsp
                request.getRequestDispatcher("/WEB-INF/views/orderDetails.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendRedirect("orders"); // Redirect back if orderId is invalid
            }
        } else {
            response.sendRedirect("orders"); // Redirect back if orderId is missing
        }
    }
}
