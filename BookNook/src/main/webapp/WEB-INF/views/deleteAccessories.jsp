<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Accessory" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Accessories</title>
    
    <link rel="stylesheet" type="text/css" href="/BookNook/css/deleteBooksAccessories.css">
</head>

<body>
    <h2>Delete Accessories</h2>

    <!-- Form to submit selected accessories for deletion -->
    <form action="DeleteAccessoriesPageServlet" method="post" onsubmit="return confirmDelete();">
        <table border="1">
            <tr>
                <th>SELECT</th>
                <th>ID</th>
                <th>NAME</th>
                <th>DESCRIPTION</th>
                <th>PRICE</th>
            </tr>

            <%
                // Retrieve the list of accessories from the request attribute
                List<Accessory> accessoriesList = (List<Accessory>) request.getAttribute("accessoriesList");

                if (accessoriesList == null || accessoriesList.isEmpty()) {
            %>
                <!-- If no accessories are available, display a message -->
                <tr>
                    <td colspan="5" style="text-align: center; color: red;">No accessories available</td>
                </tr>
            <%
                } else {
                    // Loop through each accessory and display its details in a table row
                    for (Accessory accessory : accessoriesList) {
            %>
                <tr>
                    <!-- Checkbox for selecting accessories to delete -->
                    <td><input type="checkbox" name="accessoryId" value="<%= accessory.getId() %>"></td>
                    <td><%= accessory.getId() %></td>
                    <td><%= accessory.getName() %></td>
                    <td><%= accessory.getDescription() %></td>
                    <td><%= accessory.getPrice() %></td>
                </tr>
            <%
                    }
                }
            %>
        </table>

        <!-- Button to submit the form and delete selected accessories -->
        <input type="submit" value="Delete Selected Accessories">
    </form>

    <!-- Button to go back to the Admin Dashboard -->
    <a href="<%= request.getContextPath() %>/AdminDashboardServlet" class="btn btn-primary mb-3">Back to Dashboard</a>
    
<script src="js/confirmDelete.js"></script>
</body>
</html>