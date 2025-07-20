<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Book</title>
    
     <link rel="stylesheet" type="text/css" href="/BookNook/css/insert.css">
</head>
<body>
    <h1>Add a New Book</h1>
    <form action="AddBookServlet" method="post">
        <label>Book Name:</label>
        <input type="text" name="name" required><br>
        <label>Price:</label>
        <input type="number" name="price" step="0.01" required><br>
        <label>Author:</label>
        <input type="text" name="author" required><br>
        <label>Category:</label>
        <input type="text" name="category" required><br>
        <label>Quantity:</label>
        <input type="number" name="quantity" required><br>
        <label>Image URL:</label>
        <input type="text" name="image" placeholder="images/books/..."><br>
        <button type="submit">Add Book</button>
    </form>
    
    <a href="<%= request.getContextPath() %>/AdminDashboardServlet" class="btn btn-primary mb-3">Back to Dashboard</a>
    
    
</body>
</html>