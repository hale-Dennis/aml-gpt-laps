package com.dennis.librarymanagement.services;

import com.dennis.librarymanagement.entities.Transaction;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IssueReturnServiceTest {

    private static Connection mockConn;
    private static IssueReturnService issueReturnService;

    @BeforeAll
    static void setup() throws SQLException {
        mockConn = mock(Connection.class);
        issueReturnService = new IssueReturnService();
        issueReturnService.conn = mockConn;
    }

    @AfterAll
    static void tearDown() throws SQLException {
        mockConn.close();
    }

    @Test
    void testIssueBook() throws SQLException {
        PreparedStatement mockPstmt = mock(PreparedStatement.class);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPstmt);

        issueReturnService.issueBook(1, 1);

        verify(mockPstmt, times(1)).setInt(1, 1);
        verify(mockPstmt, times(1)).setInt(2, 1);
        verify(mockPstmt, times(1)).executeUpdate();
    }

    @Test
    void testReturnBook() throws SQLException {
        PreparedStatement mockPstmt = mock(PreparedStatement.class);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPstmt);

        issueReturnService.returnBook(1, 1);

        verify(mockPstmt, times(1)).setInt(1, 1);
        verify(mockPstmt, times(1)).setInt(2, 1);
        verify(mockPstmt, times(1)).executeUpdate();
    }

    @Test
    void testLoadTransactions() throws SQLException {
        PreparedStatement mockPstmt = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        when(mockConn.prepareStatement(anyString())).thenReturn(mockPstmt);
        when(mockPstmt.executeQuery()).thenReturn(mockRs);

        when(mockRs.next()).thenReturn(true, false); // Simulate one row
        when(mockRs.getInt("id")).thenReturn(1);
        when(mockRs.getInt("user_id")).thenReturn(1);
        when(mockRs.getInt("book_id")).thenReturn(1);
        when(mockRs.getDate("issue_date")).thenReturn(java.sql.Date.valueOf("2024-01-01"));
        when(mockRs.getDate("return_date")).thenReturn(java.sql.Date.valueOf("2024-01-10"));
        when(mockRs.getString("status")).thenReturn("issued");

        List<Transaction> transactions = issueReturnService.loadTransactions();

        assertEquals(1, transactions.size());
        Transaction transaction = transactions.get(0);
        assertEquals(1, transaction.getId());
        assertEquals(1, transaction.getUserId());
        assertEquals(1, transaction.getBookId());
        assertEquals(java.sql.Date.valueOf("2024-01-01"), transaction.getIssueDate());
        assertEquals(java.sql.Date.valueOf("2024-01-10"), transaction.getReturnDate());
        assertEquals("issued", transaction.getStatus());
    }
}
