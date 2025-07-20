<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Book, model.Accessory, java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="DAO.CartDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Nook App</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>

<div class="header">
    <div class="logo">Book <span>Nook</span></div>
</div>

<nav class="menu">
    
    <form action="SearchBookServlet" method="GET" class="search-form">
        <input type="text" name="query" placeholder="Search books by title or author..." required>
        <button type="submit">&#128270; Search</button>
    </form>
    <ul>
        <li><a href="HomeServlet">&#127980; Shop</a></li>
        <li><a href="CartServlet">&#128722; Cart</a></li>
        <li><a href="LoginServlet">&#128100; Login</a></li>
    </ul>
</nav>

<div class="container">
    <div class="section-title">Books</div>
    <div class="grid">
        <% List<Book> books = (List<Book>) request.getAttribute("books");
           if (books != null) {
               for (Book book : books) { 
                   int availableStock = CartDAO.getAvailableStock(book.getId(), "book");
        %>
        <div class="item">
            <img src="<%= book.getImage() %>" alt="Book Image">
            <h3><%= book.getName() %></h3>
            <p><%= book.getAuthor() %></p>
            <p><%= book.getCategory() %></p>
            <p>Price: £<%= book.getPrice() %></p>
            <p>Available: <%= availableStock %></p>
            <form action="CartServlet" method="post">
            <input type="hidden" name="productId" value="<%= book.getId() %>">
            <input type="hidden" name="name" value="<%= book.getName() %>">
            <input type="hidden" name="price" value="<%= book.getPrice() %>">
            <input type="hidden" name="quantity" value="1">
            <input type="hidden" name="type" value="book">
            <button type="submit" <%= availableStock == 0 ? "disabled" : "" %>>Add to Cart</button>
        </form>
        </div>
        <% } } %>
    </div>

    <div class="section-title">Accessories</div>
    <div class="grid">
        <% List<Accessory> accessories = (List<Accessory>) request.getAttribute("accessories");
           if (accessories != null) {
               for (Accessory accessory : accessories) { 
                   int availableStock = CartDAO.getAvailableStock(accessory.getId(), "accessory");
        %>
        <div class="item">
            <img src="<%= accessory.getImage() %>" alt="Book Image">
            <h3><%= accessory.getName() %></h3>
            <p>Price: £<%= accessory.getPrice() %></p>
            <p>Available: <%= availableStock %></p>
            <form action="CartServlet" method="post">
            <input type="hidden" name="productId" value="<%= accessory.getId() %>">
            <input type="hidden" name="name" value="<%= accessory.getName() %>">
            <input type="hidden" name="price" value="<%= accessory.getPrice() %>">
            <input type="hidden" name="quantity" value="1">
            <input type="hidden" name="type" value="accessory">
            <button type="submit" <%= availableStock == 0 ? "disabled" : "" %>>Add to Cart</button>
        </form>
        </div>
        <% } } %>
    </div>
</div>
</body>
</html>
