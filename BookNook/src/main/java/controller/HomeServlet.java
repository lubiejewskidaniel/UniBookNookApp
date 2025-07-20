package controller;

import DAO.BookDAO;
import DAO.AccessoryDAO;

import model.Accessory;
import model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch books and accessories
        BookDAO bookDAO = new BookDAO();
        AccessoryDAO accessoryDAO = new AccessoryDAO();

        List<Book> books = bookDAO.getAllBooks();
        List<Accessory> accessories = accessoryDAO.getAllAccessories();
        
         // Debugging statements to confirm data retrieval
        System.out.println("Books: " + books);
        System.out.println("Accessories: " + accessories);
        
            // Debugging statements
            System.out.println("Books fetched: " + books);
            System.out.println("Accessories fetched: " + accessories);

            // Confirm books and accessories are not null or empty
            if (books == null || books.isEmpty()) {
                System.out.println("No books available!");
            }
            if (accessories == null || accessories.isEmpty()) {
                System.out.println("No accessories available!");
            }
        
        // Set attributes for JSP
        request.setAttribute("books", books);
        request.setAttribute("accessories", accessories);

        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }


}
