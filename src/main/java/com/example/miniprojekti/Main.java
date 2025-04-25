package com.example.miniprojekti;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    private BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        HBox menu = new HBox(10);
        menu.setStyle("-fx-padding: 20; -fx-alignment: center;");

        //Napit
        Button yleiskalenteri = new Button("Yleiskalenteri");
        Button varauskalenteri = new Button("Varauskalenteri");
        Button mokkihallinnointi = new Button("Mokkien hallinnointi");
        Button taustaohjelma = new Button("Taustaohjelma");

        //Napit valikkoon
        menu.getChildren().addAll(yleiskalenteri, varauskalenteri, mokkihallinnointi, taustaohjelma);

        //Nappien sijainti
        root.setCenter(menu);

        Scene scene = new Scene(root, 600, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Varausjärjestelmä");
        primaryStage.show();
    }

}
