package controller;

import DAO.AccessoryDAO;
import jakarta.servlet.RequestDispatcher;
import model.Accessory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddAccessoryServlet") // Defines the URL mapping for this servlet
public class AddAccessoryServlet extends HttpServlet {

    // Handles GET requests (when the user visits the page to add an accessory)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the JSP form for adding an accessory
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/insertAccessory.jsp");
        dispatcher.forward(request, response);
    }

    // Handles POST requests (when the user submits the form to add an accessory)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form data from the request (submitted by the user)
            String name = request.getParameter("name"); // Accessory name
            String description = request.getParameter("description"); // Description of the accessory
            double price = Double.parseDouble(request.getParameter("price")); // Convert price to double
            int quantity = Integer.parseInt(request.getParameter("quantity")); // Convert quantity to integer
            String image = request.getParameter("image"); // Image URL or file path
            int reservedStock = 0;

            // Create a new Accessory object with the retrieved data
            Accessory accessory = new Accessory(name, description, price, quantity, image, reservedStock);

            // Save the new accessory into the database using DAO
            AccessoryDAO accessoryDAO = new AccessoryDAO();
            boolean isAdded = accessoryDAO.insertAccessory(accessory);

            if (isAdded) {
                // If insertion is successful, redirect to the page that displays all books and accessories
                response.sendRedirect("ViewBooksAccessoriesServlet");
            } else {
                // If insertion fails, send an error message and reload the form
                request.setAttribute("errorMessage", "Failed to add accessory.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/insertAccessory.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            // If there's an error (like invalid input), show an error message on the form
            request.setAttribute("errorMessage", "Invalid input: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/insertAccessory.jsp");
            dispatcher.forward(request, response);
        }
    }
}