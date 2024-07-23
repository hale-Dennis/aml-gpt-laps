package com.dennis.librarymanagement.controller;

import com.dennis.librarymanagement.entities.Student;
import com.dennis.librarymanagement.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private UserService userService;

    public UserController(){
        try {
            this.userService = new UserService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() throws SQLException {
        users = FXCollections.observableArrayList();
        usersTable.setItems(users);
        loadUsers();

        usersTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        usersTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        usersTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        usersTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("booksReserved"));
        userService = new UserService();
    }

    @FXML
    private void handleAddUser() {
        int id = Integer.parseInt(this.id.getText());
        String name = this.name.getText();
        String email = this.email.getText();
        int reservedBooks = Integer.parseInt(booksReserved.getText());
        Student student = new Student(id, name, email, reservedBooks);
        userService.addUser(student);
        loadUsers();
    }

    @FXML
    private void handleDeleteUser() {
        Student selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.deleteUser(selectedUser.getId());
            loadUsers();
        }
    }

    private void loadUsers() {
        users.clear();
        users.addAll(userService.getAllUsers());

//        String sql = "SELECT * FROM User";
//
//        try (Connection conn = DatabaseService.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//
//            while (rs.next()) {
//                Student user = new Student();
//                user.setId(rs.getInt("ID"));
//                user.setName(rs.getString("name"));
//                user.setEmail(rs.getString("email"));
//                user.setBooksReserved(rs.getInt("reserved_books"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}

