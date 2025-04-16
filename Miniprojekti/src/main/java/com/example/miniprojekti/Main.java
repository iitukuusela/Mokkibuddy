package com.example.miniprojekti;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main extends Application {

    private TableView<Mokki> table;
    private ObservableList<Mokki> data;

    @Override
    public void start(Stage primaryStage) {

        //Taulukko mökkien näyttämiseen
        table = new TableView<>();
        data = FXCollections.observableArrayList();
        table.setItems(data);

        TableColumn<Mokki, String> capacityColumn = new TableColumn<>("Henkilömäärä");
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().henkiloMaaraProperty());

        TableColumn<Mokki, String> distanceColumn = new TableColumn<>("Etäisyys");
        distanceColumn.setCellValueFactory(cellData -> cellData.getValue().etaisyysProperty());

        TableColumn<Mokki, String> saunaColumn = new TableColumn<>("Sauna");
        saunaColumn.setCellValueFactory(cellData -> cellData.getValue().saunaProperty());

        TableColumn<Mokki, String> hotTubColumn = new TableColumn<>("Poreamme");
        hotTubColumn.setCellValueFactory(cellData -> cellData.getValue().poreammeProperty());

        TableColumn<Mokki, String> priceColumn = new TableColumn<>("Hinta per yö");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().hintaPerYoProperty());

        table.getColumns().addAll(capacityColumn, distanceColumn, saunaColumn, hotTubColumn, priceColumn);

        //Lomake mökin lisäämiseen
        TextField capacityField = new TextField();
        capacityField.setPromptText("Henkilömäärä");

        TextField distanceField = new TextField();
        distanceField.setPromptText("Etäisyys");

        TextField saunaField = new TextField();
        saunaField.setPromptText("Sauna");

        TextField hotTubField = new TextField();
        hotTubField.setPromptText("Poreamme");

        TextField priceField = new TextField();
        priceField.setPromptText("Hinta per yö");

        Button addButton = new Button("Lisää mökki");
        addButton.setOnAction(e -> {
            Mokki mokki = new Mokki(capacityField.getText(), distanceField.getText(), saunaField.getText(), hotTubField.getText(), priceField.getText());
            data.add(mokki);
            //Lisätään mökki tietokantaan
            addMokkiToDatabase(mokki);
        });
        VBox vBox = new VBox(capacityField, distanceField, saunaField, hotTubField, priceField, addButton, table);
        Scene scene = new Scene(vBox, 600, 400);

        primaryStage.setTitle("Mökkien hallinta");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Lisää esimerkkimökit
        addSampleMokki();
    }

    private void addSampleMokki() {
        String [][] sampleData = {
                {"6", "2 km", "Kyllä", "Ei", "210.00"},
                {"15", "2.8 km", "Kyllä", "Kyllä", "275.00"},
                {"20", "4 km", "Kyllä", "Kyllä", "290.00"},
                {"4", "2.2 km", "Kyllä", "Ei", "185.00"},
                {"13", "3.5 km", "Kyllä", "Kyllä", "270.00"},
                {"10", "3 km", "Kyllä", "Ei", "255.00"},
                {"8", "4.9 km", "Ei", "Ei", "200.00"},
                {"25", "6 km", "Kyllä", "Kyllä", "310.00"},
                {"5", "5 km", "Ei", "Ei", "150.00"},
                {"11", "5.5 km", "Kyllä", "Kyllä", "259.00"},
        };

        for (String[] mokkiData : sampleData) {
            Mokki mokki = new Mokki(mokkiData[0], mokkiData[1], mokkiData[2], mokkiData[3], mokkiData[4]);
            data.add(mokki);
            addMokkiToDatabase(mokki);
        }
    }

    private void addMokkiToDatabase(Mokki mokki) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mokkitietokanta", "root", "70100aamu");
            String sql = "INSERT INTO mokki (henkilo_maara, etaisyys, sauna, poreamme, hinta_per_yo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mokki.getHenkiloMaara());
            statement.setString(2, mokki.getEtaisyys());
            statement.setString(3, mokki.getSauna());
            statement.setString(4, mokki.getPoreamme());
            statement.setString(5, mokki.getHintaPerYo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
