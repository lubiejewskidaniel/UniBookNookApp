package controller;

import DAO.BookDAO;
import model.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteBooksPageServlet") // URL mapping for this servlet
public class DeleteBooksPageServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO(); // DAO instance to interact with the database

    // Handles GET requests (when the page is loaded)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all books from the database
        List<Book> books = bookDAO.getAllBooks();
        
        // Attach the list of books to the request (so it can be displayed in JSP)
        request.setAttribute("booksList", books);

        // Forward the request to deleteBooks.jsp for displaying the books
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/deleteBooks.jsp");
        dispatcher.forward(request, response);
    }

    // Handles POST requests (when the user submits a delete request)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the selected book IDs from the form submission
        String[] selectedBookIds = request.getParameterValues("bookId");

        if (selectedBookIds != null) {
            // Loop through selected IDs and delete each book from the database
            for (String bookId : selectedBookIds) {
                bookDAO.deleteBook(Integer.parseInt(bookId));
            }
        }

        // Redirect back to the same page to refresh the book list after deletion
        response.sendRedirect("DeleteBooksPageServlet");
    }
}