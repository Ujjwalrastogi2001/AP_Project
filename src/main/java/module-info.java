module com.example.ap_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.ap_project to javafx.fxml;
    exports com.example.ap_project;
}