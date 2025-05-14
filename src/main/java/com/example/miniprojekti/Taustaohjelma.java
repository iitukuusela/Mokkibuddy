package com.example.miniprojekti;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Taustaohjelma extends Application {

    public TableView<Varaus> table;
    public ObservableList<Varaus> data;

    public String url = "jdbc:mysql://localhost:3306/asiakasdb";
    public String user = "root";
    public String password = "HirttoKoysi150!";

    private TextField nameField, emailField, phoneField, peopleField, mokkiField, summaField, korttiNumeroField, voimassaoloaikaField, turvakoodiField;
    private ComboBox<String> lisaSankyBox, cleaningBox, lateCOBox;
    private DatePicker saapumispvmField, lahtopvmField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(createScene(primaryStage));
        primaryStage.setTitle("Taustaohjelma");
        primaryStage.show();
    }

    public Scene createScene(Stage primaryStage) {

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

        HBox tiedotBox = new HBox(5);
        tiedotBox.getChildren().addAll(nameField, emailField);

        TextField mokkiField = new TextField();
        mokkiField.setPromptText("Mökki");
        mokkiField.setMaxWidth(150);

        TextField priceField = new TextField();
        priceField.setPromptText("Summa");
        priceField.setMaxWidth(150);

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
        Button findButton = new Button("Etsi");
        findButton.setOnAction(e -> {
            etsiVaraus();
        });

        Button luoPDFButton = new Button("Luo PDF");
        luoPDFButton.setOnAction(e -> {
            luoPdf();
        });

        //Paluu etusivulle -painike
        Button btPaluu = new Button("Palaa etusivulle");
        btPaluu.setOnAction(e -> {
            primaryStage.setScene(new Main().createScene(primaryStage));
        });

        HBox buttonbox = new HBox(5);
        buttonbox.getChildren().addAll(findButton, luoPDFButton, btPaluu);

        //Asettelu
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(allInfoBox, buttonbox);

        Scene scene = new Scene(vBox, 600, 400);
        return scene;
    }

    public void luoPdf() {

    }

    public void etsiVaraus() {
        String nimiSearch = nameField.getText().toLowerCase();
        String sahkopostiSearch = emailField.getText().toLowerCase();
        String mokkiSearch = mokkiField.getText().toLowerCase();
        String summaSearch = summaField.getText();
        String saapumispvmSearch = saapumispvmField.getValue().toString();
        String lahtopvmSearch = lahtopvmField.getValue().toString();

        // Suodatetaan alkuperäinen lista (data)
        ObservableList<Varaus> filteredData = FXCollections.observableArrayList();

        for (Varaus varaus : data) {
            boolean matches = true;

            // Suodata nimellä
            if (!nimiSearch.isEmpty() && !varaus.getNimi().toLowerCase().contains(nimiSearch)) {
                matches = false;
            }

            // Suodata sähköpostilla
            if (!sahkopostiSearch.isEmpty() && !varaus.getSahkoposti().toLowerCase().contains(sahkopostiSearch)) {
                matches = false;
            }

            // Suodata mökin ID:llä
            if (!mokkiSearch.isEmpty() && !String.valueOf(varaus.getMokkiId()).contains(mokkiSearch)) {
                matches = false;
            }

            // Suodata summalla
            if (!summaSearch.isEmpty() && varaus.getSumma() != Double.parseDouble(summaSearch)) {
                matches = false;
            }

            // Suodata saapumispäivämäärällä
            if (!saapumispvmSearch.isEmpty() && !varaus.getSaapumispvm().toString().contains(saapumispvmSearch)) {
                matches = false;
            }

            // Suodata lähtöpvm:llä
            if (!lahtopvmSearch.isEmpty() && !varaus.getLahtopvm().toString().contains(lahtopvmSearch)) {
                matches = false;
            }

            // Jos kaikki hakuehdot täyttyvät, lisätään varaus tuloksiin
            if (matches) {
                filteredData.add(varaus);
            }
        }
        table.setItems(filteredData);
    }
}
