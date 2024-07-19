package com.dennis.librarymanagement.librarymanagement.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        transaction = new Transaction(1, 101, 202, new Date(), new Date(), "issued");
    }

    @Test
    public void testGetId() {
        assertEquals(1, transaction.getId());
    }

    @Test
    public void testSetId() {
        transaction.setId(2);
        assertEquals(2, transaction.getId());
    }

    @Test
    public void testGetUserId() {
        assertEquals(101, transaction.getUserId());
    }

    @Test
    public void testSetUserId() {
        transaction.setUserId(102);
        assertEquals(102, transaction.getUserId());
    }

    @Test
    public void testGetBookId() {
        assertEquals(202, transaction.getBookId());
    }

    @Test
    public void testSetBookId() {
        transaction.setBookId(203);
        assertEquals(203, transaction.getBookId());
    }

    @Test
    public void testGetIssueDate() {
        Date issueDate = new Date();
        transaction.setIssueDate(issueDate);
        assertEquals(issueDate, transaction.getIssueDate());
    }

    @Test
    public void testSetIssueDate() {
        Date issueDate = new Date();
        transaction.setIssueDate(issueDate);
        assertEquals(issueDate, transaction.getIssueDate());
    }

    @Test
    public void testGetReturnDate() {
        Date returnDate = new Date();
        transaction.setReturnDate(returnDate);
        assertEquals(returnDate, transaction.getReturnDate());
    }

    @Test
    public void testSetReturnDate() {
        Date returnDate = new Date();
        transaction.setReturnDate(returnDate);
        assertEquals(returnDate, transaction.getReturnDate());
    }

    @Test
    public void testGetStatus() {
        assertEquals("issued", transaction.getStatus());
    }

    @Test
    public void testSetStatus() {
        transaction.setStatus("returned");
        assertEquals("returned", transaction.getStatus());
    }
}
