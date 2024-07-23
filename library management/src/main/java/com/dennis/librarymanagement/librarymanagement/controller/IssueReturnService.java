package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Transaction;
import com.dennis.librarymanagement.librarymanagement.services.DatabaseService;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueReturnService {
    static Connection conn;

    public void issueBook(int userId, int bookId) {

        String sql = "INSERT INTO Transaction (user_id, book_id, issue_date, status) VALUES (?, ?, CURDATE(), 'issued')";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void returnBook(int userId, int bookId) {

        String sql = "UPDATE Transaction SET return_date = CURDATE(), status = 'returned' WHERE user_id = ? AND book_id = ? AND status = 'issued'";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void loadTransactions(ObservableList<Transaction> transactions){

        String sql = "SELECT * FROM Transaction";

        try{
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();

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
