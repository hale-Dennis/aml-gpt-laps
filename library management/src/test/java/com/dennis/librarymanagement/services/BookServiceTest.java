package com.dennis.librarymanagement.services;

import com.dennis.librarymanagement.entities.Book;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private static Connection conn;
    private BookService bookService;

    @BeforeAll
    static void setup() throws SQLException {
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        try (var stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE Book (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "title VARCHAR(255), " +
                    "author VARCHAR(255), " +
                    "publisher VARCHAR(255), " +
                    "published_year INT, " +
                    "isbn VARCHAR(255), " +
                    "copies INT)");
        }
    }

    @AfterAll
    static void tearDown() throws SQLException {
        try (var stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE Book");
        }
        conn.close();
    }

    @BeforeEach
    void init() {
        bookService = new BookService(conn);
    }

    @Test
    void testAddBook() {
        Book book = new Book("Test Title", "Test Author", "Test Publisher", 2021, "1234567890", 5);
        bookService.addBook(book);

        List<Book> books = bookService.getAllBooks();
        assertEquals(1, books.size());
        assertEquals("Test Title", books.get(0).getTitle());
    }

    @Test
    void testUpdateBook() {
        Book book = new Book("Test Title", "Test Author", "Test Publisher", 2021, "1234567890", 5);
        bookService.addBook(book);

        Book addedBook = bookService.getAllBooks().get(0);
        addedBook.setTitle("Updated Title");
        bookService.updateBook(addedBook);

        Book updatedBook = bookService.getBookById(addedBook.getId());
        assertEquals("Updated Title", updatedBook.getTitle());
    }

    @Test
    void testDeleteBook() {
        Book book = new Book("Test Title", "Test Author", "Test Publisher", 2021, "1234567890", 5);
        bookService.addBook(book);

        List<Book> booksBeforeDelete = bookService.getAllBooks();
        assertEquals(1, booksBeforeDelete.size(), "Expected 1 book before deletion");

        Book addedBook = booksBeforeDelete.get(0);
        bookService.deleteBook(addedBook.getId());

        List<Book> booksAfterDelete = bookService.getAllBooks();
        assertEquals(0, booksAfterDelete.size(), "Expected 0 books after deletion");

        Book retrievedBook = bookService.getBookById(addedBook.getId());
        assertNull(retrievedBook, "Expected null when retrieving deleted book");
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book("Test Title 1", "Test Author 1", "Test Publisher 1", 2021, "1234567890", 5);
        Book book2 = new Book("Test Title 2", "Test Author 2", "Test Publisher 2", 2022, "0987654321", 3);
        bookService.addBook(book1);
        bookService.addBook(book2);

        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    void testGetBookById() {
        Book book = new Book("Test Title", "Test Author", "Test Publisher", 2021, "1234567890", 5);
        bookService.addBook(book);

        Book addedBook = bookService.getAllBooks().get(0);
        Book retrievedBook = bookService.getBookById(addedBook.getId());
        assertNotNull(retrievedBook);
        assertEquals(addedBook.getId(), retrievedBook.getId());
    }
}
