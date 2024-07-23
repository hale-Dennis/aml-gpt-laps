package com.dennis.librarymanagement.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTest {

    private Reservation reservation;

    @BeforeEach
    public void setUp() {
        reservation = new Reservation(1, 101, 201, new Date(), "Reserved");
    }

    @Test
    public void testGetId() {
        assertEquals(1, reservation.getId());
    }

    @Test
    public void testSetId() {
        reservation.setId(2);
        assertEquals(2, reservation.getId());
    }

    @Test
    public void testGetUserId() {
        assertEquals(101, reservation.getUserId());
    }

    @Test
    public void testSetUserId() {
        reservation.setUserId(102);
        assertEquals(102, reservation.getUserId());
    }

    @Test
    public void testGetBookId() {
        assertEquals(201, reservation.getBookId());
    }

    @Test
    public void testSetBookId() {
        reservation.setBookId(202);
        assertEquals(202, reservation.getBookId());
    }

    @Test
    public void testGetReservationDate() {
        Date date = new Date();
        reservation.setReservationDate(date);
        assertEquals(date, reservation.getReservationDate());
    }

    @Test
    public void testSetReservationDate() {
        Date newDate = new Date();
        reservation.setReservationDate(newDate);
        assertEquals(newDate, reservation.getReservationDate());
    }

    @Test
    public void testGetStatus() {
        assertEquals("Reserved", reservation.getStatus());
    }

    @Test
    public void testSetStatus() {
        reservation.setStatus("Checked Out");
        assertEquals("Checked Out", reservation.getStatus());
    }
}
