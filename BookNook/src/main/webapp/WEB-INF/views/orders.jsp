<%@ page import="model.Order, DAO.OrderDAO, java.util.List" %>
<html>
<head>
    <title>Order History</title>

    <link rel="stylesheet" type="text/css" href="css/orders.css">
</head>
<body>
    <div class="container">
        <h2>Your Order History</h2>
        <%
            model.User user = (model.User) session.getAttribute("user");
            if (user != null) {
                List<Order> orders = OrderDAO.getOrdersByUser(user.getId());
                if (orders != null && !orders.isEmpty()) {
                    for (Order order : orders) {
        %>
                        <div class="order">
                            <span><a href="orderDetails?orderId=<%= order.getId() %>">Order #<%= order.getId() %></a></span>
                            <span>Total: £<%= order.getTotalAmount() %></span>
                            <span>Date: <%= order.getOrderDate() %></span>
                        </div>
        <%
                    }
                } else {
        %>
                    <p>No orders found.</p>
        <%
                }
            } else {
        %>
            <p>Please log in to view your orders.</p>
        <%
            }
        %>

        <!-- Back Button -->
        <button class="back-btn" onclick="goBack()">BACK to Dashboard</button>
    </div>
        
    
<script src="js/goBack.js"></script>
</body>
</html>