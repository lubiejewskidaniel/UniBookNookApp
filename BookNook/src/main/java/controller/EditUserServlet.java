package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/EditUserServlet") // URL mapping for this servlet
public class EditUserServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO(); // DAO instance for database operations

    /**
     * Handles GET requests (when the admin opens the edit user page).
     * It fetches the user details based on the given user ID and forwards them to editUser.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the user ID from the request parameters
            int userId = Integer.parseInt(request.getParameter("id"));

            // Fetch user details from the database
            User user = userDAO.getUserById(userId);

            if (user == null) {
                // If the user is not found, send an error message
                response.getWriter().write("<p class='text-danger'>User not found.</p>");
                return;
            }

            // Attach the user details to the request for displaying in the form
            request.setAttribute("user", user);

            // Forward the request to editUser.jsp where the admin can update user details
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/editUser.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            // Handle invalid user ID format
            response.getWriter().write("<p class='text-danger'>Invalid user ID.</p>");
        } catch (Exception e) {
            // Handle general errors while retrieving user details
            response.getWriter().write("<p class='text-danger'>Error retrieving user details.</p>");
        }
    }

    /**
     * Handles POST requests (when the admin submits the form to update user details).
     * It validates input, updates the user record in the database, and redirects with success or error messages.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve user ID from the form
            int userId = Integer.parseInt(request.getParameter("id"));

            // Retrieve updated user details from the form
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            String phone = request.getParameter("phone");
            String balanceStr = request.getParameter("balance");

            // Debugging log
            System.out.println("Received update request for user ID: " + userId);

            // Fetch the existing user details from the database
            User existingUser = userDAO.getUserById(userId);

            if (existingUser == null) {
                // If user does not exist, redirect with an error message
                System.err.println("Error: User with ID " + userId + " not found.");
                response.sendRedirect("ViewUsersServlet?error=User not found");
                return;
            }

            // Update user details only if the values are provided (non-empty)
            if (username != null && !username.trim().isEmpty()) {
                existingUser.setUsername(username);
            }
            if (password != null && !password.trim().isEmpty()) {
                existingUser.setPassword(password);
            }
            if (email != null && !email.trim().isEmpty()) {
                existingUser.setEmail(email);
            }
            if (role != null && !role.trim().isEmpty()) {
                existingUser.setRole(role);
            }
            if (phone != null && !phone.trim().isEmpty()) {
                existingUser.setPhone(phone);
            }
            if (balanceStr != null && !balanceStr.trim().isEmpty()) {
                try {
                    double balance = Double.parseDouble(balanceStr);
                    existingUser.setBalance(balance);
                } catch (NumberFormatException e) {
                    // Handle invalid balance input
                    System.err.println("Invalid balance value provided: " + balanceStr);
                    response.sendRedirect("ViewUsersServlet?error=Invalid balance value");
                    return;
                }
            }

            // Update the user in the database
            userDAO.updateUser(existingUser);
            System.out.println("User updated successfully in database.");

            // Fetch the updated user details and store them in the session
            HttpSession session = request.getSession();
            session.setAttribute("user", userDAO.getUserById(userId));
            System.out.println("Session updated for user ID: " + userId);

            // Redirect to the user list with a success message
            response.sendRedirect("ViewUsersServlet?success=User updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            // Redirect with an error message if an exception occurs
            response.sendRedirect("ViewUsersServlet?error=Failed to update user");
        }
    }
}