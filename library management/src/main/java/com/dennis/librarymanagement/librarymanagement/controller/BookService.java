package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Book;
import com.dennis.librarymanagement.librarymanagement.services.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private Connection conn;

    public BookService() throws SQLException {
        this.conn = DatabaseService.getConnection();
    }

    public BookService(Connection connection) {
        this.conn = connection;
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO Book (title, author, publisher, published_year, isbn, copies) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setInt(4, book.getPublishedYear());
            pstmt.setString(5, book.getIsbn());
            pstmt.setInt(6, book.getCopies());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        String sql = "UPDATE Book SET title = ?, author = ?, publisher = ?, published_year = ?, isbn = ?, copies = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setInt(4, book.getPublishedYear());
            pstmt.setString(5, book.getIsbn());
            pstmt.setInt(6, book.getCopies());
            pstmt.setInt(7, book.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        String sql = "DELETE FROM Book WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishedYear(rs.getInt("published_year"));
                book.setIsbn(rs.getString("isbn"));
                book.setCopies(rs.getInt("copies"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Book getBookById(int bookId) {
        Book book = null;
        String sql = "SELECT * FROM Book WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setPublishedYear(rs.getInt("published_year"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setCopies(rs.getInt("copies"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }
}
