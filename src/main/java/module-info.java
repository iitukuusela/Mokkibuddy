module com.example.miniprojekti {
    requires javafx.fxml;
    requires java.sql;
    requires com.calendarfx.view;


    opens com.example.miniprojekti to javafx.fxml;
    exports com.example.miniprojekti;
}