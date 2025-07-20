<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Users</title>
    
    <link rel="stylesheet" type="text/css" href="/BookNook/css/deleteUser.css">
</head>
<body>

<h2>Select Users to Delete</h2>

<form action="DeleteUsersServlet" method="post">
    <table class="table">
        <tr>
            <th>Select</th>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
        <%
            List<model.User> usersList = (List<model.User>) request.getAttribute("usersList");
            if (usersList != null) {
                for (model.User user : usersList) {
        %>
            <tr>
                <td><input type="checkbox" name="userIds" value="<%= user.getId() %>"></td>
                <td><%= user.getId() %></td>
                <td><%= user.getUsername() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getRole() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>
    <button type="submit">Delete Selected Users</button>
</form>
    
    <a href="<%= request.getContextPath() %>/AdminDashboardServlet" class="btn btn-primary mb-3">Back to Dashboard</a>

</body>
</html>