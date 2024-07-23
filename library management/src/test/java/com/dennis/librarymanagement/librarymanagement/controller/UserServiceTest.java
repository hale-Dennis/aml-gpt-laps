package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Student;
import com.dennis.librarymanagement.librarymanagement.entities.User;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private static Connection conn;
    private UserService userService;

    @BeforeAll
    static void setup() throws SQLException {
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        try (var stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE \"User\" (" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255), " +
                    "reserved_books INT)");
        }
    }

    @AfterAll
    static void tearDown() throws SQLException {
        try (var stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE \"User\"");
        }
        conn.close();
    }

    @BeforeEach
    void init() {
        userService = new UserService(conn);
    }

    @Test
    void testAddUser() {
        Student user = new Student(1, "John Doe", "john.doe@example.com", 0);
        userService.addUser(user);

        List<User> users = getAllUsers();
        assertEquals(1, users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("john.doe@example.com", users.get(0).getEmail());
    }

    @Test
    void testDeleteUser() {
        Student user = new Student(2, "Jane Smith", "jane.smith@example.com", 0);
        userService.addUser(user);

        List<User> usersBeforeDelete = getAllUsers();
        assertEquals(1, usersBeforeDelete.size(), "Expected 1 user before deletion");

        userService.deleteUser(user);

        List<User> usersAfterDelete = getAllUsers();
        assertEquals(0, usersAfterDelete.size(), "Expected 0 users after deletion");
    }


    private List<User> getAllUsers() {
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
