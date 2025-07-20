package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.BookDAO;
import DAO.AccessoryDAO;
import model.Book;
import model.Accessory;
import model.User;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserDashboardServlet") // URL mapping for this servlet
public class UserDashboardServlet extends HttpServlet {

    /**
     * Handles GET requests (when a user visits the dashboard).
     * This method checks if the user is logged in, fetches books and accessories from the database,
     * and forwards the data to userDashboard.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Retrieve the current session (but do not create a new one if it doesn’t exist)
        HttpSession session = request.getSession(false);
        
        // Get the logged-in user from the session
        User user = (User) session.getAttribute("user");

        // If no user is logged in, redirect them to the login page
        if (user == null) {
            response.sendRedirect("LoginServlet");
            return; // Stop further execution
        }

        // Fetch books from the database using BookDAO
        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.getAllBooks();

        // Fetch accessories from the database using AccessoryDAO
        AccessoryDAO accessoryDAO = new AccessoryDAO();
        List<Accessory> accessories = accessoryDAO.getAllAccessories();

        // Attach the books and accessories lists to the request so they can be displayed in the JSP
        request.setAttribute("books", books);
        request.setAttribute("accessories", accessories);

        // Forward the request to userDashboard.jsp, where the user will see available books and accessories
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/userDashboard.jsp");
        dispatcher.forward(request, response);
    }
}