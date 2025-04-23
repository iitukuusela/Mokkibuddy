package com.example.miniprojekti;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Varauskalenteri extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox(20);
        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(startDatePicker.getValue().plusDays(1));

        vbox.getChildren().add(new Label("Start Date:"));
        vbox.getChildren().add(startDatePicker);
        vbox.getChildren().add(new Label("End Date:"));
        vbox.getChildren().add(endDatePicker);
        primaryStage.show();
    }
}
