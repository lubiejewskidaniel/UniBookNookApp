package model;

public class Order {
    private int id;
    private int userId;
    private String username;
    private double totalAmount;
    private String orderDate;

    public Order(int id, int userId, double totalAmount, String orderDate) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }
    
    public void setUsername(String username) {
    this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
