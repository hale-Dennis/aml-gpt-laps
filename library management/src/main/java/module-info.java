module com.dennis.librarymanagement.librarymanagement {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.dennis.librarymanagement to javafx.fxml;
    opens com.dennis.librarymanagement.controller to javafx.fxml;


    exports com.dennis.librarymanagement;
    exports com.dennis.librarymanagement.controller;
    exports com.dennis.librarymanagement.entities;
    exports com.dennis.librarymanagement.services;
    opens com.dennis.librarymanagement.services to javafx.fxml;
}