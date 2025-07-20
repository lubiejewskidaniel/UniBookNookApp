<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Books</title>
    
    <link rel="stylesheet" type="text/css" href="/BookNook/css/deleteBooksAccessories.css">
</head>
<body>
    <h2>Delete Books</h2>

    <form action="DeleteBooksPageServlet" method="post" onsubmit="return confirmDelete();">
        <table border="1">
            <tr>
                <th>Select</th>
                <th>ID</th>
                <th>TITLE</th>
                <th>AUTHOR</th>
                <th>CATEGORY</th>
                <th>PRICE</th>
            </tr>

            <%
                List<Book> booksList = (List<Book>) request.getAttribute("booksList");

                if (booksList == null || booksList.isEmpty()) {
            %>
                <tr>
                    <td colspan="6" style="text-align: center; color: red;">No Books in inventory!</td>
                </tr>
            <%
                } else {
                    for (Book book : booksList) {
            %>
                <tr>
                    <td><input type="checkbox" name="bookId" value="<%= book.getId() %>"></td>
                    <td><%= book.getId() %></td>
                    <td><%= book.getName() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getCategory() %></td>
                    <td><%= book.getPrice() %></td>
                </tr>
            <%
                    }
                }
            %>
        </table>

        <input type="submit" value="Delete selected Books">
    </form>
    <a href="<%= request.getContextPath() %>/AdminDashboardServlet" class="btn btn-primary mb-3">Back to Dashboard</a>
    
<script src="js/confirmDelete.js"></script>
</body>
</html>