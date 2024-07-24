package com.dennis.librarymanagement.services;

import com.dennis.librarymanagement.entities.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IssueReturnService {
    static Connection conn;

    public IssueReturnService() throws SQLException {
        this.conn = DatabaseService.getConnection();
    }

    public void issueBook(int userId, int bookId) {

        String sql = "INSERT INTO transaction (user_id, book_id, issue_date, status) VALUES (?, ?, CURDATE(), 'issued')";

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

        String sql = "UPDATE transaction SET return_date = CURDATE(), status = 'returned' WHERE user_id = ? AND book_id = ? AND status = 'issued'";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Transaction> loadTransactions(){
        List<Transaction> transactions = new ArrayList<>();
        String sql = "select t.id as id, u.id as user_id, b.id as book_id,\n" +
                "       t.issue_date as issue_date, t.return_date as return_date,\n" +
                "       t.status as status\n" +
                "from transaction t left join book b on t.book_id = b.id\n" +
                "    left join library.user u on t.user_id = u.id;";

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
        return transactions;
    }
}
