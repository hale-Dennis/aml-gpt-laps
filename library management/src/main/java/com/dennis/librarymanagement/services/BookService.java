package com.dennis.librarymanagement.services;

import com.dennis.librarymanagement.entities.Book;

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

    public int addBook(Book book) {
        String sql = "INSERT INTO Book (id,title, author, publisher, published_year, isbn, copies) VALUES (?, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, String.valueOf(book.getId()));
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getPublisher());
            pstmt.setInt(5, book.getPublishedYear());
            pstmt.setString(6, book.getIsbn());
            pstmt.setInt(7, book.getCopies());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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
        String sql = "DELETE FROM book WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";

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
        String sql = "SELECT * FROM book WHERE id = ?";

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
