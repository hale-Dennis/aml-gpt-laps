package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    private static Connection conn;
    private ReservationService reservationService;

    @BeforeAll
    static void setup() throws SQLException {
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        try (var stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE Reservation (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT, " +
                    "book_id INT, " +
                    "reservation_date DATE, " +
                    "status VARCHAR(255)" +
                    ")");
        }
    }

    @AfterAll
    static void tearDown() throws SQLException {
        try (var stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE Reservation");
        }
        conn.close();
    }

    @BeforeEach
    void init() {
        reservationService = new ReservationService(conn);
    }

    @Test
    void testReserveBook() {
        reservationService.reserveBook(1, 1);

        String sql = "SELECT * FROM Reservation WHERE user_id = ? AND book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 1);
            try (ResultSet rs = pstmt.executeQuery()) {
                assertTrue(rs.next(), "Reservation should be present in the database");
                assertEquals(1, rs.getInt("user_id"));
                assertEquals(1, rs.getInt("book_id"));
                assertEquals("reserved", rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException should not have occurred");
        }
    }

    @Test
    void testLoadReservation() {
        reservationService.reserveBook(2, 2);
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        reservationService.loadReservation(reservations);

        assertFalse(reservations.isEmpty(), "Reservations list should not be empty");
        assertEquals(1, reservations.size(), "There should be one reservation in the list");

        Reservation reservation = reservations.get(0);
        assertEquals(2, reservation.getUserId());
        assertEquals(2, reservation.getBookId());
        assertEquals("reserved", reservation.getStatus());
    }
}
