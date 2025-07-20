// As part of indyvidual work I added image field and chenged 
// constructor getters and setters accordingly 
// also added reservedStock

package model;

public class Accessory extends Product {

    private int id; // Unique ID for the accessory
    private String description; // Description of the accessory
    private int quantity; // Quantity available
    private int reservedStock;
    
    private String image;

    // Constructor for Accessory with all fields except id
    public Accessory(String name, String description, double price, int quantity, String image, int reservedStock) {
        super(name, price); // Initialize name and price via Product constructor
        this.description = description; // Set description
        this.quantity = quantity; // Set quantity
        this.image = image;
        this.reservedStock = reservedStock;
    }

    // Constructor for Accessory with all fields, including id
    public Accessory(int id, String name, String description, double price, int quantity, String image, int reservedStock) {
        super(name, price); // Initialize name and price via Product constructor
        this.id = id; // Set ID
        this.description = description; // Set description
        this.quantity = quantity; // Set quantity
        this.image = image;
        this.reservedStock = reservedStock;
    }

    // Getter for the unique ID of the Accessory
    public int getId() {
        return id;
    }

    // Setter for the unique ID of the Accessory
    public void setId(int id) {
        this.id = id;
    }

    // Getter for the description
    public String getDescription() {
        return description;
    }

    // Setter for the description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for the quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for the quantity
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
        return "Accessory{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}