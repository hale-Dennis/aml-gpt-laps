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
    private ReservationService reservationService;

    @FXML
    public void initialize() throws SQLException {
        reservations = FXCollections.observableArrayList();
        reservationsTable.setItems(reservations);
        loadReservations();

        reservationsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userId"));
        reservationsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        reservationsTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        reservationsTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));
        reservationService = new ReservationService();
    }

    @FXML
    private void handleReserveBook() {
        int userId = Integer.parseInt(userIdField.getText());
        int bookId = Integer.parseInt(bookIdField.getText());

        reservationService.reserveBook(userId, bookId);
        loadReservations();
    }

    private void loadReservations() {
        reservations.clear();
        reservationService.loadReservation(reservations);
    }
}

