<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="/BookNook/css/login.css">
    <link rel="stylesheet" type="text/css" href="/BookNook/css/modalNoFuncionality.css">

</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form action="LoginServlet" method="post">
            <div class="input-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>
           <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null) { %>
            <p style="color:red; text-align:center;"><%= errorMessage %></p>
        <% } %>
        
        <div class="link">
            <a href="#" onclick="openModal()">Forgot Password?</a>
        </div>
    </div>
 


<!-- Modal -->
<div id="forgotPasswordModal" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeModal()">&times;</span>
        <h2>We are working on this functionality</h2>
        <p>The password recovery feature is under development. It will be available soon.</p>
    </div>
</div>

<script>
    function openModal() {
        document.getElementById('forgotPasswordModal').style.display = 'block';
    }

    function closeModal() {
        document.getElementById('forgotPasswordModal').style.display = 'none';
    }

    // closing modal after clicking outside 
    window.onclick = function(event) {
        var modal = document.getElementById('forgotPasswordModal');
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    };
</script>
</body>
</html>
