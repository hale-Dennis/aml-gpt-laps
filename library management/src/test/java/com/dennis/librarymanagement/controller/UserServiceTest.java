package com.dennis.librarymanagement.controller;

import com.dennis.librarymanagement.entities.Student;
import com.dennis.librarymanagement.services.DatabaseService;
import com.dennis.librarymanagement.services.UserService;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private static Connection conn;
    private static UserService userService;
    private static List<Integer> insertedUserIds;

    @BeforeAll
    static void setup() throws SQLException {
        conn = DatabaseService.getConnection();
        userService = new UserService(conn);
    }

    @BeforeEach
    void insertTestUsers() throws SQLException {
        insertedUserIds = new ArrayList<>();

        String insertQuery = "INSERT INTO user(name, email, reserved_books) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            String[][] testUsers = {
                    {"Sam Larbi", "slarbi@gmail.com", "0"},
                    {"Ntummy Redeemer", "ntummy@gmail.com", "0"},
                    {"Elisha Gyamfi", "elisha@gmail.com", "0"},
            };
            for (String[] testUser : testUsers) {
                statement.setString(1, testUser[0]);
                statement.setString(2, testUser[1]);
                statement.setString(3, testUser[2]);
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    insertedUserIds.add(generatedKeys.getInt(1));
                }
            }
        }
    }

    @AfterEach
    void removeTestUsers() throws SQLException {
        String removeQuery = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(removeQuery)) {
            for (int userId : insertedUserIds) {
                statement.setInt(1, userId);
                statement.executeUpdate();
            }
        }
        insertedUserIds.clear();
    }

    @AfterAll
    static void tearDown() throws SQLException {
       if (conn != null && !conn.isClosed()) {
           conn.close();
       }
    }

    @Test
    void testAddUser() throws SQLException {
        Student user = new Student(0, "John Mac", "john.mac@gmail.com", 0);
        int userId = userService.addUser(user);

        assertEquals("John Mac", user.getName());
        assertEquals("john.mac@gmail.com", user.getEmail());

        String removeQuery = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(removeQuery)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }

    @Test
    void testDeleteUser() {
        Student user = new Student(0, "Jane Smith", "jane.smith@example.com", 0);
        int userId = userService.addUser(user);

        List<Student> usersBeforeDelete = userService.getAllUsers();
        assertEquals(4, usersBeforeDelete.size(), "Expected 4 users before deletion");

        userService.deleteUser(userId);

        List<Student> usersAfterDelete = userService.getAllUsers();
        assertEquals(3, usersAfterDelete.size(), "Expected 3 users after deletion");
    }


    @Test
    void getAllUsers() {
        List<Student> users = userService.getAllUsers();

        assertEquals(3, users.size());

    }
}
