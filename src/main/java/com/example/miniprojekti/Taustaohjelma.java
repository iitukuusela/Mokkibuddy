package com.example.miniprojekti;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import java.io.FileOutputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Taustaohjelma extends Application {

    public TableView<Tausta> table;
    public ObservableList<Tausta> data;

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

        TableColumn<Tausta, String> nameColumn = new TableColumn<>("Varaajan nimi");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nimiProperty());

        TableColumn<Tausta, String> mailColumn = new TableColumn<>("Sähköposti");
        mailColumn.setCellValueFactory(cellData -> cellData.getValue().sahkopostiProperty());

        TableColumn<Tausta, Number> mokkiColumn = new TableColumn<>("Mökki");
        mokkiColumn.setCellValueFactory(cellData -> cellData.getValue().mokkiIdProperty());

        TableColumn<Tausta, Number> summaColumn = new TableColumn<>("Summa");
        summaColumn.setCellValueFactory(cellData -> cellData.getValue().summaProperty());

        TableColumn<Tausta, String> tuloColumn = new TableColumn<>("Tulopäivä");
        tuloColumn.setCellValueFactory(cellData -> cellData.getValue().saapumispvmProperty());

        TableColumn<Tausta, String> lahtoColumn = new TableColumn<>("Lähtöpäivä");
        lahtoColumn.setCellValueFactory(cellData -> cellData.getValue().lahtopvmProperty());

        table.getColumns().addAll(nameColumn, mailColumn, mokkiColumn, summaColumn, tuloColumn, lahtoColumn);

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

        Label tuloLabel = new Label();
        tuloLabel.setText("Tulopäivä:");

        Label lahtoLabel = new Label();
        lahtoLabel.setText("Lähtöpäivä:");

        DatePicker tuloPicker = new DatePicker();
        tuloPicker.setValue(LocalDate.now());

        DatePicker lahtoPicker = new DatePicker();
        lahtoPicker.setValue(LocalDate.now());

        HBox tuloBox = new HBox(5);
        tuloBox.getChildren().addAll(tuloLabel, tuloPicker);

        HBox lahtoBox = new HBox(5);
        lahtoBox.getChildren().addAll(lahtoLabel, lahtoPicker);

        HBox dateBox = new HBox(5);
        dateBox.getChildren().addAll(tuloBox, lahtoBox);

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
        vBox.getChildren().addAll(allInfoBox, buttonbox, table);

        Scene scene = new Scene(vBox, 500, 600);

        loadVarausFromDatabase();
        return scene;
    }

    public void luoPdf() {
        try {
            String tiedostoPolku = "varaukset.pdf";

            PdfWriter writer = new PdfWriter(new FileOutputStream(tiedostoPolku));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Varaukset"));

            float[] columnWidths = {100F, 100F, 80F, 80F, 50F, 70F};
            Table tablePdf = new Table(columnWidths);

            tablePdf.addCell(new Cell().add(new Paragraph("Nimi")));
            tablePdf.addCell(new Cell().add(new Paragraph("Sähköposti")));
            tablePdf.addCell(new Cell().add(new Paragraph("Saapuminen")));
            tablePdf.addCell(new Cell().add(new Paragraph("Lähtö")));
            tablePdf.addCell(new Cell().add(new Paragraph("Mökki")));
            tablePdf.addCell(new Cell().add(new Paragraph("Summa")));

            List<Tausta> varaukset = table.getItems();

            for (Tausta tausta : varaukset) {
                tablePdf.addCell(tausta.getNimi());
                tablePdf.addCell(tausta.getSahkoposti());
                tablePdf.addCell(tausta.getSaapumispvm().toString());
                tablePdf.addCell(tausta.getLahtopvm().toString());
                tablePdf.addCell(String.valueOf(tausta.getMokkiId()));
                tablePdf.addCell(String.format("%.2f €", tausta.getSumma()));
            }

            document.add(tablePdf);
            document.close();

            System.out.println("PDF luotu: " + tiedostoPolku);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void etsiVaraus() {
        String nimiSearch = nameField.getText().toLowerCase();
        String sahkopostiSearch = emailField.getText().toLowerCase();
        String mokkiSearch = mokkiField.getText().toLowerCase();
        String summaSearch = summaField.getText();
        String saapumispvmSearch = saapumispvmField.getValue().toString();
        String lahtopvmSearch = lahtopvmField.getValue().toString();

        // Suodatetaan alkuperäinen lista (data)
        ObservableList<Tausta> filteredData = FXCollections.observableArrayList();

        for (Tausta tausta : data) {
            boolean matches = true;

            // Suodata nimellä
            if (!nimiSearch.isEmpty() && !tausta.getNimi().toLowerCase().contains(nimiSearch)) {
                matches = false;
            }

            // Suodata sähköpostilla
            if (!sahkopostiSearch.isEmpty() && !tausta.getSahkoposti().toLowerCase().contains(sahkopostiSearch)) {
                matches = false;
            }

            // Suodata mökin ID:llä
            if (!mokkiSearch.isEmpty() && !String.valueOf(tausta.getMokkiId()).contains(mokkiSearch)) {
                matches = false;
            }

            // Suodata summalla
            if (!summaSearch.isEmpty() && tausta.getSumma() != Double.parseDouble(summaSearch)) {
                matches = false;
            }

            // Suodata saapumispäivämäärällä
            if (!saapumispvmSearch.isEmpty() && !tausta.getSaapumispvm().toString().contains(saapumispvmSearch)) {
                matches = false;
            }

            // Suodata lähtöpvm:llä
            if (!lahtopvmSearch.isEmpty() && !tausta.getLahtopvm().toString().contains(lahtopvmSearch)) {
                matches = false;
            }

            // Jos kaikki hakuehdot täyttyvät, lisätään varaus tuloksiin
            if (matches) {
                filteredData.add(tausta);
            }
        }
        table.setItems(filteredData);
    }

    public void loadVarausFromDatabase() {

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from varaus";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet resultSet = statment.executeQuery();

            data.clear();
            while (resultSet.next()) {
                String nimi = resultSet.getString("nimi");
                String sahkoposti = resultSet.getString("sahkoposti");
                LocalDate saapumispvm = resultSet.getDate("saapumispvm").toLocalDate();
                LocalDate lahtopvm = resultSet.getDate("lahtopvm").toLocalDate();
                int mokkiId = resultSet.getInt("mokki_id");
                double summa = resultSet.getDouble("summa");

                // Lisätään uusi varaus tietokannasta
                Tausta tausta = new Tausta(nimi, sahkoposti, mokkiId, summa, saapumispvm, lahtopvm);
                data.add(tausta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
