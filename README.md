# 📚 Library Management System (Java + MySQL)

## 📌 Overview
A desktop library management system built using Java Swing and MySQL to manage books, authors, members, and borrowing operations.  
The system supports full CRUD operations with a connected database using JDBC.

## 🛠️ Technologies Used
- Java (Swing GUI)
- MySQL
- JDBC
- Maven

## ✨ Features
- Add new books
- Update existing books
- Delete books
- View all books
- Manage authors, customers, and borrowing records

## 📸 Screenshot
<img width="915" height="800" alt="GUI" src="https://github.com/user-attachments/assets/b697367b-44d8-4f3d-a0c5-5c8e4c7adac2" />

## 🗄️ Database Schema
The system uses five main tables:
- `books`: Stores book details (title, category, author).
- `authors`: Stores author information.
- `library`: Stores library branch details.
- `customers`: Stores member information.
- `borrow`: Tracks borrowing transactions.

## 🚀 How to Run

### 1. Clone the repository
```bash```
git clone https://github.com/Rawdaa-A/Library-Management-System-Java.git

### 2. Database Setup
- Install **MySQL Server** and **Workbench**.
- Create a database: `CREATE DATABASE library_db;`.
- Use the provided `.csv` files in the `data/` folder to import data into the tables (or run the `.sql` script if available).

### 3. Database Connection
- Open the project in your IDE (IntelliJ or Eclipse).
- Navigate to the database connection class (e.g., `DBConnection.java`).
- Update your MySQL **username** and **password**:
  ```java
  String url = "jdbc:mysql://localhost:3306/library_db";
  String user = "your_username";
  String pass = "your_password";

### 4. Run the Application
Run the Main.java file (or the file containing the public static void main method).

The Library Management System GUI should appear.
