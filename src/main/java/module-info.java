module com.example.meditrackapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.meditrackapp to javafx.fxml;
    exports com.example.meditrackapp;
}