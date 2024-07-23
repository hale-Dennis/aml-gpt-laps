package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Transaction;
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

public class IssueReturnController {
    @FXML
    private TextField userIdField;
    @FXML
    private TextField bookIdField;
    @FXML
    private TableView<Transaction> transactionsTable;
    private ObservableList<Transaction> transactions;
    private IssueReturnService issueReturnService;

    @FXML
    public void initialize() {
        transactions = FXCollections.observableArrayList();
        transactionsTable.setItems(transactions);
        loadTransactions();

        transactionsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userId"));
        transactionsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        transactionsTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        transactionsTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        transactionsTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        issueReturnService = new IssueReturnService();
    }

    @FXML
    private void handleIssueBook() {
        int userId = Integer.parseInt(userIdField.getText());
        int bookId = Integer.parseInt(bookIdField.getText());

        issueReturnService.issueBook(userId, bookId);

        loadTransactions();
    }

    @FXML
    private void handleReturnBook() {
        int userId = Integer.parseInt(userIdField.getText());
        int bookId = Integer.parseInt(bookIdField.getText());
        issueReturnService.returnBook(userId, bookId);

        loadTransactions();
    }

    private void loadTransactions() {
        transactions.clear();
        issueReturnService.loadTransactions(transactions);
    }
}
