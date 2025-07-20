package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.UserDAO;
import model.User;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    // Handles GET requests (displaying the login page)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        dispatcher.forward(request, response);
    }

    // Handles POST requests (processing login data)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve login form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Call DAO to verify the user credentials
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            // Create session and store user object
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Check the user role and redirect accordingly
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                // Redirect to the admin dashboard
                response.sendRedirect("AdminDashboardServlet");
            } else {
                // Redirect to the regular user dashboard
                response.sendRedirect("UserDashboardServlet");
            }
        } else {
            // Login failed - send an error message to the JSP page
            request.setAttribute("errorMessage", "Invalid username or password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}