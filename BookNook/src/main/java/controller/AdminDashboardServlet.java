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

@WebServlet("/AdminDashboardServlet") // URL mapping for this servlet
public class AdminDashboardServlet extends HttpServlet {

    // Handles GET requests (when the admin accesses the dashboard)
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Retrieve the current session (but don't create a new one if it doesn't exist)
    HttpSession session = request.getSession(false);

    // Check if the session exists and if a user is logged in
    if (session == null || session.getAttribute("user") == null) {
        // If not logged in, redirect to the login page with an error message
        response.sendRedirect("LoginServlet?error=Please login first");
        return;
    }

    // If the user is logged in, forward the request to the admin dashboard page
    request.getRequestDispatcher("/WEB-INF/views/adminDashboard.jsp").forward(request, response);
}


}