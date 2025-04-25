package com.example.miniprojekti;

import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Taustaohjelma extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Lomake varauksen lisäämiseen

        TextField hotTubField = new TextField();
        hotTubField.setPromptText("Varaajan nimi");

        TextField distanceField = new TextField();
        distanceField.setPromptText("Sähköposti");

        TextField priceField = new TextField();
        priceField.setPromptText("Summa");

        TextField vMokkiField = new TextField();
        vMokkiField.setPromptText("Mökki");

        TextField capacityField = new TextField();
        capacityField.setPromptText("Päivämäärä");

        //summa, asiakas, päivämäärä, mökki
        //Napit: Etsi, luo pdf/lasku

    }
}
