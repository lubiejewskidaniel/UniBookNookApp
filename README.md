# 📚 UniBookNook – University Bookstore Web Application

**UniBookNook** is a dynamic Java-based web application designed for managing a university-level bookstore platform. This project demonstrates practical application of Java EE technologies including Servlets, JSP, JDBC, MVC architecture, and SQL-based persistence.

🗓️ **Developed:** 2025  
🛠️ **Tech Stack:** Java, JSP, Servlets, JDBC, SQLite, HTML, CSS, JavaScript


## 📌 Table of Contents

- [Project Overview](#-project-overview)
- [Features](#-features)
- [Project Structure](#-project-structure)
- [Database Configuration](#️-database-configuration)
- [Diagrams Included](#-diagrams-included)
- [Testing](#-testing)
- [Key Technologies](#-key-technologies)
- [Future Improvements](#-future-improvements)
- [Contact](#-contact)


---

## 🌟 Project Overview

The UniBookNook platform allows students and administrators to interact with an online bookstore system. Students can browse and purchase books and accessories, while admins can manage inventory, users, and orders.

This full-stack solution was designed from the ground up as part of an academic project and showcases solid backend integration with a modular, extensible frontend.

---

## 🚀 Features

### 👤 Student Features
- User registration & login
- Book and accessory browsing
- Shopping cart functionality
- Order checkout with summary
- Order history view

### 🔐 Admin Features
- Dashboard with quick stats
- Add/edit/delete books & accessories
- View/delete users
- View all orders and user order history
- Search functionality

---

## 🗂️ Project Structure

```bash
BookNook/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controller/         # Servlets for user/admin flow
│   │   │   ├── DAO/                # Data access objects for database logic
│   │   │   └── model/              # POJOs representing business objects
│   │   ├── resources/
│   │   │   ├── config.properties   # DB config
│   │   │   └── databases/booknook.db  # SQLite DB (manual path setting required)
│   │   └── webapp/
│   │       ├── css/               # Stylesheets
│   │       ├── js/                # Client-side JavaScript
│   │       ├── images/           # Book & accessory images
│   │       └── WEB-INF/views/     # JSP pages for UI
├── pom.xml                        # Maven dependencies
└── README.md                      # This documentation
```

---

## ⚠️ Database Configuration

> **Important Note:**  
Due to an unresolved issue with relative path handling in some IDE environments (e.g., NetBeans), this project currently uses an **absolute path** to access the database.

🛠 To run the application:
- Open the project in your IDE (NetBeans recommended)
- Navigate to `JdbcDAO.java` or `config.properties`
- Replace the path to:
  ```
  BookNook/src/main/resources/databases/booknook.db
  ```
  with the full **absolute path** on your machine.

✅ Example (Windows):
```java
String dbPath = "C:/Users/YourName/Documents/UniBookNookApp-main/BookNook/src/main/resources/databases/booknook.db";
```

---

## 📷 Diagrams Included

📁 `Diagrams/` contains:
- Use Case Diagrams
- Sequence Diagrams
- Robustness Diagrams

These visually document functional flows like login, book search, and admin management.

---

## 🧪 Testing

Basic unit testing classes are located under:

```bash
src/test/java/test/
├── DAOTest.java
├── HomeServletTest.java
└── JDBCTest.java
```

---

## 📌 Key Technologies

| Layer            | Technology                        |
|------------------|------------------------------------|
| Backend Logic     | Java, Servlets, DAO Pattern       |
| Frontend View     | JSP, HTML, CSS, JavaScript        |
| Data Persistence  | JDBC, SQLite                      |
| Build Tool        | Maven                             |
| Architecture      | MVC (Model-View-Controller)       |
| Deployment        | Apache Tomcat / NetBeans          |

---

## 🧭 Future Improvements

- Implement dynamic DB path resolution via ServletContext
- Integrate email confirmation or order notification
- Add user registration validation feedback
- Refactor styles to use a CSS framework (e.g., Bootstrap)

---

## 📬 Contact

Feel free to reach out if you're hiring or want to collaborate:

📧 **LubiejewskiDaniel@gmail.com**  
🔗 [GitHub Profile](https://github.com/lubiejewskidaniel)

---

Thank you for exploring this project! 🚀
