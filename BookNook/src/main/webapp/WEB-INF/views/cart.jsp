<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Cart, model.CartItem" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Shopping Cart</title>
  
    <link rel="stylesheet" type="text/css" href="css/cart.css">
</head>
<body>
<div class="container">
    <h2>Your Cart</h2>
    <% Cart cart = (Cart) session.getAttribute("cart"); %>
    <% if (cart == null || cart.getItems().isEmpty()) { %>
        <p class="empty-cart">Your cart is empty.</p>
    <% } else { %>
        <% List<CartItem> items = cart.getItems(); %>
        <% for (CartItem item : items) { %>
            <div class="cart-item">
                <p><%= item.getName() %> - Quantity: <%= item.getQuantity() %> - Price: £<%= item.getPrice() * item.getQuantity() %></p>
                <form action="CartServlet" method="post">
                    <input type="hidden" name="productId" value="<%= item.getProductId() %>">
                    <input type="hidden" name="type" value="<%= item.getType() %>">
                    <input type="hidden" name="remove" value="true">
                    <button type="submit" class="remove-btn">Remove</button>
                </form>
            </div>
        <% } %>
        <p class="total">Total Price: £<%= cart.getTotalPrice() %></p>
    <% } %>
    <a href="HomeServlet" class="continue-shopping">Continue Shopping</a>
    <p class="warning-message">***If you want to buy selected items - LOGIN TO YOUR ACCOUNT!***</p>
</div>
</body>
</html>