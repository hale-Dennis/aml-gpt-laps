package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Reservation;
import com.dennis.librarymanagement.librarymanagement.services.DatabaseService;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationService {

    private Connection conn;

    public ReservationService() throws SQLException {
        this.conn = DatabaseService.getConnection();
    }

    public ReservationService(Connection conn) {
        this.conn = conn;
    }

    public void reserveBook(int userId, int bookId){
        String sql = "INSERT INTO Reservation (user_id, book_id, reservation_date, status) VALUES (?, ?, CURDATE(), 'reserved')";

        try {
             PreparedStatement pstmt = conn.prepareStatement(sql);


            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void loadReservation(ObservableList<Reservation> reservations){
        String sql = "SELECT * FROM Reservation";

        try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setUserId(rs.getInt("user_id"));
                reservation.setBookId(rs.getInt("book_id"));
                reservation.setReservationDate(rs.getDate("reservation_date"));
                reservation.setStatus(rs.getString("status"));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

