<%@ page import="model.OrderItem, java.util.List" %>
<html>
<head>
    <title>Order Invoice</title>
   
    <link rel="stylesheet" type="text/css" href="css/orderDetails.css">
</head>
<body>
    <div class="container">
        <h2>Order Invoice</h2>
        <table>
            <tr>
                <th>Item</th>
                <th>Type</th>
                <th>Author/Description</th>
                <th>Quantity</th>
                <th>Price (Each)</th>
                <th>Total</th>
            </tr>
        <%
            List<OrderItem> orderItems = (List<OrderItem>) request.getAttribute("orderItems");
            double grandTotal = 0;

            if (orderItems != null && !orderItems.isEmpty()) {
                for (OrderItem item : orderItems) {
                    double total = item.getQuantity() * item.getPrice();
                    grandTotal += total;
        %>
            <tr>
                <td><%= item.getItemName() %></td>
                <td><%= item.getItemType().substring(0, 1).toUpperCase() + item.getItemType().substring(1) %></td>
                <td><%= item.getAdditionalInfo() %></td>
                <td><%= item.getQuantity() %></td>
                <td>£<%= String.format("%.2f", item.getPrice()) %></td>
                <td>£<%= String.format("%.2f", total) %></td>
            </tr>
        <%
                }
        %>
            <tr class="total-row">
                <td colspan="5" style="text-align: right;">Grand Total:</td>
                <td>£<%= String.format("%.2f", grandTotal) %></td>
            </tr>
        <%
            } else {
        %>
            <tr>
                <td colspan="6" style="text-align: center;">No items found for this order.</td>
            </tr>
        <%
            }
        %>
        </table>
               <!-- Back Button -->
        <button class="back-btn" onclick="goBack()">BACK to Order History</button>
    </div>
        
        
<script src="js/goBack.js"></script>
</body>
</html>