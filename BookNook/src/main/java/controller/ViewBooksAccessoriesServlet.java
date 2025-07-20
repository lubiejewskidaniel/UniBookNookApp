package controller;

import DAO.BookDAO;
import DAO.AccessoryDAO;
import model.Book;
import model.Accessory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ViewBooksAccessoriesServlet")
public class ViewBooksAccessoriesServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();
    private final AccessoryDAO accessoryDAO = new AccessoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Book> bookList = bookDAO.getAllBooks();
            List<Accessory> accessoryList = accessoryDAO.getAllAccessories();
            
            request.setAttribute("bookList", bookList);
            request.setAttribute("accessoryList", accessoryList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/addBookAndAccessory.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving books and accessories: " + e.getMessage());
        }
    }
}
