<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.User" %>

<%
    List<User> users = (List<User>) request.getAttribute("sortedUsers");
%>

<%
    String userId = request.getParameter("id");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/BookNook/css/viewUsers.css">
</head>
<body>

    <div class="container mt-5">
        <h1 class="text-center">User List</h1>
        <a href="<%= request.getContextPath() %>/AdminDashboardServlet" class="btn btn-primary mb-3">Back to Dashboard</a>

        <%
            String successMessage = request.getParameter("success");
            String errorMessage = request.getParameter("error");
        %>
        <% if (successMessage != null) { %>
            <div class="alert alert-success"><%= successMessage %></div>
        <% } %>
        <% if (errorMessage != null) { %>
            <div class="alert alert-danger"><%= errorMessage %></div>
        <% } %>

        <%
            if (users == null || users.isEmpty()) {
        %>
            <div class="alert alert-warning text-center">No users found in the system.</div>
        <%
            } else {
        %>

        <table class="table table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Phone</th>
                    <th>Balance</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (User user : users) {
                %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getUsername() %></td>
                    <td><%= user.getPassword() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getRole() %></td>
                    <td><%= user.getPhone() %></td>
                    <td><%= user.getBalance() %></td>
                    <td>
                        <a href="javascript:void(0);" class="btn btn-warning btn-sm edit-btn" data-id="<%= user.getId() %>">Edit</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <form action="DeleteUsersServlet" method="get">
            <input type="submit" value="Delete Users" class="btn btn-danger">
        </form>

        <div id="editSection"></div>

        <%
            }
        %>
    </div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.querySelectorAll('.edit-btn').forEach(button => {
            button.addEventListener('click', function (event) {
                event.preventDefault();
                let userId = this.getAttribute('data-id');
                window.location.href = "EditUserServlet?id=" + userId;
            });
        });
    });
</script>

</body>
</html>