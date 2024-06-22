package com.dennis.librarymanagement.librarymanagement.entities;

import java.util.Date;

public class Reservation {
    private int id;
    private int userId;
    private int bookId;
    private Date reservationDate;
    private String status;

    public Reservation() {}

    public Reservation(int id, int userId, int bookId, Date reservationDate, String status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

