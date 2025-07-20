// CartItem.java - Represents an item in the shopping cart
package model;

public class CartItem {
    private int productId;
    private String name;
    private double price;
    private int quantity;
    private String type; 

    public CartItem(int productId, String name, double price, int quantity, String type) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getType() { return type; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}