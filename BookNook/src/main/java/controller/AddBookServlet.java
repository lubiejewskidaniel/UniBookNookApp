package controller;

import DAO.BookDAO;
import jakarta.servlet.RequestDispatcher;
import model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the form for adding books
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/insertBook.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get form parameters
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String author = request.getParameter("author");
            String category = request.getParameter("category");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String image = request.getParameter("image");
            int reservedStock = 0;

            // Create a new Book object
            Book book = new Book(name, price, author, category, quantity, image, reservedStock);

            // Save the book to the database
            BookDAO bookDAO = new BookDAO();
            boolean isAdded = bookDAO.insertBook(book);

            if (isAdded) {
                response.sendRedirect("ViewBooksAccessoriesServlet");
            } else {
                request.setAttribute("errorMessage", "Failed to add book.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/insertBook.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Invalid input: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/insertBook.jsp");
            dispatcher.forward(request, response);
        }
    }
}