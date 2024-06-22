package com.dennis.librarymanagement.librarymanagement.controller;

import com.dennis.librarymanagement.librarymanagement.entities.Student;
import com.dennis.librarymanagement.librarymanagement.services.DatabaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField booksReserved;


    @FXML
    private TableView<Student> usersTable;
    private ObservableList<Student> users;

    @FXML
    public void initialize() {
        users = FXCollections.observableArrayList();
        usersTable.setItems(users);
        loadUsers();

        usersTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        usersTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        usersTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        usersTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("booksReserved"));
    }

    @FXML
    private void handleAddUser() {
        String id = this.id.getText();
        String name = this.name.getText();
        String email = this.email.getText();
        int reservedBooks = Integer.parseInt(booksReserved.getText());

        String sql = "INSERT INTO User (id, name, email, reserved_books) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setInt(4, reservedBooks);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadUsers();
    }
//
//    @FXML
//    private void handleUpdateBook() {
//        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
//        if (selectedUser != null) {
//            String title = ID.getText();
//            String author = name.getText();
//            String publisher = email.getText();
//            int year = Integer.parseInt(booksReserved.getText());
//            String isbn = isbnField.getText();
//            int copies = Integer.parseInt(copiesField.getText());
//
//            String sql = "UPDATE Book SET title = ?, author = ?, publisher = ?, published_year = ?, isbn = ?, copies = ? WHERE id = ?";
//
//            try (Connection conn = DatabaseService.getConnection();
//                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//                pstmt.setString(1, title);
//                pstmt.setString(2, author);
//                pstmt.setString(3, publisher);
//                pstmt.setInt(4, year);
//                pstmt.setString(5, isbn);
//                pstmt.setInt(6, copies);
//                pstmt.setInt(7, selectedUser.getId());
//                pstmt.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            loadBooks();
//        }
//    }

    @FXML
    private void handleDeleteUser() {
        Student selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            String sql = "DELETE FROM User WHERE id = ?";

            try (Connection conn = DatabaseService.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, selectedUser.getId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            loadUsers();
        }
    }

    private void loadUsers() {
        users.clear();

        String sql = "SELECT * FROM User";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Student user = new Student();
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setBooksReserved(rs.getInt("reserved_books"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

