package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.CartItem;
import DAO.CartDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Retrieve the cart from session
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart != null) {
                // Release reserved stock
                List<CartItem> items = cart.getItems();
                for (CartItem item : items) {
                    CartDAO.decreaseReservedStock(item.getProductId(), item.getType(), item.getQuantity());
                }
            }

            // Invalidate session after clearing reserved stock
            session.invalidate();
        }

        // Redirect to home page after logout
        response.sendRedirect("HomeServlet");
    }
}