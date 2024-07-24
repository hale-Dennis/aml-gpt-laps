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
    TextField id;
    @FXML
    TextField name;
    @FXML
    TextField email;
    @FXML
    TextField booksReserved;


    @FXML
    TableView<Student> usersTable;
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
    public void handleAddUser() {
        int id = Integer.parseInt(this.id.getText());
        String name = this.name.getText();
        String email = this.email.getText();
        int reservedBooks = Integer.parseInt(booksReserved.getText());
        Student student = new Student(id, name, email, reservedBooks);
        userService.addUser(student);
        loadUsers();
    }

    @FXML
    public void handleDeleteUser() {
        Student selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.deleteUser(selectedUser.getId());
            loadUsers();
        }
    }

    public void loadUsers() {
        users.clear();
        users.addAll(userService.getAllUsers());
    }
}

