package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private BookService bookService;

    public BooksController() {
        try {
            this.bookService = new BookService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
    public void handleAddBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        int year = Integer.parseInt(yearField.getText());
        String isbn = isbnField.getText();
        int copies = Integer.parseInt(copiesField.getText());

        Book book = new Book(title, author, publisher, year, isbn, copies);
        bookService.addBook(book);
        loadBooks();
    }

    @FXML
    public void handleUpdateBook() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            String title = titleField.getText();
            String author = authorField.getText();
            String publisher = publisherField.getText();
            int year = Integer.parseInt(yearField.getText());
            String isbn = isbnField.getText();
            int copies = Integer.parseInt(copiesField.getText());

            selectedBook.setTitle(title);
            selectedBook.setAuthor(author);
            selectedBook.setPublisher(publisher);
            selectedBook.setPublishedYear(year);
            selectedBook.setIsbn(isbn);
            selectedBook.setCopies(copies);

            bookService.updateBook(selectedBook);
            loadBooks();
        }
    }

    @FXML
    public void handleDeleteBook() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            bookService.deleteBook(selectedBook.getId());
            loadBooks();
        }
    }

    public void loadBooks() {
        books.clear();
        books.addAll(bookService.getAllBooks());
    }
}
