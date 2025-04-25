package com.example.miniprojekti;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mokkivaraus extends Application {

    private TableView<Mokki> table;
    private ObservableList<Mokki> data;

    @Override
    public void start(Stage primaryStage) {

        //Taulukko mökkien näyttämiseen
        table = new TableView<>();
        data = FXCollections.observableArrayList();
        table.setItems(data);

        //TableColumn<Mokki, Number> idColumn = new TableColumn<>("ID");
        //idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<Mokki, String> capacityColumn = new TableColumn<>("Henkilömäärä");
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().henkiloMaaraProperty());

        TableColumn<Mokki, String> distanceColumn = new TableColumn<>("Etäisyys (km)");
        distanceColumn.setCellValueFactory(cellData -> cellData.getValue().etaisyysProperty());

        TableColumn<Mokki, String> saunaColumn = new TableColumn<>("Sauna");
        saunaColumn.setCellValueFactory(cellData -> cellData.getValue().saunaProperty());

        TableColumn<Mokki, String> hotTubColumn = new TableColumn<>("Poreamme");
        hotTubColumn.setCellValueFactory(cellData -> cellData.getValue().poreammeProperty());

        TableColumn<Mokki, String> priceColumn = new TableColumn<>("Hinta per yö");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().hintaPerYoProperty());

        //table.getColumns().addAll(idColumn, capacityColumn, distanceColumn, saunaColumn, hotTubColumn, priceColumn);
        table.getColumns().addAll(capacityColumn, distanceColumn, saunaColumn, hotTubColumn, priceColumn);

        //Lomake mökin lisäämiseen
        TextField capacityField = new TextField();
        capacityField.setPromptText("Henkilömäärä");
        capacityField.setMaxWidth(200);

        TextField distanceField = new TextField();
        distanceField.setPromptText("Etäisyys km");
        distanceField.setMaxWidth(200);

        TextField saunaField = new TextField();
        saunaField.setPromptText("Sauna");
        saunaField.setMaxWidth(200);

        TextField hotTubField = new TextField();
        hotTubField.setPromptText("Poreamme");
        hotTubField.setMaxWidth(200);

        TextField priceField = new TextField();
        priceField.setPromptText("Hinta per yö");
        priceField.setMaxWidth(200);

        //Buttonien lisäys
        Button addButton = new Button("Lisää mökki");
        addButton.setOnAction(e -> {
            Mokki mokki = new Mokki(0, capacityField.getText(), distanceField.getText(), saunaField.getText(), hotTubField.getText(), priceField.getText());
            addMokkiToDatabase(mokki);
            loadMokitFromDatabase();
        });

        Button deleteButton = new Button("Poista mökki");
        deleteButton.setOnAction(e -> {
            Mokki selectedMokki = table.getSelectionModel().getSelectedItem();
            if (selectedMokki != null) {
                deleteMokkiFromDatabase(selectedMokki.getId());
                data.remove(selectedMokki);
            }
        });

        Button editButton = new Button("Muokkaa mökkiä");
        editButton.setOnAction(e -> {
            Mokki selectedMokki = table.getSelectionModel().getSelectedItem();
            if (selectedMokki != null) {
                selectedMokki.setHenkiloMaara(capacityField.getText());
                selectedMokki.setEtaisyys(distanceField.getText());
                selectedMokki.setSauna(saunaField.getText());
                selectedMokki.setPoreamme(hotTubField.getText());
                selectedMokki.setHintaPerYo(priceField.getText());
                updateMokkiInDatabase(selectedMokki);
                table.refresh();
            }
        });

        HBox hbox = new HBox(10, addButton, deleteButton, editButton);

        VBox vBox = new VBox(5, capacityField, distanceField, saunaField, hotTubField, priceField, hbox, table);
        Scene scene = new Scene(vBox, 400, 600);

        primaryStage.setTitle("Mökkien hallinta");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Lisää esimerkkimökit
        loadMokitFromDatabase();
        addSampleMokki();
    }

    private void loadMokitFromDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mokkidb", "root", "70100aamu");
            String sql = "select * from mokki";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet resultSet = statment.executeQuery();

            data.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String henkiloMaara = resultSet.getString("henkilo_maara");
                String etaisyys = resultSet.getString("etaisyys");
                String sauna = resultSet.getString("sauna");
                String poreamme = resultSet.getString("poreamme");
                String hintaPerYo = resultSet.getString("hinta_per_yo");

                Mokki mokki = new Mokki(id, henkiloMaara, etaisyys, sauna, poreamme, hintaPerYo);
                data.add(mokki);
            }
        } catch (SQLException e) {                                                                                                     
            e.printStackTrace();
        }
    }

    private void addMokkiToDatabase(Mokki mokki) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mokkidb", "root", "70100aamu");
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

    private void deleteMokkiFromDatabase(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mokkidb", "root", "70100aamu");
            String sql = "delete from mokki where id = ?";
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setInt(1, id);
            statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateMokkiInDatabase(Mokki mokki) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mokkidb", "root", "70100aamu");
            String sql = "update mokki set henkilo_maara = ?, etaisyys = ?, sauna = ?, poreamme = ?, hinta_per_yo = ? where id = ?";
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setString(1, mokki.getHenkiloMaara());
            statment.setString(2, mokki.getEtaisyys());
            statment.setString(3, mokki.getSauna());
            statment.setString(4, mokki.getPoreamme());
            statment.setString(5, mokki.getHintaPerYo());
            statment.setInt(6, mokki.getId());
            statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addSampleMokki() {
        if (data.isEmpty()) {
            String[][] sampleData = {
                    {"6", "2", "Kyllä", "Ei", "210.00"},
                    {"15", "2.8", "Kyllä", "Kyllä", "275.00"},
                    {"20", "4", "Kyllä", "Kyllä", "290.00"},
                    {"4", "2.2", "Kyllä", "Ei", "185.00"},
                    {"13", "3.5", "Kyllä", "Kyllä", "270.00"},
                    {"10", "3", "Kyllä", "Ei", "255.00"},
                    {"8", "4.9", "Ei", "Ei", "200.00"},
                    {"25", "6", "Kyllä", "Kyllä", "310.00"},
                    {"5", "5", "Ei", "Ei", "150.00"},
                    {"11", "5.5", "Kyllä", "Kyllä", "259.00"},
            };

            for (String[] mokkiData : sampleData) {
                Mokki mokki = new Mokki(
                        mokkiData[0],
                        mokkiData[1],
                        mokkiData[2],
                        mokkiData[3],
                        mokkiData[4]
                );
                addMokkiToDatabase(mokki);
            }
            loadMokitFromDatabase();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
