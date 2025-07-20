package controller;

import DAO.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteUsersServlet") // URL mapping for this servlet
public class DeleteUsersServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO(); // DAO instance to handle database operations

    /**
     * Handles GET requests (when an admin visits the user deletion page).
     * This method retrieves all users from the database and forwards them to deleteUsers.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of all users from the database
        List<User> users = userDAO.getAllUsers();
        
        // Attach the user list to the request so it can be displayed in JSP
        request.setAttribute("usersList", users);

        // Forward the request to deleteUsers.jsp (page where the admin can select users to delete)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/deleteUsers.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles POST requests (when an admin submits the form to delete users).
     * This method processes the selected user IDs and deletes them from the database.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the selected user IDs from the form submission
        String[] userIds = request.getParameterValues("userIds");

        if (userIds != null) { // Check if any users were selected
            // Loop through the selected user IDs and delete each user
            for (String id : userIds) {
                userDAO.deleteUserById(Integer.parseInt(id)); // Convert ID to integer and delete the user
            }
        }

        // After deletion, redirect back to the user list page to reflect the changes
        response.sendRedirect("ViewUsersServlet");
    }
}