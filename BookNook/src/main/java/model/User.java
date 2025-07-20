// added new field - balance - and updated construstors and added getters and setters
// changed balace from int to double in field constructor getter and setter.
package model;

// The User class describes application user with their basic details and role.
public class User {

    // Fields to store user details such as username, password, contact information, and role.
    private String username;
    private String password; 
    private String phone;
    private String email;
    private String role;
    private int id;
    private double balance;

    // Constructor to initialize a User object with the provided details.
    public User(int id, String username, String password, String email, String role, String phone,  double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        // Email and phone below I used in form of ternary operator to deal with optional 
        // fileds in my app by providing default messages when 'null' value is passed.
        this.email = email != null ? email : "Email address skipped.";
        this.role = role;
        this.phone = phone != null ? phone : "Phone number skipped.";
       
        this.balance = balance;
    }

  
    
    // Getters and Setters accordingly 
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRole() { // Getter for the user's role. This is really important for cooperating within 
        // role-based access and them features in the app.
        return role;
    }
    public void setPassword(String password) { // Setter which updates the password, validation and security matters willbe provided further (self-studies).
        this.password = password;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
      }
    
    public void setRole(String role) {
        this.role = role;
    }
}
