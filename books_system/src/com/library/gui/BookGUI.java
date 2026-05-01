package com.library.gui;

import com.library.dao.BookDAO;
import com.library.model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class BookGUI extends JFrame {

    private JTextField idField, titleField, categoryField, authorsIdField, libraryIdField;
    private JButton addButton, updateButton, deleteButton, showButton;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private BookDAO bookDAO;

    public BookGUI() {
        super("Book Management System");
        bookDAO = new BookDAO();
        initComponents();
        loadBooks();
    }

    private void initComponents() {
        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Book ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("Author ID:"));
        authorsIdField = new JTextField();
        inputPanel.add(authorsIdField);

        inputPanel.add(new JLabel("Library ID:"));
        libraryIdField = new JTextField();
        inputPanel.add(libraryIdField);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        addButton    = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        showButton   = new JButton("Show All");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showButton);

        // Combine Input and Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        topPanel.setBorder(BorderFactory.createTitledBorder("Book Management"));

        // Table
        tableModel = new DefaultTableModel(
                new String[]{"Book ID", "Title", "Category", "Author Name", "Author ID", "Library ID"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        bookTable = new JTable(tableModel);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Hide Author ID column
        bookTable.getColumnModel().getColumn(4).setMinWidth(0);
        bookTable.getColumnModel().getColumn(4).setMaxWidth(0);
        bookTable.getColumnModel().getColumn(4).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setPreferredSize(new Dimension(650, 250)); // Set preferred size for table

        // Listeners
        addButton.addActionListener(e    -> addBook());
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
        showButton.addActionListener(e   -> loadBooks());

        bookTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && bookTable.getSelectedRow() != -1) {
                displaySelectedBook();
            }
        });

        // Main Layout 
        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER); // Table in center to fill space properly

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 650); // Increased height to accommodate both sections
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Book buildBookFromFields() {
        int    booksId   = Integer.parseInt(idField.getText().trim());
        String title     = titleField.getText().trim();
        String category  = categoryField.getText().trim();
        int    authorsId = Integer.parseInt(authorsIdField.getText().trim());
        int    libraryId = Integer.parseInt(libraryIdField.getText().trim());
        return new Book(booksId, title, category, authorsId, libraryId);
    }

    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        categoryField.setText("");
        authorsIdField.setText("");
        libraryIdField.setText("");
    }

    private void addBook() {
        try {
            Book book = buildBookFromFields();
            bookDAO.insertBook(book);
            JOptionPane.showMessageDialog(this, "Book Added Successfully!");
            clearFields();
            loadBooks();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Book ID, Author ID and Library ID must be integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBook() {
        try {
            Book book = buildBookFromFields();
            bookDAO.updateBook(book);
            JOptionPane.showMessageDialog(this, "Book Updated Successfully!");
            clearFields();
            loadBooks();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Book ID, Author ID and Library ID must be integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating book: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBook() {
        try {
            String idText = idField.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Book ID to delete.");
                return;
            }
            int id = Integer.parseInt(idText);
            bookDAO.deleteBook(id);
            JOptionPane.showMessageDialog(this, "Book Deleted Successfully!");
            clearFields();
            loadBooks();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Book ID must be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting book: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadBooks() {
        tableModel.setRowCount(0);
        try {
            List<Object[]> rows = bookDAO.selectAllBooks();
            for (Object[] row : rows) {
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading books: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displaySelectedBook() {
        int row = bookTable.getSelectedRow();
        if (row == -1) return;
        idField.setText(tableModel.getValueAt(row, 0).toString());
        titleField.setText(tableModel.getValueAt(row, 1).toString());
        categoryField.setText(tableModel.getValueAt(row, 2).toString());
        authorsIdField.setText(tableModel.getValueAt(row, 4).toString());
        libraryIdField.setText(tableModel.getValueAt(row, 5).toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookGUI::new);
    }
}
