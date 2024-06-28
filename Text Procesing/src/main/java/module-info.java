module com.dennis.textprocessing {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dennis.textprocessing to javafx.fxml;
    exports com.dennis.textprocessing;
}