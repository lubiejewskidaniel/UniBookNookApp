<%@ page import="java.util.List, model.Order" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Admin - All Orders</title>
    <link rel="stylesheet" type="text/css" href="css/adminOrders.css">
</head>
<body>
    <div class="container">
        <h2>All User Orders</h2>
        <table border="1">
            <tr>
                <th>Order ID</th>
                <th>User ID</th>
                <th>Total Amount</th>
                <th>Order Date</th>
            </tr>
            <%
                List<Order> orders = (List<Order>) request.getAttribute("allOrders");
                if (orders != null && !orders.isEmpty()) {
                    for (Order order : orders) {
            %>
                        <tr>
                            <td><%= order.getId() %></td>
                            <td><%= order.getUsername() %></td>
                            <td>£<%= order.getTotalAmount() %></td>
                            <td><%= order.getOrderDate() %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="4">No orders found.</td></tr>
            <%
                }
            %>
        </table>
        <button class="back-btn" onclick="goBack()">BACK to Dashboard</button>
    </div>
        

    
    
<script src="js/goBack.js"></script>
</body>
</html>