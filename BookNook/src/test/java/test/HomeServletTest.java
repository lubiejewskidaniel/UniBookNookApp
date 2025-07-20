//package test;
//
//import controller.HomeServlet;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import jakarta.servlet.ServletException;
//
//import java.io.IOException;
//import java.util.List;
//
//import model.Book;
//import model.Accessory;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class HomeServletTest {
//
//    private HomeServlet homeServlet;
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//    private RequestDispatcher requestDispatcher;
//
//    @BeforeEach
//    public void setUp() {
//        // Inicjalizacja serwletu
//        homeServlet = new HomeServlet();
//
//        // Mock obiektów servletowych
//        request = mock(HttpServletRequest.class);
//        response = mock(HttpServletResponse.class);
//        requestDispatcher = mock(RequestDispatcher.class);
//
//        // Mock zachowania request dispatcher
//        when(request.getRequestDispatcher("/WEB-INF/views/index.jsp")).thenReturn(requestDispatcher);
//    }
//
//    @Test
//    public void testDoGetFetchesRealData() throws IOException, ServletException {
//        // Wywołanie metody doGet
//        homeServlet.doGet(request, response);
//
//        // Pobieranie atrybutów z request
//        List<Book> books = (List<Book>) request.getAttribute("books");
//        List<Accessory> accessories = (List<Accessory>) request.getAttribute("accessories");
//
//        // Walidacja danych
//        assertNotNull(books, "Books list should not be null.");
//        assertFalse(books.isEmpty(), "List of books .");
//        assertNotNull(accessories, "Lista akcesoriów nie powinna być null.");
//        assertFalse(accessories.isEmpty(), "Lista akcesoriów nie powinna być pusta.");
//
//        // Sprawdzenie logów dla debugowania
//        System.out.println("Books: " + books);
//        System.out.println("Accessories: " + accessories);
//
//        // Sprawdzenie, czy przekazano żądanie do widoku
//        verify(requestDispatcher).forward(request, response);
//    }
//
//}
