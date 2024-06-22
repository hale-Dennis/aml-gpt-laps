package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Reservation;
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

public class ReservationsController {
    @FXML
    private TextField userIdField;
    @FXML
    private TextField bookIdField;
    @FXML
    private TableView<Reservation> reservationsTable;
    private ObservableList<Reservation> reservations;

    @FXML
    public void initialize() {
        reservations = FXCollections.observableArrayList();
        reservationsTable.setItems(reservations);
        loadReservations();

        reservationsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userId"));
        reservationsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        reservationsTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        reservationsTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    private void handleReserveBook() {
        int userId = Integer.parseInt(userIdField.getText());
        int bookId = Integer.parseInt(bookIdField.getText());

        String sql = "INSERT INTO Reservation (user_id, book_id, reservation_date, status) VALUES (?, ?, CURDATE(), 'reserved')";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadReservations();
    }

    private void loadReservations() {
        reservations.clear();

        String sql = "SELECT * FROM Reservation";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

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

