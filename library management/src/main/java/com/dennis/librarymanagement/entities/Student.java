package com.dennis.librarymanagement.entities;

public class Student extends User{
    private int booksReserved;

    public Student(){

    }

    public Student(int id, String name, String email, int reservedBooks) {
        super(id, name, email);
        this.booksReserved = reservedBooks;
    }

    public Student(String id, String name, String email) {
        super(Integer.parseInt(id), name,email);
    }

    public int getBooksReserved() {
        return booksReserved;
    }

    public void setBooksReserved(int booksReserved) {
        this.booksReserved = booksReserved;
    }

    public void setReservedBooks(int reservedBooks) {
        this.booksReserved = reservedBooks;
    }
}
