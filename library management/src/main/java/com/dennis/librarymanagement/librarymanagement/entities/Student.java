package com.dennis.librarymanagement.librarymanagement.entities;

public class Student extends User{
    private int booksReserved;

    public Student(){

    }

    public Student(int id, String name, String email, int reservedBooks) {
        super(id, name, email);
        this.booksReserved = reservedBooks;
    }

    public int getBooksReserved() {
        return booksReserved;
    }

    public void setBooksReserved(int booksReserved) {
        this.booksReserved = booksReserved;
    }
}
