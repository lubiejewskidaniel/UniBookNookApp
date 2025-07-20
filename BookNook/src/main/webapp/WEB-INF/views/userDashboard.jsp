<%@page import="model.Cart"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - <%= ((User) session.getAttribute("user")).getUsername() %></title>
    
    <link rel="stylesheet" type="text/css" href="/BookNook/css/index.css">
    <link rel="stylesheet" type="text/css" href="/BookNook/css/modalNoFuncionality.css">
    <style>
        
        /* Navigation Bar Styling */
        .menu {
            background: #007BFF; /* Professional Blue */
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        .menu ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            width: 100%;
            justify-content: space-between;
            align-items: center;
        }

        .menu li {
            display: inline-block;
            font-size: 16px;
            color: white;
            font-weight: bold;
        }

        /* Welcome Message */
        .welcome {
            color: white;
            font-size: 18px;
            font-weight: bold;
            text-transform: capitalize;
        }

        /* Logout Button */
        .logout-btn {
            background: #dc3545; /* Red color */
            color: white;
            padding: 8px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        .logout-btn:hover {
            background: #b02a37; /* Darker red on hover */
        }

   /* Main Dashboard Layout */
        .dashboard-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 30px;
            max-width: 1200px;
            margin: auto;
        }

        /* User Information Box */
        .user-info {
            width: 100%;
            background: #f9f9f9;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            margin-bottom: 30px;
        }

        .user-info h2 {
            color: #007BFF;
            font-size: 24px;
            margin-bottom: 10px;
        }

        .user-info p {
            font-size: 16px;
            color: #333;
            margin: 5px 0;
        }

        /* Cart Section */
        .cart-container {
            width: 100%;
            background: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.1);
        }

        /* Hide navigation and header inside cart.jsp when included here */
        .cart-container .header,
        .cart-container nav.menu {
            display: none !important;
        }

        /* Buttons Container */
        .buttons-container {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 10px; /* Small gap between buttons */
            margin-top: 15px;
        }

        /* Check Out Button (Large) */
        .checkout-btn {
            padding: 12px 25px;
            font-size: 16px;
            font-weight: bold;
            background: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .checkout-btn:hover {
            background: #218838;
        }

        /* View Order History Button (Small & Compact) */
        .history-btn {
            padding: 6px 10px;
            font-size: 14px;
            font-weight: bold;
            background: blue;
            color: white;
            border-radius: 45%;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            transition: background 0.3s ease;
        }

        .history-btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("LoginServlet");
        return;
    }
    Cart cart = (Cart) session.getAttribute("cart");
    double totalCartValue = (cart != null) ? cart.getTotalPrice() : 0;
    boolean insufficientBalance = totalCartValue > user.getBalance();
    String checkoutError = (String) session.getAttribute("checkoutError");
    session.removeAttribute("checkoutError"); // Clear message after displaying
%>

<div class="dashboard-container">
    <div class="user-info">
        <h2>Your Dashboard</h2>
        <p>Welcome, <%= user.getUsername() %>!</p>
        <p>Email: <%= user.getEmail() %></p>
        <p>Phone: <%= user.getPhone() %></p>
        <p>Role: <%= user.getRole() %></p>
        <p>Your Balance: £<%= user.getBalance() %></p>

        <% if (checkoutError != null) { %>
            <p class="checkout-error"><%= checkoutError %></p>
        <% } %>

        <!-- Buttons Container -->
        <div class="buttons-container">
            <form action="CheckoutServlet" method="post" style="display: inline;">
                <button type="submit" class="checkout-btn">Check Out</button>
            </form>

            <a href="OrdersServlet" class="history-btn">View Orders History</a>
            
           <a href="LogoutServlet" class="logout-btn"  onclick="return confirmLogout();">Log Out</a>
        </div>
        
      
    </div>
        
    <div class="cart-container">
        <jsp:include page="cart.jsp" />
    </div>
</div>

    <!-- Modal -->
    <div id="confirmLogout" class="modal">
        <div class="modal-content">
            <span class="close-btn" onclick="closeModal()">&times;</span>
            <h2>We are working on this functionality</h2>
            <p>The password recovery feature is under development. It will be available soon.</p>
        </div>
    </div>    
    
<script>
    function confirmLogout() {
        return confirm("Logging out will delete your cart. Are you sure?");
    }
</script>

<script>
        function openModal() {
            document.getElementById('confirmLogout').style.display = 'block';
        }

        function closeModal() {
            document.getElementById('confirmLogout').style.display = 'none';
        }

        // closing modal after clicking outside 
        window.onclick = function(event) {
            var modal = document.getElementById('confirmLogout');
            if (event.target === modal) {
                modal.style.display = 'none';
            }
        };
    </script>
</body>
</html>
