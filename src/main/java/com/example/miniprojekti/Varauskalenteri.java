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

    public TableView<Varaus> table;
    public ObservableList<Varaus> data;

    public String url = "jdbc:mysql://localhost:3306/asiakasdb";
    public String user = "root";
    public String password = "HirttoKoysi150!";

    private TextField nameField, emailField, phoneField, peopleField, mokkiField, summaField, korttiNumeroField, voimassaoloaikaField, turvakoodiField;
    private ComboBox<String> lisaSankyBox, cleaningBox, lateCOBox;
    private DatePicker saapumispvmField, lahtopvmField;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(createScene(primaryStage));
        primaryStage.setTitle("Varauskalenteri");
        primaryStage.show();
    }

    public Scene createScene(Stage primaryStage) {

        table = new TableView<>();
        data = FXCollections.observableArrayList();
        table.setItems(data);

        TableColumn<Varaus, String> nimiColumn = new TableColumn<>("Nimi");
        nimiColumn.setCellValueFactory(cellData -> cellData.getValue().nimiProperty());

        TableColumn<Varaus, String> sahkopostiColumn = new TableColumn<>("Sähköposti");
        sahkopostiColumn.setCellValueFactory(cellData -> cellData.getValue().sahkopostiProperty());

        TableColumn<Varaus, String> puhelinColumn = new TableColumn<>("Puhelin");
        puhelinColumn.setCellValueFactory(cellData -> cellData.getValue().puhelinProperty());

        TableColumn<Varaus, Number> henkiloLkmColumn = new TableColumn<>("Henkilömäärä");
        henkiloLkmColumn.setCellValueFactory(cellData -> cellData.getValue().henkiloLkmProperty());

        TableColumn<Varaus, String> mokkiIdColumn = new TableColumn<>("Mökki");
        mokkiIdColumn.setCellValueFactory(cellData -> cellData.getValue().mokkiProperty());

        TableColumn<Varaus, Boolean> lisaSankyColumn = new TableColumn<>("Lisäpatja");
        lisaSankyColumn.setCellValueFactory(cellData -> cellData.getValue().lisaSankyProperty());

        TableColumn<Varaus, Boolean> siivousColumn = new TableColumn<>("Siivous");
        siivousColumn.setCellValueFactory(cellData -> cellData.getValue().siivousProperty());

        TableColumn<Varaus, Boolean> myohainenUloskirjautuminenColumn = new TableColumn<>("Myöhäinen Uloskirjautuminen");
        myohainenUloskirjautuminenColumn.setCellValueFactory(cellData -> cellData.getValue().myohainenUloskirjautuminenProperty());

        TableColumn<Varaus, Number> summaColumn = new TableColumn<>("Summa");
        summaColumn.setCellValueFactory(cellData -> cellData.getValue().summaProperty());

        TableColumn<Varaus, String> korttiNumeroColumn = new TableColumn<>("Korttinumero");
        korttiNumeroColumn.setCellValueFactory(cellData -> cellData.getValue().korttiNumeroProperty());

        TableColumn<Varaus, String> voimassaoloaikaColumn = new TableColumn<>("Voimassaoloaika");
        voimassaoloaikaColumn.setCellValueFactory(cellData -> cellData.getValue().voimassaoloaikaProperty());

        TableColumn<Varaus, String> turvakoodiColumn = new TableColumn<>("Turvakoodi");
        turvakoodiColumn.setCellValueFactory(cellData -> cellData.getValue().turvakoodiProperty());

        TableColumn<Varaus, String> saapumispvmColumn = new TableColumn<>("Saapumispvm");
        saapumispvmColumn.setCellValueFactory(cellData -> cellData.getValue().saapumispvmProperty());

        TableColumn<Varaus, String> lahtopvmColumn = new TableColumn<>("Lähtöpvm");
        lahtopvmColumn.setCellValueFactory(cellData -> cellData.getValue().lahtopvmProperty());

        table.getColumns().addAll(
                nimiColumn,
                sahkopostiColumn,
                puhelinColumn,
                henkiloLkmColumn,
                mokkiIdColumn,
                lisaSankyColumn,
                siivousColumn,
                myohainenUloskirjautuminenColumn,
                summaColumn,
                korttiNumeroColumn,
                voimassaoloaikaColumn,
                turvakoodiColumn,
                saapumispvmColumn,
                lahtopvmColumn
        );

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
                        mokkiField.getText(),
                        lisaSankyBox.getValue().equals("Kyllä"),
                        cleaningBox.getValue().equals("Kyllä"),
                        lateCOBox.getValue().equals("Kyllä"),
                        false,
                        Double.parseDouble(priceField.getText()),
                        cardNumberField.getText(),
                        validityField.getText(),
                        securityField.getText(),
                        startDatePicker.getValue(),
                        endDatePicker.getValue()
                );

                addVarausToDatabase(varaus);
                loadVarausFromDatabase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button deleteButton = new Button("Poista varaus");
        deleteButton.setOnAction(e -> {
            Varaus selectedVaraus = table.getSelectionModel().getSelectedItem();
            if (selectedVaraus != null) {
                deleteVarausFromDatabase(selectedVaraus.getId());
                data.remove(selectedVaraus);
            }
        });

        Button editButton = new Button("Muokkaa varausta");
        editButton.setOnAction(e -> {
            Varaus selectedVaraus = table.getSelectionModel().getSelectedItem();
            if (selectedVaraus != null) {
                selectedVaraus.setNimi(nameField.getText());
                selectedVaraus.setSahkoposti(emailField.getText());
                selectedVaraus.setPuhelin(phoneField.getText());
                selectedVaraus.setHenkiloLkm(Integer.parseInt(peopleField.getText()));
                selectedVaraus.setMokki(mokkiField.getText());
                selectedVaraus.setLisaSanky(lisaSankyBox.getValue().equals("Kyllä"));
                selectedVaraus.setSiivous(cleaningBox.getValue().equals("Kyllä"));
                selectedVaraus.setMyohainenUloskirjautuminen(lateCOBox.getValue().equals("Kyllä"));
                selectedVaraus.setSumma(Double.parseDouble(priceField.getText()));
                selectedVaraus.setKorttiNumero(cardNumberField.getText());
                selectedVaraus.setVoimassaoloaika(validityField.getText());
                selectedVaraus.setTurvakoodi(securityField.getText());
                selectedVaraus.setSaapumispvm(startDatePicker.getValue());
                selectedVaraus.setLahtopvm(endDatePicker.getValue());
                updateVarausInDatabase(selectedVaraus);
                table.refresh();
            }
        });

        Button findButton = new Button("Etsi varaus");
        findButton.setOnAction(e -> {
            etsiVaraus();
        });

        //Paluu etusivulle -painike
        Button btPaluu = new Button("Palaa etusivulle");
        btPaluu.setOnAction(e -> {
            primaryStage.setScene(new Main().createScene(primaryStage));
        });

        HBox buttons = new HBox(5);
        buttons.getChildren().addAll(addButton, deleteButton, editButton, findButton, btPaluu);

        //Asettelu
        VBox vbox = new VBox(10);

        vbox.getChildren().addAll(infoVBoxs, datesBox, buttons, table);

        Scene scene = new Scene(vbox, 1130, 600);

        loadVarausFromDatabase();
        addSampleVaraus();

        return scene;
    }

    private void etsiVaraus() {

        String nimiSearch = nameField.getText().toLowerCase();
        String sahkopostiSearch = emailField.getText().toLowerCase();
        String puhelinSearch = phoneField.getText().toLowerCase();
        String peopleSearch = peopleField.getText();
        String mokkiSearch = mokkiField.getText().toLowerCase();
        String lisaSankySearch = lisaSankyBox.getValue();
        String siivousSearch = cleaningBox.getValue();
        String lateCOSearch = lateCOBox.getValue();
        String summaSearch = summaField.getText();
        String korttiNumeroSearch = korttiNumeroField.getText();
        String voimassaoloaikaSearch = voimassaoloaikaField.getText();
        String turvakoodiSearch = turvakoodiField.getText();
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

            // Suodata puhelimella
            if (!puhelinSearch.isEmpty() && !varaus.getPuhelin().toLowerCase().contains(puhelinSearch)) {
                matches = false;
            }

            // Suodata henkilömäärällä
            if (!peopleSearch.isEmpty() && !String.valueOf(varaus.getHenkiloLkm()).contains(peopleSearch)) {
                matches = false;
            }

            // Suodata mökin ID:llä
            if (!mokkiSearch.isEmpty() && !String.valueOf(varaus.getMokki()).contains(mokkiSearch)) {
                matches = false;
            }

            // Suodata lisäsängyn tarpeella
            if (lisaSankySearch != null && !lisaSankySearch.isEmpty() && varaus.isLisaSanky() != Boolean.parseBoolean(lisaSankySearch)) {
                matches = false;
            }

            // Suodata siivouspalvelulla
            if (siivousSearch != null && !siivousSearch.isEmpty() && varaus.isSiivous() != Boolean.parseBoolean(siivousSearch)) {
                matches = false;
            }

            // Suodata myöhäisellä uloskirjautumisella
            if (lateCOSearch != null && !lateCOSearch.isEmpty() && varaus.isMyohainenUloskirjautuminen() != Boolean.parseBoolean(lateCOSearch)) {
                matches = false;
            }

            // Suodata summalla
            if (!summaSearch.isEmpty() && varaus.getSumma() != Double.parseDouble(summaSearch)) {
                matches = false;
            }

            // Suodata korttinumeroilla
            if (!korttiNumeroSearch.isEmpty() && !varaus.getKorttiNumero().contains(korttiNumeroSearch)) {
                matches = false;
            }

            // Suodata voimassaoloajalla
            if (!voimassaoloaikaSearch.isEmpty() && !varaus.getVoimassaoloaika().contains(voimassaoloaikaSearch)) {
                matches = false;
            }

            // Suodata turvakoodilla
            if (!turvakoodiSearch.isEmpty() && !varaus.getTurvakoodi().contains(turvakoodiSearch)) {
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

    public void loadVarausFromDatabase() {

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from varaus";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet resultSet = statment.executeQuery();

            data.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nimi = resultSet.getString("nimi");
                String sahkoposti = resultSet.getString("sahkoposti");
                String puhelin = resultSet.getString("puhelin");
                int henkiloLkm = resultSet.getInt("henkilo_lkm");
                String mokki = resultSet.getString("mokki");
                boolean lisaSanky = resultSet.getBoolean("lisa_sanky");
                boolean siivous = resultSet.getBoolean("siivous");
                boolean myohainenUloskirjautuminen = resultSet.getBoolean("myohainen_uloskirjautuminen");
                double summa = resultSet.getDouble("summa");
                String korttiNumero = resultSet.getString("kortti_numero");
                String voimassaoloaika = resultSet.getString("voimassaoloaika");
                String turvakoodi = resultSet.getString("turvakoodi");
                Date saapumispvm = resultSet.getDate("saapumispvm");
                Date lahtopvm = resultSet.getDate("lahtopaivamaara");

                Varaus varaus = new Varaus(id, nimi, sahkoposti, puhelin, henkiloLkm, mokki,
                        lisaSanky, siivous, myohainenUloskirjautuminen,
                        summa, korttiNumero, voimassaoloaika, turvakoodi,
                        saapumispvm.toLocalDate(), lahtopvm.toLocalDate());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addVarausToDatabase(Varaus varaus) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO varaus (nimi, sahkoposti, puhelin, henkilo_lkm, mokki, lisa_sanky, siivous, myohainen_uloskirjautuminen, cleaning, summa, kortti_numero, voimassaoloaika, turvakoodi, saapumispvm, lahtopaivamaara) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, varaus.getNimi());
            statement.setString(2, varaus.getSahkoposti());
            statement.setString(3, varaus.getPuhelin());
            statement.setInt(4, varaus.getHenkiloLkm());
            statement.setString(5, varaus.getMokki());
            statement.setBoolean(6, varaus.isLisaSanky());
            statement.setBoolean(7, varaus.isSiivous());
            statement.setBoolean(8, varaus.isMyohainenUloskirjautuminen());
            statement.setBoolean(9, varaus.isSiivous()); // UUSI RIVI
            statement.setDouble(10, varaus.getSumma());
            statement.setString(11, varaus.getKorttiNumero());
            statement.setString(12, varaus.getVoimassaoloaika());
            statement.setString(13, varaus.getTurvakoodi());
            statement.setDate(14, Date.valueOf(varaus.getSaapumispvm()));
            statement.setDate(15, Date.valueOf(varaus.getLahtopvm()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteVarausFromDatabase(int id) {

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql ="DELETE FROM varaus WHERE id = ?";
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setInt(1, id);
            statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVarausInDatabase(Varaus varaus) {

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "UPDATE varaus SET nimi = ?, sahkoposti = ?, puhelin = ?, henkilo_lkm = ?, mokki = ?, lisa_sanky = ?, siivous = ?, myohainen_uloskirjautuminen = ?, summa = ?, kortti_numero = ?, voimassaoloaika = ?, turvakoodi = ?, saapumispvm = ?, lahtopaivamaara = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, varaus.getNimi());
            statement.setString(2, varaus.getSahkoposti());
            statement.setString(3, varaus.getPuhelin());
            statement.setInt(4, varaus.getHenkiloLkm());
            statement.setString(5, varaus.getMokki());
            statement.setBoolean(6, varaus.isLisaSanky());
            statement.setBoolean(7, varaus.isSiivous());
            statement.setBoolean(8, varaus.isMyohainenUloskirjautuminen());
            statement.setDouble(9, varaus.getSumma());
            statement.setString(10, varaus.getKorttiNumero());
            statement.setString(11, varaus.getVoimassaoloaika());
            statement.setString(12, varaus.getTurvakoodi());
            statement.setDate(13, Date.valueOf(varaus.getSaapumispvm()));
            statement.setDate(14, Date.valueOf(varaus.getLahtopvm()));
            statement.setInt(15, varaus.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSampleVaraus() {
        if (data.isEmpty()) {
            String[][] sampleData = {
                    {"Erkki Nyström", "erkki.nystrom@gmail.com", "Erik", "210.00", "Ei", "Kyllä", "Ei", "2025-06-01", "2025-06-07", "3654 8032 0034 3856", "01/28", "985", "4", "0401234567", "Kyllä"},
                    {"Heikki Jokinen", "heikki.jokinen@gmail.com", "Pilvi", "275.00", "Kyllä", "Ei", "Kyllä", "2025-06-03", "2025-06-07", "2255 6435 7987 7762", "05/27", "553", "2", "0509876543", "Ei"},
                    {"Jussi Korhonen", "jussi.korhonen@icloud.com", "Liisa", "290.00", "Kyllä", "Kyllä", "Ei", "2025-06-01", "2025-06-10", "8785 0066 4653 6980", "06/29", "330", "3", "0447654321", "Kyllä"}
            };

            for (String[] varausData : sampleData) {
                String nimi = varausData[0];
                String sahkoposti = varausData[1];
                String mokki = varausData[2];
                double summa = Double.parseDouble(varausData[3]);
                boolean lisaSanky = varausData[4].equalsIgnoreCase("Kyllä");
                boolean siivous = varausData[5].equalsIgnoreCase("Kyllä");
                boolean myohainenLahto = varausData[6].equalsIgnoreCase("Kyllä");
                LocalDate saapumispvm = LocalDate.parse(varausData[7]);
                LocalDate lahtopvm = LocalDate.parse(varausData[8]);
                String korttiNumero = varausData[9];
                String voimassaoloaika = varausData[10];
                String turvakoodi = varausData[11];
                int henkiloLkm = Integer.parseInt(varausData[12]);
                String puhelin = varausData[13];
                boolean cleaning = varausData[14].equalsIgnoreCase("Kyllä");

                Varaus varaus = new Varaus(
                        nimi,
                        sahkoposti,
                        puhelin,
                        henkiloLkm,
                        mokki,
                        lisaSanky,
                        siivous,
                        myohainenLahto,
                        cleaning,
                        summa,
                        korttiNumero,
                        voimassaoloaika,
                        turvakoodi,
                        saapumispvm,
                        lahtopvm
                );
                addVarausToDatabase(varaus);
            }
            loadVarausFromDatabase();
        }
    }
}
