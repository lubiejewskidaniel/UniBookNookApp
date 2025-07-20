<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
    User user = (User) request.getAttribute("user");
    if (user == null) {
%>
    <p class="text-danger">Error: User data is not available.</p>
<%
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/BookNook/css/editUser.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Edit User</h2>
        
        <form action="EditUserServlet" method="post">
            <input type="hidden" name="id" value="<%= user.getId() %>">
            
            <!-- Username Field -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" 
                       value="<%= user.getUsername() %>" placeholder="Enter new username">
                <small class="form-text text-muted">Leave blank to keep the current username.</small>
            </div>

            <!-- Password Field -->
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" 
                       value="<%= user.getPassword() %>" accept=""placeholder="Enter new password">
                <small class="form-text text-muted">Leave blank to keep the current password.</small>
            </div>

            <!-- Email Field -->
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" 
                       value="<%= user.getEmail() %>" placeholder="Enter new email address">
                <small class="form-text text-muted">Leave blank to keep the current email.</small>
            </div>

            <!-- Role Selection -->
            <div class="mb-3">
                <label for="role" class="form-label">Role</label>
                <select class="form-control" id="role" name="role">
                    <option value="ADMIN" <%= "ADMIN".equals(user.getRole()) ? "selected" : "" %>>ADMIN</option>
                    <option value="USER" <%= "USER".equals(user.getRole()) ? "selected" : "" %>>USER</option>
                </select>
                <small class="form-text text-muted">Choose a role.</small>
            </div>

            <!-- Phone Field -->
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" 
                       value="<%= user.getPhone() %>" placeholder="Enter new phone number">
                <small class="form-text text-muted">Leave blank to keep the current phone number.</small>
            </div>

            <!-- Balance Field -->
            <div class="mb-3">
                <label for="balance" class="form-label">Balance</label>
                <input type="number" step="0.01" class="form-control" id="balance" name="balance" 
                       value="<%= user.getBalance() %>" placeholder="Enter new balance amount">
                <small class="form-text text-muted">Leave blank to keep the current balance.</small>
            </div>

            <!-- Action Buttons -->
            <button type="submit" class="btn btn-success">Save Changes</button>
            <button type="button" class="btn btn-danger" id="closeEditSection">Cancel</button>
        </form>
    </div>
                
    <a href="<%= request.getContextPath() %>/AdminDashboardServlet" class="btn btn-primary mb-3">Back to Dashboard</a>

     
     
<script>
    // Handles cancel button logic
    document.getElementById('closeEditSection').addEventListener('click', function() {
        document.getElementById('editSection').innerHTML = '';
    });
</script>
</body>
</html>