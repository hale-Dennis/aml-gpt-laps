package com.dennis.librarymanagement.services;

import com.dennis.librarymanagement.entities.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    private ReservationService reservationService;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        reservationService = new ReservationService(mockConnection);
    }

    @Test
    void testReserveBook() throws SQLException {
        String sql = "INSERT INTO reservation (user_id, book_id, reservation_date, status) VALUES (?, ?, CURDATE(), 'reserved')";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        reservationService.reserveBook(1, 1);

        ArgumentCaptor<Integer> userIdCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> bookIdCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(mockPreparedStatement, times(1)).setInt(eq(1), userIdCaptor.capture());
        verify(mockPreparedStatement, times(1)).setInt(eq(2), bookIdCaptor.capture());
        verify(mockPreparedStatement, times(1)).executeUpdate();

        assertEquals(1, userIdCaptor.getValue());
        assertEquals(1, bookIdCaptor.getValue());
    }

    @Test
    void testLoadReservation() throws SQLException {
        String sql = "SELECT * FROM reservation";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getInt("user_id")).thenReturn(2);
        when(mockResultSet.getInt("book_id")).thenReturn(2);
        when(mockResultSet.getDate("reservation_date")).thenReturn(java.sql.Date.valueOf("2023-01-01"));
        when(mockResultSet.getString("status")).thenReturn("reserved");

        List<Reservation> reservations = reservationService.loadReservation();

        assertFalse(reservations.isEmpty(), "Reservations list should not be empty");
        assertEquals(1, reservations.size(), "There should be one reservation in the list");

        Reservation reservation = reservations.get(0);
        assertEquals(1, reservation.getId());
        assertEquals(2, reservation.getUserId());
        assertEquals(2, reservation.getBookId());
        assertEquals("2023-01-01", reservation.getReservationDate().toString());
        assertEquals("reserved", reservation.getStatus());
    }
}
