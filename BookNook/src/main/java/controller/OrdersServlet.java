package controller;

import DAO.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Order;
import model.User;

@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp"); // Redirect if not logged in
            return;
        }

        // Fetch the user's order history
        List<Order> orders = OrderDAO.getOrdersByUser(user.getId());

        // Pass orders to JSP
        request.setAttribute("orders", orders);

        // Forward to orders.jsp
        request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
    }
}