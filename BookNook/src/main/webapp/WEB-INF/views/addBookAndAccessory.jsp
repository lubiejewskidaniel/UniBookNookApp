<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<%@ page import="model.Accessory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Books and Accessories</title>
    <link rel="stylesheet" type="text/css" href="/BookNook/css/addBookAndAccessory.css">
</head>
<body>

    <h1>Manage Books and Accessories</h1>

    <h2>Books</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
        <%
            List<Book> bookList = (List<Book>) request.getAttribute("bookList");
            if (bookList != null && !bookList.isEmpty()) {
                for (Book book : bookList) {
        %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getName() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getCategory() %></td>
            <td><%= book.getQuantity() %></td>
            <td><%= book.getPrice() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="6" style="text-align: center; color: red;">No Books Available</td>
        </tr>
        <%
            }
        %>
    </table>

    <br>

    <div class="button-group">
        <form action="AddBookServlet" method="get">
            <input type="submit" value="Add Book">
        </form>
        <form action="DeleteBooksPageServlet" method="get">
            <button type="submit">Delete Books</button>
        </form>
    </div>

    <br>

    <h2>Accessories</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
        <%
            List<Accessory> accessoryList = (List<Accessory>) request.getAttribute("accessoryList");
            if (accessoryList != null && !accessoryList.isEmpty()) {
                for (Accessory accessory : accessoryList) {
        %>
        <tr>
            <td><%= accessory.getId() %></td>
            <td><%= accessory.getName() %></td>
            <td><%= accessory.getDescription() %></td>
            <td><%= accessory.getQuantity() %></td>
            <td><%= accessory.getPrice() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" style="text-align: center; color: red;">No Accessories Available</td>
        </tr>
        <%
            }
        %>
    </table>

    <br>

    <div class="button-group">
        <form action="AddAccessoryServlet" method="get">
            <input type="submit" value="Add Accessory">
        </form>
        <form action="DeleteAccessoriesPageServlet" method="get">
            <button type="submit">Delete Accessories</button>
        </form>
    </div>

    <br>

    <a href="<%= request.getContextPath() %>/AdminDashboardServlet">Back to Dashboard</a>

</body>
</html>