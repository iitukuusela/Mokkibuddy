package com.example.miniprojekti;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Taustaohjelma extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Lomake varauksen lisäämiseen
        TextField nameField = new TextField();
        nameField.setPromptText("Varaajan nimi");
        nameField.setMaxWidth(150);

        TextField emailField = new TextField();
        emailField.setPromptText("Sähköposti");
        emailField.setMaxWidth(150);

        HBox tiedotBox = new HBox(5);
        tiedotBox.getChildren().addAll(nameField, emailField);

        TextField mokkiField = new TextField();
        mokkiField.setPromptText("Mökki");
        mokkiField.setMaxWidth(150);

        TextField priceField = new TextField();
        priceField.setPromptText("Summa");
        priceField.setMaxWidth(150);

        //PITÄÄKÖ OLLA MYÖS YÖVYTTYJEN ÖIDEN MÄÄRÄ??

        HBox summaMokkiBox = new HBox(5);
        summaMokkiBox.getChildren().addAll(mokkiField, priceField);

        Label dateLabel = new Label();
        dateLabel.setText("Päivämäärä:");

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        HBox dateBox = new HBox(5);
        dateBox.getChildren().addAll(dateLabel, datePicker);

        VBox allInfoBox = new VBox(5);
        allInfoBox.getChildren().addAll(tiedotBox, summaMokkiBox, dateBox);

        //Napit
        Button findButton = new Button();
        findButton.setText("Etsi");

        Button createPDFButton = new Button();
        createPDFButton.setText("Luo PDF");

        HBox buttonbox = new HBox(5);
        buttonbox.getChildren().addAll(findButton, createPDFButton);

        //Asettelu
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(allInfoBox, buttonbox);

        Scene scene = new Scene(vBox, 600, 400);
        primaryStage.setTitle("Taustaohjelma");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
