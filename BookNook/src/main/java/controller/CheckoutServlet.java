package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.User;
import DAO.CartDAO;
import DAO.OrderDAO;
import DAO.UserDAO;
import java.io.IOException;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (cart == null || cart.getItems().isEmpty()) {
            request.getRequestDispatcher("/WEB-INF/views/userDashboard.jsp").forward(request, response); // No items, redirect back
            return;
        }

        double totalCartValue = cart.getTotalPrice();
        double userBalance = user.getBalance();

        // Check if balance is sufficient
        if (totalCartValue > userBalance) {
            session.setAttribute("checkoutError", "You do not have enough balance to complete this purchase.");
            request.getRequestDispatcher("/WEB-INF/views/userDashboard.jsp").forward(request, response);
            return;
        }

        // Deduct the amount from the user's balance
        double newBalance = userBalance - totalCartValue;
        user.setBalance(newBalance);

        // Update balance in the database
        UserDAO.updateUserBalance(user.getId(), newBalance);

        // Finalize purchase: Reduce stock and reset reserved stock in DB
        cart.getItems().forEach(item -> {
            CartDAO.finalizePurchase(item.getProductId(), item.getType());
        });
        
        // Save the order in the database
        int orderId = OrderDAO.createOrder(user.getId(), totalCartValue);

        // Save order items
        cart.getItems().forEach(item -> {
            OrderDAO.saveOrderItem(orderId, item.getProductId(), item.getType(), item.getQuantity(), item.getPrice());
        });

        // Clear the cart after checkout
        session.removeAttribute("cart");

        // Redirect to checkout success page
        request.getRequestDispatcher("/WEB-INF/views/checkoutSuccess.jsp").forward(request, response);
    }
}