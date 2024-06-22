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
    }

    @FXML
    private void handleIssueBook() {
        int userId = Integer.parseInt(userIdField.getText());
        int bookId = Integer.parseInt(bookIdField.getText());

        String sql = "INSERT INTO Transaction (user_id, book_id, issue_date, status) VALUES (?, ?, CURDATE(), 'issued')";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadTransactions();
    }

    @FXML
    private void handleReturnBook() {
        int userId = Integer.parseInt(userIdField.getText());
        int bookId = Integer.parseInt(bookIdField.getText());

        String sql = "UPDATE Transaction SET return_date = CURDATE(), status = 'returned' WHERE user_id = ? AND book_id = ? AND status = 'issued'";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadTransactions();
    }

    private void loadTransactions() {
        transactions.clear();

        String sql = "SELECT * FROM Transaction";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setBookId(rs.getInt("book_id"));
                transaction.setIssueDate(rs.getDate("issue_date"));
                transaction.setReturnDate(rs.getDate("return_date"));
                transaction.setStatus(rs.getString("status"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
