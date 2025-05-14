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

        Scene mainScene = createScene(primaryStage);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Mökkivarausjärjestelmä");
        primaryStage.show();

    }

    public Scene createScene(Stage primaryStage) {

       root = new BorderPane();

        HBox menu = new HBox(10);
        menu.setStyle("-fx-padding: 20; -fx-alignment: center;");

        //Napit
        Button btyleiskalenteri = new Button("Yleiskalenteri");
        Button btvarauskalenteri = new Button("Varauskalenteri");
        Button btmokkihallinnointi = new Button("Mokkien hallinnointi");
        Button bttaustaohjelma = new Button("Taustaohjelma");

        //Napit valikkoon
        menu.getChildren().addAll(btyleiskalenteri, btvarauskalenteri, btmokkihallinnointi, bttaustaohjelma);

        //Nappien sijainti
        root.setCenter(menu);

        Scene scene = new Scene(root, 600, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Varausjärjestelmä");
        primaryStage.show();

        btyleiskalenteri.setOnAction(e -> {
            Yleiskalenteri yleiskalenteri = new Yleiskalenteri();
            primaryStage.setScene(yleiskalenteri.createScene(primaryStage));
        });

        btvarauskalenteri.setOnAction(e -> {
            Varauskalenteri varauskalenteri = new Varauskalenteri();
            primaryStage.setScene(varauskalenteri.createScene(primaryStage));
        });

        btmokkihallinnointi.setOnAction(e -> {
            Mokkihallinnointi mokkihallinnointi = new Mokkihallinnointi();
            primaryStage.setScene(mokkihallinnointi.createScene(primaryStage));
        });

        bttaustaohjelma.setOnAction(e -> {
            Taustaohjelma taustaohjelma = new Taustaohjelma();
            primaryStage.setScene(taustaohjelma.createScene(primaryStage));
        });

        return scene;
    }
}
