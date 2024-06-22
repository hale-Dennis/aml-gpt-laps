package com.dennis.librarymanagement.librarymanagement.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button manageBooksButton;

    @FXML
    private Button issueReturnBooksButton;

    @FXML
    private Button handleReservationsButton;

    @FXML
    private Button manageFinesButton;

    // Load the "Manage Books" scene
    @FXML
    private void handleManageBooks() throws IOException {
        loadScene("management.fxml", "Manage Books");
    }

    // Load the "Issue/Return Books" scene
    @FXML
    private void handleIssueReturnBooks() throws IOException {
        loadScene("issue_return.fxml", "Issue-Return Books");
    }

    // Load the "Handle Reservations" scene
    @FXML
    private void handleHandleReservations() throws IOException {
        loadScene("reservations.fxml", "Reservations");
    }

    // Load the "Manage Fines" scene
    @FXML
    private void handleManageUsers() throws IOException {
        loadScene("users.fxml", "Manage users");
    }

    // Helper method to load a new scene
    private void loadScene(String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fxmlFile));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
