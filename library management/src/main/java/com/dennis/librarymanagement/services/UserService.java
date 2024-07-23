package com.dennis.librarymanagement.services;

import com.dennis.librarymanagement.entities.Student;

import java.sql.*;
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

    public int addUser(Student user) {
        String sql = "INSERT INTO user (id, name, email, reserved_books) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setInt(4, user.getBooksReserved());
            pstmt.executeUpdate();
            ResultSet generatedKey = pstmt.getGeneratedKeys();
            if (generatedKey.next()) {
                return generatedKey.getInt(1);  // Return the generated userId
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllUsers(){
        String sql = "SELECT * FROM user";
        List<Student> users = new ArrayList<>();

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
        System.out.println(users);
        return users;
    }
}
