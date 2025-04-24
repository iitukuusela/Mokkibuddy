module com.example.miniprojekti {
    requires javafx.fxml;
    requires java.sql;
    requires com.calendarfx.view;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.fontawesome;
    requires javafx.controls;
    requires org.controlsfx.controls;




    opens com.example.miniprojekti to javafx.fxml;
    exports com.example.miniprojekti;
}