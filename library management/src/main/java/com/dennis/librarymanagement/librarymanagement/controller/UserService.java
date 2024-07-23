package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Student;
import com.dennis.librarymanagement.librarymanagement.entities.User;
import com.dennis.librarymanagement.librarymanagement.services.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private Connection conn;

    public UserService() throws SQLException {
        this.conn = DatabaseService.getConnection();
    }

    public UserService(Connection conn) {
        this.conn = conn;
    }

    public void addUser(Student user) {
        String sql = "INSERT INTO \"User\" (id, name, email, reserved_books) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setInt(4, user.getBooksReserved());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        String sql = "DELETE FROM \"User\" WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM \"User\"";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Student user = new Student();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setReservedBooks(rs.getInt("reserved_books"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
