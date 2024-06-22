package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Book;
import com.dennis.librarymanagement.librarymanagement.services.DatabaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksController {
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField publisherField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField copiesField;
    @FXML
    private TableView<Book> booksTable;
    private ObservableList<Book> books;

    @FXML
    public void initialize() {
        books = FXCollections.observableArrayList();
        booksTable.setItems(books);
        loadBooks();

        booksTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("title"));
        booksTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("author"));
        booksTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("publisher"));
        booksTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("publishedYear"));
        booksTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("isbn"));
        booksTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("copies"));
    }

    @FXML
    private void handleAddBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        int year = Integer.parseInt(yearField.getText());
        String isbn = isbnField.getText();
        int copies = Integer.parseInt(copiesField.getText());

        String sql = "INSERT INTO Book (title, author, publisher, published_year, isbn, copies) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, publisher);
            pstmt.setInt(4, year);
            pstmt.setString(5, isbn);
            pstmt.setInt(6, copies);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadBooks();
    }

    @FXML
    private void handleUpdateBook() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            String title = titleField.getText();
            String author = authorField.getText();
            String publisher = publisherField.getText();
            int year = Integer.parseInt(yearField.getText());
            String isbn = isbnField.getText();
            int copies = Integer.parseInt(copiesField.getText());

            String sql = "UPDATE Book SET title = ?, author = ?, publisher = ?, published_year = ?, isbn = ?, copies = ? WHERE id = ?";

            try (Connection conn = DatabaseService.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, title);
                pstmt.setString(2, author);
                pstmt.setString(3, publisher);
                pstmt.setInt(4, year);
                pstmt.setString(5, isbn);
                pstmt.setInt(6, copies);
                pstmt.setInt(7, selectedBook.getId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            loadBooks();
        }
    }

    @FXML
    private void handleDeleteBook() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            String sql = "DELETE FROM Book WHERE id = ?";

            try (Connection conn = DatabaseService.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, selectedBook.getId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            loadBooks();
        }
    }

    private void loadBooks() {
        books.clear();

        String sql = "SELECT * FROM Book";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishedYear(rs.getInt("published_year"));
                book.setIsbn(rs.getString("isbn"));
                book.setCopies(rs.getInt("copies"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

