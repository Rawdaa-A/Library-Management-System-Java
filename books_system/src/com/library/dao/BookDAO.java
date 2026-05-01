package com.library.dao;

import com.library.model.Book;
import com.library.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    
    // Insert a new book.
    public void insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (books_id, title, category, authors_id, library_id) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, book.getBooksId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getCategory());
            pstmt.setInt(4, book.getAuthorsId());
            pstmt.setInt(5, book.getLibraryId());
            pstmt.executeUpdate();
        }
    }

    /*
       show all books with their authors' names.
     */
    public List<Object[]> selectAllBooks() throws SQLException {
        List<Object[]> rows = new ArrayList<>();
        String sql = "SELECT b.books_id, b.title, b.category, a.name AS author_name, b.authors_id, b.library_id "
                   + "FROM books b "
                   + "LEFT JOIN authors a ON b.authors_id = a.authors_id";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rows.add(new Object[]{
                    rs.getInt("books_id"),
                    rs.getString("title"),
                    rs.getString("category"),
                    rs.getString("author_name"),
                    rs.getInt("authors_id"),
                    rs.getInt("library_id")
                });
            }
        }
        return rows;
    }

    /*
      Update title, category, authors_id and library_id for a given books_id.
     */
    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET title = ?, category = ?, authors_id = ?, library_id = ? "
                   + "WHERE books_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getCategory());
            pstmt.setInt(3, book.getAuthorsId());
            pstmt.setInt(4, book.getLibraryId());
            pstmt.setInt(5, book.getBooksId());
            pstmt.executeUpdate();
        }
    }

    /*
      Delete a book by its primary key.
     */
    public void deleteBook(int booksId) throws SQLException {
        String sql = "DELETE FROM books WHERE books_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, booksId);
            pstmt.executeUpdate();
        }
    }
}

