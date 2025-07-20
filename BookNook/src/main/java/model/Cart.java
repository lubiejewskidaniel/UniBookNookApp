// Cart.java - Manages the shopping cart
package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() { return items; }

    public void addItem(CartItem newItem) {
        for (CartItem item : items) {
            if (item.getProductId() == newItem.getProductId() && item.getType().equals(newItem.getType())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public void removeItem(int productId, String type) {
        items.removeIf(item -> item.getProductId() == productId && item.getType().equals(type));
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
    
    public int getItemQuantity(int productId, String type) {
        for (CartItem item : items) {
            if (item.getProductId() == productId && item.getType().equals(type)) {
                return item.getQuantity(); // Return the quantity of the matching item
            }
        }
        return 0; // Return 0 if item is not found
    }

}