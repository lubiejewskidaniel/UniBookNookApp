package controller;

import DAO.AccessoryDAO;
import model.Accessory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteAccessoriesPageServlet") // URL mapping for this servlet
public class DeleteAccessoriesPageServlet extends HttpServlet {
    private final AccessoryDAO accessoryDAO = new AccessoryDAO(); // DAO instance to interact with the database

    // Handles GET requests (when the page is loaded)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all accessories from the database
        List<Accessory> accessories = accessoryDAO.getAllAccessories();
        
        // Attach the list of accessories to the request (so it can be displayed in JSP)
        request.setAttribute("accessoriesList", accessories);

        // Forward the request to deleteAccessories.jsp for displaying accessories
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/deleteAccessories.jsp");
        dispatcher.forward(request, response);
    }

    // Handles POST requests (when the user submits a delete request)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the selected accessory IDs from the form submission
        String[] selectedAccessoryIds = request.getParameterValues("accessoryId");

        if (selectedAccessoryIds != null) {
            // Loop through selected IDs and delete each accessory from the database
            for (String accessoryId : selectedAccessoryIds) {
                accessoryDAO.deleteAccessory(Integer.parseInt(accessoryId));
            }
        }

        // Redirect back to the same page to refresh the accessory list after deletion
        response.sendRedirect("DeleteAccessoriesPageServlet");
    }
}