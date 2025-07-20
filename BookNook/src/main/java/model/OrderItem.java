package model;

public class OrderItem {
    private int id;
    private int orderId;
    private int itemId;
    private String itemType;
    private int quantity;
    private double price;
    private String itemName;
    private String additionalInfo;

    public OrderItem(int id, int orderId, int itemId, String itemType, int quantity, double price, String itemName, String additionalInfo) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemType = itemType;
        this.quantity = quantity;
        this.price = price;
        this.itemName = itemName;
        this.additionalInfo = additionalInfo;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}