// As part of indyvidual work I added image field and chenged constructor 
// getters and setters accordingly also added reservedStock

package model;

public class Book extends Product {
    private int id; // Unique identifier for the book
    private String author; // Author of the book
    private String category; // Genre/category of the book
    private int quantity; // Quantity of the book available
    private String image;
    private int reservedStock;
    
    // Constructor with all fields except id
    public Book(String name, double price, String author, String category, int quantity, String image, int reservedStock) {
        super(name, price); // Calls the parent Product constructor
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.image = image;
        this.reservedStock = reservedStock;
    }

    // Constructor with all fields, including id
    public Book(String name, double price, String author, String category, int quantity, int id, String image, int reservedStock) {
        super(name, price); // Calls the parent Product constructor
        this.id = id;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.image = image;
        this.reservedStock = reservedStock;
        
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public int getReservedStock() {
        return reservedStock;
    }

    public void setReservedStock(int reservedStock) {
        this.reservedStock = reservedStock;
    }

    // Optional: toString method for debugging
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity + '\'' +
                  ", image=" + image +
                '}';
    }
}