
package controller;


import DAO.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import model.User;

@WebServlet("/ViewUsersServlet")
public class ViewUsersServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> Users = userDAO.getAllUsers();
            request.setAttribute("sortedUsers", Users);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/viewUsers.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving users: " + e.getMessage());
        }
    }
}