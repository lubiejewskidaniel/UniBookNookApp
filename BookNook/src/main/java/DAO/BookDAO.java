package DAO;

import model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Retrieves all books from the database
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Books";

        // Using try-with-resources to automatically close database connections
        try (Connection connection = JdbcDAO.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Loop through the result set and create Book objects
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getString("image"),
                        rs.getInt("reserved_stock")
                );
                book.setId(rs.getInt("id")); // Setting the book ID
                books.add(book);
                System.out.println("Fetched Book: " + book); // Debugging log
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving books: " + e.getMessage());
        }
        return books; // Return the list of books
    }

    // Retrieves a single book by its ID
    public Book getBookById(int id) {
        Book book = null;
        String query = "SELECT * FROM Books WHERE id = ?";

        // Using prepared statement to prevent SQL injection
        try (Connection connection = JdbcDAO.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id); // Set the book ID parameter

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    book = new Book(
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("author"),
                            rs.getString("category"),
                            rs.getInt("quantity"),
                            rs.getString("image"),
                            rs.getInt("reserved_stock")
                    );
                    book.setId(rs.getInt("id")); // Assign the retrieved ID
                    System.out.println("Fetched Book: " + book); // Debugging log
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving book by ID: " + e.getMessage());
        }
        return book; // Return the found book or null if not found
    }

    // Updates the quantity of a book in the database
    public boolean updateBookQuantity(int bookId, int quantity) {
        // Decrease the quantity only if enough stock is available
        String query = "UPDATE Books SET quantity = quantity - ? WHERE id = ? AND quantity >= ?";

        try (Connection connection = JdbcDAO.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, quantity); // Amount to decrease
            pstmt.setInt(2, bookId);   // Book ID to update
            pstmt.setInt(3, quantity); // Ensure there's enough stock before updating

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // If at least one row is affected, return true
        } catch (SQLException e) {
            System.err.println("Error updating book quantity: " + e.getMessage());
            return false;
        }
    }

    // Deletes a book from the database
    public boolean deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE id = ?";

        try (Connection connection = JdbcDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bookId); // Set the book ID to delete

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // If at least one row was deleted, return true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Inserts a new book into the database
    public boolean insertBook(Book book) {
        String query = "INSERT INTO books (name, price, author, category, quantity, image, reserved_stock) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = JdbcDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set values for the new book entry
            preparedStatement.setString(1, book.getName());
            preparedStatement.setDouble(2, book.getPrice());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getCategory());
            preparedStatement.setInt(5, book.getQuantity());
            preparedStatement.setString(6, book.getImage());
            preparedStatement.setInt(7, book.getReservedStock());

            return preparedStatement.executeUpdate() > 0; // Returns true if insert was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public void updateReservedStock(int bookId, int quantityChange) {
        String query = "UPDATE Books SET reserved_stock = reserved_stock + ? WHERE id = ?";

        try (Connection connection = JdbcDAO.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantityChange);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating reserved stock: " + e.getMessage());
        }
    }
     
    public void increaseAvailableStock(int bookId, int quantity) {
        String sql = "UPDATE books SET quantity = quantity + ? WHERE id = ?";
        try (Connection conn = JdbcDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Book> searchBooksByTitleOrAuthor(String query) {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books WHERE name LIKE ? OR author LIKE ?";

    try (Connection conn = JdbcDAO.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
        stmt.setString(1, "%" + query + "%");
        stmt.setString(2, "%" + query + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Book book = new Book(
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("author"),
                rs.getString("category"),
                rs.getInt("quantity"),
                rs.getString("image"),
                rs.getInt("reserved_stock")
            );
            book.setId(rs.getInt("id"));
            books.add(book);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return books;
}
}