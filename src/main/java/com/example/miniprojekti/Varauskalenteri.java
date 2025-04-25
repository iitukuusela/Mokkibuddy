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

        //Lomake varauksen lisäämiseen
        TextField peopleField = new TextField();
        peopleField.setPromptText("Henkilömäärä");
        peopleField.setMaxWidth(150);

        TextField nameField = new TextField();
        nameField.setPromptText("Varaajan nimi");
        nameField.setMaxWidth(150);

        TextField emailField = new TextField();
        emailField.setPromptText("Sähköposti");
        emailField.setMaxWidth(150);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Puhelinnumero");
        phoneField.setMaxWidth(150);

        HBox peopleBox = new HBox(5);
        peopleBox.getChildren().addAll(peopleField, nameField, emailField, phoneField);

        TextField mokkiField = new TextField();
        mokkiField.setPromptText("Varattu mökki");
        mokkiField.setMaxWidth(150);

        TextField lisaSankyField = new TextField();
        lisaSankyField.setPromptText("Lisäsängyn tarve");
        lisaSankyField.setMaxWidth(150);

        TextField cleaningField = new TextField();
        cleaningField.setPromptText("Siivouspalvelu");
        cleaningField.setMaxWidth(150);

        TextField lateCOField = new TextField();
        lateCOField.setPromptText("Myöhäinen uloskirjautuminen");
        lateCOField.setMaxWidth(150);

        HBox mokkiBox = new HBox(5);
        mokkiBox.getChildren().addAll(mokkiField, lisaSankyField, cleaningField, lateCOField);

        TextField priceField = new TextField();
        priceField.setPromptText("Summa");
        priceField.setMaxWidth(150);

        TextField cardNumberField = new TextField();
        cardNumberField.setPromptText("Kortin numero");
        cardNumberField.setMaxWidth(150);

        TextField validityField = new TextField();
        validityField.setPromptText("Voimassaoloaika");
        validityField.setMaxWidth(150);

        TextField securityField = new TextField();
        securityField.setPromptText("Turvanumero");
        securityField.setMaxWidth(150);

        HBox priceInfoBox = new HBox(5);
        priceInfoBox.getChildren().addAll(priceField, cardNumberField, validityField, securityField);

        VBox infoVBoxs = new VBox(5);
        infoVBoxs.getChildren().addAll(peopleBox, mokkiBox, priceInfoBox);

        //Nappien luonti
        Button addButton = new Button("Lisää varaus");

        Button deleteButton = new Button("Poista varaus");

        Button editButton = new Button("Muokkaa varausta");

        Button findButton = new Button("Etsi varaus");

        //Loppu ja alkupäivät
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        Label startDateLabel = new Label("Start date:");
        Label endDateLabel = new Label("End date:");

        VBox startDateVBox = new VBox(5);
        startDateVBox.getChildren().addAll(startDateLabel, startDatePicker);

        VBox endDateVBox = new VBox(5);
        endDateVBox.getChildren().addAll(endDateLabel, endDatePicker);

        HBox datesBox = new HBox(5);
        datesBox.getChildren().addAll(startDateVBox, endDateVBox);

        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(startDatePicker.getValue().plusDays(1));

        //Asettelu
        VBox vbox = new VBox(10);

        vbox.getChildren().addAll(infoVBoxs, datesBox);

        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
