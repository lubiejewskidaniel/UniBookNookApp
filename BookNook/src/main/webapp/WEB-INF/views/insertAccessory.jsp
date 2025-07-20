<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Accessory</title>
    
     <link rel="stylesheet" type="text/css" href="/BookNook/css/insert.css">
</head>
<body>
    <h1>Add a New Accessory</h1>
    <form action="AddAccessoryServlet" method="post">
        <label>Accessory Name:</label>
        <input type="text" name="name" required><br>
        <label>Description:</label>
        <input type="text" name="description" required><br>
        <label>Price:</label>
        <input type="number" name="price" step="0.01" required><br>
        <label>Quantity:</label>
        <input type="number" name="quantity" required><br>
        <label>Image URL:</label>
        <input type="text" name="image" placeholder="images/accessories/..."><br>
        <button type="submit">Add Accessory</button>
    </form>
    
    <a href="<%= request.getContextPath() %>/AdminDashboardServlet" class="btn btn-primary mb-3">Back to Dashboard</a>
    
    
</body>
</html>