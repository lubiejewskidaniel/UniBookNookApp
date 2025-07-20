package test;

import DAO.BookDAO;
import DAO.AccessoryDAO;
import model.Book;
import model.Accessory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {

    @Test
    public void testGetAllBooks() {
        BookDAO bookDAO = new BookDAO();
        // Fetch all available books
        List<Book> books = bookDAO.getAllBooks();
        
        // Validating that the result is not null
        assertNotNull(books, "Books list should not be null");
        
        // Log books for debugging purposes
        if (books.isEmpty()) {
            System.out.println("No books found in the database.");
        } else {
            System.out.println("Books retrieved from the database:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
        
        // Example assertion to ensure the test fails if the list is empty
        assertFalse(books.isEmpty(), "Books list should not be empty");
    }

    @Test
    public void testGetAllAccessories() {
        AccessoryDAO accessoryDAO = new AccessoryDAO();
        // Fetch all available accessories
        List<Accessory> accessories = accessoryDAO.getAllAccessories();
        
        // Validating that the result is not null
        assertNotNull(accessories, "Accessories list should not be null");

        // Log accessories for debugging purposes
        if (accessories.isEmpty()) {
            System.out.println("No accessories found in the database.");
        } else {
            System.out.println("Accessories retrieved from the database:");
            for (Accessory accessory : accessories) {
                System.out.println(accessory);
            }
        }
        
        // Example assertion to ensure the test fails if the list is empty
        assertFalse(accessories.isEmpty(), "Accessories list should not be empty");
    }
}