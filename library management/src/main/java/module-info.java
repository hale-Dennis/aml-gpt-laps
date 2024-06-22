module com.dennis.librarymanagement.librarymanagement {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.dennis.librarymanagement.librarymanagement to javafx.fxml;
    opens com.dennis.librarymanagement.librarymanagement.controller to javafx.fxml;


    exports com.dennis.librarymanagement.librarymanagement;
    exports com.dennis.librarymanagement.librarymanagement.controller;
    exports com.dennis.librarymanagement.librarymanagement.entities;
    exports com.dennis.librarymanagement.librarymanagement.services;
}