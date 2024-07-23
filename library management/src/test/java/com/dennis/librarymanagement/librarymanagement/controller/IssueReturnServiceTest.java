package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueReturnServiceTest {
    private static Connection conn;
    private IssueReturnService issueReturnService;

    @BeforeAll
    static void setup() throws SQLException {
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        IssueReturnService.conn = conn; // Assign the static connection in IssueReturnService
        try (var stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE Transaction (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT, " +
                    "book_id INT, " +
                    "issue_date DATE, " +
                    "return_date DATE, " +
                    "status VARCHAR(255)" +
                    ")");
        }
    }

    @AfterAll
    static void tearDown() throws SQLException {
        try (var stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE Transaction");
        }
        conn.close();
    }

    @BeforeEach
    void init() {
        issueReturnService = new IssueReturnService();
    }

    @Test
    void testIssueBook() {
        issueReturnService.issueBook(1, 1);

        String sql = "SELECT * FROM Transaction WHERE user_id = ? AND book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 1);
            try (ResultSet rs = pstmt.executeQuery()) {
                assertTrue(rs.next(), "Transaction should be present in the database");
                assertEquals(1, rs.getInt("user_id"));
                assertEquals(1, rs.getInt("book_id"));
                assertEquals("issued", rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException should not have occurred");
        }
    }

    @Test
    void testReturnBook() {
        // First issue a book
        issueReturnService.issueBook(1, 1);
        // Now return the book
        issueReturnService.returnBook(1, 1);

        String sql = "SELECT * FROM Transaction WHERE user_id = ? AND book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 1);
            try (ResultSet rs = pstmt.executeQuery()) {
                assertTrue(rs.next(), "Transaction should be present in the database");
                assertEquals(1, rs.getInt("user_id"));
                assertEquals(1, rs.getInt("book_id"));
                assertEquals("returned", rs.getString("status"));
                assertNotNull(rs.getDate("return_date"), "Return date should be set");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException should not have occurred");
        }
    }

    @Test
    void testLoadTransactions() {
        issueReturnService.issueBook(1, 1);
        issueReturnService.returnBook(1, 1);

        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        issueReturnService.loadTransactions(transactions);

        assertFalse(transactions.isEmpty(), "Transactions list should not be empty");
        assertEquals(1, transactions.size(), "There should be one transaction in the list");

        Transaction transaction = transactions.get(0);
        assertEquals(1, transaction.getUserId());
        assertEquals(1, transaction.getBookId());
        assertEquals("returned", transaction.getStatus());
    }
}
