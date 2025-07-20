// Nothing changed - adpted fully
package model; // Daniel Lubiejewski all in here

// The Product class serves as a base (abstract) class for all types of products in the system.
// It defines common properties like name, price, and sales, which are shared across all products.
// The class also implements Comparable, allowing products to be sorted alphabetically by name.
public abstract class Product implements Comparable<Product> {
    // Comon felds declarations wich can be used in Book and Accessory class.
    String name;
    double price;
    private int sales;

    // Constructor to initialize the product's name and price.
    // By default, sales I set to 0 when a product is created.
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.sales = 0;
    }

    // Again Getters and Setters to deal with the privite fields in a controll way
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getSales() {
        return sales;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) { // This Setter allows the app to show price to users and use it in calculations
        this.price = price;
    }

    public void setSales(int sales) { // This Setter allows can show clearly which products how performing on the market showing its selaes dynamically.
        this.sales = sales;
    }

    // Overriding the compareTo method from the Comparable interface.
    // This is part of my personal studies to understand how to override methods for sorting.
    // Here, I'm making sure products are compared alphabetically by their name, ignoring case.
    // It works by using the built-in `compareToIgnoreCase` method, which compares two strings
    // based on their dictionary order without considering case differences.
    // This makes it easier to create sorted product lists that look organized for users. It can be use then 
    // anuwhere where this kind of sorting is required in the app.
    @Override
    public int compareTo(Product other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}