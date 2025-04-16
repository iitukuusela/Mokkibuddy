module com.example.miniprojekti {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.miniprojekti to javafx.fxml;
    exports com.example.miniprojekti;
}