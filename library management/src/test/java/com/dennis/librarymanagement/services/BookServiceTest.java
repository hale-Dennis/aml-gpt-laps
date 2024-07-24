package com.dennis.librarymanagement.services;

import com.dennis.librarymanagement.entities.Book;
import com.dennis.librarymanagement.entities.Student;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private static Connection conn;
    private static BookService bookService;
    private List<Integer> insertedBookIds;

    @BeforeAll
    static void setup() throws SQLException {
        conn = DatabaseService.getConnection();
        bookService = new BookService();
    }

    @BeforeEach
    void insertTestBooks() throws SQLException {
        insertedBookIds = new ArrayList<>();

        String insertQuery = "INSERT INTO book(title, author, publisher, published_year, isbn, copies) VALUES (?, ?, ?, ?, ?,?)";
        try (PreparedStatement statement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            String[][] testBooks = {
                    {"Song of Ice and Water", "George RR Marting", "Me", String.valueOf(2020), "3498", String.valueOf(100)},
            };
            for (String[] testBook : testBooks) {
                statement.setString(1, testBook[0]);
                statement.setString(2, testBook[1]);
                statement.setString(3, testBook[2]);
                statement.setString(4, testBook[3]);
                statement.setString(5, testBook[4]);
                statement.setString(6, testBook[5]);
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    insertedBookIds.add(generatedKeys.getInt(1));
                }
            }
        }
    }

    @AfterEach
    void removeTestBooks() throws SQLException {
        String removeQuery = "DELETE FROM book WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(removeQuery)) {
            for (int userId : insertedBookIds) {
                statement.setInt(1, userId);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        insertedBookIds.clear();
    }

    @AfterAll
    static void closeConnection() throws SQLException {
        if(conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
    @Test
    void addBook() {
        Book book = new Book("Ananse", "The man", "Amalitech", 2003, "39485", 100);
        int bookId = bookService.addBook(book);
        insertedBookIds.add(bookId);
        assertEquals("Ananse", book.getTitle());
    }

    @Test
    void updateBook() {
        Book book = new Book( "Ananse", "The man", "Amalitech", 2009, "39485", 100);
        int bookId = bookService.addBook(book);
        System.out.println("Id " + bookId);
        book.setId(bookId);
        book.setTitle("Dennis");
        bookService.updateBook(book);
        Book updatedBook = bookService.getBookById(bookId);
        insertedBookIds.add(bookId);
        assertEquals("Dennis", updatedBook.getTitle());
    }

    @Test
    void deleteBook() {
        Book book = new Book( "Ananse", "The man", "Amalitech", 2009, "39485", 100);
        int bookId = bookService.addBook(book);
        System.out.println("Id " + bookId);

        List<Book> booksBeforeDelete = bookService.getAllBooks();
        System.out.println(booksBeforeDelete.size());

        assertEquals(2, booksBeforeDelete.size(), "Expected 2 books before deletion");

        bookService.deleteBook(bookId);

        List<Book> booksAfterDelete = bookService.getAllBooks();
        System.out.println(booksAfterDelete.size());
        insertedBookIds.add(bookId);
        assertEquals(1, booksAfterDelete.size(), "Expected 1 books after deletion");
    }

    @Test
    void getAllBooks() {
        Book book = new Book( "Ananse", "The man", "Amalitech", 2009, "39485", 100);
        int bookId = bookService.addBook(book);
        System.out.println("Id " + bookId);
        List<Book> books = bookService.getAllBooks();
        insertedBookIds.add(bookId);
        assertEquals(2, books.size());

    }

    @Test
    void getBookById() {
        Book book = new Book( "Ananse", "The man", "Amalitech", 2009, "39485", 100);
        int bookId = bookService.addBook(book);
        System.out.println("Id " + bookId);
        Book foundBook = bookService.getBookById(bookId);
        insertedBookIds.add(bookId);
        assertEquals("Ananse", foundBook.getTitle());
    }
}