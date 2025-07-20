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

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        String type = request.getParameter("type");

        // Check if the request is to remove an item
        if (request.getParameter("remove") != null) {
            // Get the current quantity of the item in the cart
            int quantity = cart.getItemQuantity(productId, type);

            // Update reserved stock in the database
            CartDAO.decreaseReservedStock(productId, type, quantity);

            // Remove item from session cart
            cart.removeItem(productId, type);
        } else {
            // Handle adding items to cart
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            CartDAO.increaseReservedStock(productId, type, quantity);

            CartItem item = new CartItem(productId, name, price, quantity, type);
            cart.addItem(item);
        }

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }
}