package com.example.miniprojekti;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.ComboBox;

import java.sql.*;
import java.time.LocalDate;


public class Varauskalenteri extends Application {

    public TableView<Mokki> table;
    public ObservableList<Mokki> data;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(createScene());
        primaryStage.setTitle("Varauskalenteri");
        primaryStage.show();
    }

    public Scene createScene() {

        table = new TableView<>();
        data = FXCollections.observableArrayList();
        table.setItems(data);

        //Lomake varauksen lisäämiseen
        TextField nameField = new TextField();
        nameField.setPromptText("Varaajan nimi");
        nameField.setMaxWidth(150);

        TextField emailField = new TextField();
        emailField.setPromptText("Sähköposti");
        emailField.setMaxWidth(150);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Puhelinnumero");
        phoneField.setMaxWidth(150);

        TextField peopleField = new TextField();
        peopleField.setPromptText("Henkilömäärä");
        peopleField.setMaxWidth(150);

        HBox peopleBox = new HBox(5);
        peopleBox.getChildren().addAll(nameField, emailField, phoneField, peopleField);

        TextField mokkiField = new TextField();
        mokkiField.setPromptText("Varattu mökki");
        mokkiField.setMaxWidth(150);

        ComboBox<String> lisaSankyBox = new ComboBox<>();
        lisaSankyBox.getItems().addAll("Kyllä", "Ei");
        lisaSankyBox.setPromptText("Lisäsängyn tarve");
        lisaSankyBox.setMaxWidth(150);

        //TextField lisaSankyField = new TextField();
        //lisaSankyField.setPromptText("Lisäsängyn tarve");
        //lisaSankyField.setMaxWidth(150);

        //TextField cleaningField = new TextField();
        //cleaningField.setPromptText("Siivouspalvelu");
        //cleaningField.setMaxWidth(150);

        ComboBox<String> cleaningBox = new ComboBox<>();
        cleaningBox.getItems().addAll("Kyllä", "Ei");
        cleaningBox.setPromptText("Siivouspalvelu");
        cleaningBox.setMaxWidth(150);

        //TextField lateCOField = new TextField();
        //lateCOField.setPromptText("Myöhäinen uloskirjautuminen");
        //lateCOField.setMaxWidth(150);


        ComboBox<String> lateCOBox = new ComboBox<>();
        lateCOBox.getItems().addAll("Kyllä", "Ei");
        lateCOBox.setPromptText("Myöhäinen uloskirjautuminen");
        lateCOBox.setMaxWidth(250);


        HBox mokkiBox = new HBox(5);
        mokkiBox.getChildren().addAll(mokkiField, lisaSankyBox, cleaningBox, lateCOBox);

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

        //Loppu ja alkupäivät
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        Label startDateLabel = new Label("Saapumispäivä:");
        Label endDateLabel = new Label("Lähtöpäivä:");

        VBox startDateVBox = new VBox(5);
        startDateVBox.getChildren().addAll(startDateLabel, startDatePicker);

        VBox endDateVBox = new VBox(5);
        endDateVBox.getChildren().addAll(endDateLabel, endDatePicker);

        HBox datesBox = new HBox(5);
        datesBox.getChildren().addAll(startDateVBox, endDateVBox);

        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(startDatePicker.getValue().plusDays(1));

        //Nappien luonti
        Button addButton = new Button("Lisää varaus");
        addButton.setOnAction(e -> {
            try {
                Varaus varaus = new Varaus(
                        nameField.getText(),
                        emailField.getText(),
                        phoneField.getText(),
                        Integer.parseInt(peopleField.getText()),
                        Integer.parseInt(mokkiField.getText()),
                        lisaSankyBox.getValue().equals("Kyllä"),
                        cleaningBox.getValue().equals("Kyllä"),
                        lateCOBox.getValue().equals("Kyllä"),
                        Double.parseDouble(priceField.getText()),
                        cardNumberField.getText(),
                        validityField.getText(),
                        securityField.getText(),
                        startDatePicker.getValue(),
                        endDatePicker.getValue()
                );
                //addAsiakasToDatabase(varaus);
                new Alert(Alert.AlertType.INFORMATION, "Varaus lisätty onnistuneesti!").show();
            } catch (Exception ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Virhe varauksen lisäämisessä. Tarkista kentät.").show();
            }
        });

        Button deleteButton = new Button("Poista varaus");

        Button editButton = new Button("Muokkaa varausta");

        Button findButton = new Button("Etsi varaus");

        HBox buttons = new HBox(5);
        buttons.getChildren().addAll(addButton, deleteButton, editButton, findButton);

        //Asettelu
        VBox vbox = new VBox(10);

        vbox.getChildren().addAll(infoVBoxs, datesBox, buttons);

        Scene scene = new Scene(vbox, 800, 600);
        return scene;
    }
/*
    public void loadAsiakasFromDatabase() {

        String url = "jdbc:mysql://localhost:3306/asiakasdb";
        String user = "root";
        String password = "HirttoKoysi150!";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
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

    public void addAsiakasToDatabase(Varaus varaus) {

        String url = "jdbc:mysql://localhost:3306/asiaksdb";
        String user = "root";
        String password = "HirttoKoysi150!";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql =
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, varaus.getHenkiloMaara());
            statement.setString(2, varaus.getEtaisyys());
            statement.setString(3, varaus.getSauna());
            statement.setString(4, varaus.getPoreamme());
            statement.setString(5, varaus.getHintaPerYo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAsiakasFromDatabase(int id) {

        String url = "jdbc:mysql://localhost:3306/asiakasdb";
        String user = "root";
        String password = "HirttoKoysi150!";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "delete from asiakas where id = ?";
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setInt(1, id);
            statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAsiakasInDatabase(Mokki mokki) {

        String url = "jdbc:mysql://localhost:3306/asiaksdb";
        String user = "root";
        String password = "HirttoKoysi150!";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
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

    public void addSampleAsiakas() {
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
*/
}
