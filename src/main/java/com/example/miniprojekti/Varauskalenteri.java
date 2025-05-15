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
import java.text.NumberFormat;
import java.time.LocalDate;


public class Varauskalenteri extends Application {

    public TableView<Varaus> table;
    public ObservableList<Varaus> data;

    public String url = "jdbc:mysql://localhost:3306/asiakasdb";
    public String user = "root";
    public String password = "HirttoKoysi150!";

    public TextField nameField, emailField, phoneField, peopleField, mokkiField, priceField, cardNumberField, validityField, securityField;
    public ComboBox<String> lisaSankyBox, cleaningBox, lateCOBox;
    public DatePicker startDatePicker, endDatePicker;

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
        this.nameField = new TextField();
        this.nameField.setPromptText("Varaajan nimi");
        this.nameField.setMaxWidth(150);

        this.emailField = new TextField();
        this.emailField.setPromptText("Sähköposti");
        this.emailField.setMaxWidth(150);

        this.phoneField = new TextField();
        this.phoneField.setPromptText("Puhelinnumero");
        this.phoneField.setMaxWidth(150);

        this.peopleField = new TextField();
        this.peopleField.setPromptText("Henkilömäärä");
        this.peopleField.setMaxWidth(150);

        HBox peopleBox = new HBox(5);
        peopleBox.getChildren().addAll(nameField, emailField, phoneField, peopleField);

        this.mokkiField = new TextField();
        this.mokkiField.setPromptText("Varattu mökki");
        this.mokkiField.setMaxWidth(150);

        this.lisaSankyBox = new ComboBox<>();
        this.lisaSankyBox.getItems().addAll("Kyllä", "Ei");
        this.lisaSankyBox.setPromptText("Lisäsängyn tarve");
        this.lisaSankyBox.setMaxWidth(150);

        //TextField lisaSankyField = new TextField();
        //lisaSankyField.setPromptText("Lisäsängyn tarve");
        //lisaSankyField.setMaxWidth(150);

        //TextField cleaningField = new TextField();
        //cleaningField.setPromptText("Siivouspalvelu");
        //cleaningField.setMaxWidth(150);

        this.cleaningBox = new ComboBox<>();
        this.cleaningBox.getItems().addAll("Kyllä", "Ei");
        this.cleaningBox.setPromptText("Siivouspalvelu");
        this.cleaningBox.setMaxWidth(150);

        //TextField lateCOField = new TextField();
        //lateCOField.setPromptText("Myöhäinen uloskirjautuminen");
        //lateCOField.setMaxWidth(150);

        this.lateCOBox = new ComboBox<>();
        this.lateCOBox.getItems().addAll("Kyllä", "Ei");
        this.lateCOBox.setPromptText("Myöhäinen uloskirjautuminen");
        this.lateCOBox.setMaxWidth(250);

        HBox mokkiBox = new HBox(5);
        mokkiBox.getChildren().addAll(mokkiField, lisaSankyBox, cleaningBox, lateCOBox);

        this.priceField = new TextField();
        this.priceField.setPromptText("Summa");
        this.priceField.setMaxWidth(150);

        this.cardNumberField = new TextField();
        this.cardNumberField.setPromptText("Kortin numero");
        this.cardNumberField.setMaxWidth(150);

        this.validityField = new TextField();
        this.validityField.setPromptText("Voimassaoloaika");
        this.validityField.setMaxWidth(150);

        this.securityField = new TextField();
        this.securityField.setPromptText("Turvanumero");
        this.securityField.setMaxWidth(150);

        HBox priceInfoBox = new HBox(5);
        priceInfoBox.getChildren().addAll(priceField, cardNumberField, validityField, securityField);

        VBox infoVBoxs = new VBox(5);
        infoVBoxs.getChildren().addAll(peopleBox, mokkiBox, priceInfoBox);

        //Loppu ja alkupäivät
        this.startDatePicker = new DatePicker();
        this.endDatePicker = new DatePicker();

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
                int henkiloLkm = 0;
                if (!peopleField.getText().trim().isEmpty()) {
                    henkiloLkm = Integer.parseInt(peopleField.getText().trim());
                }

                double summa = 0.0;
                if (!priceField.getText().trim().isEmpty()) {
                    summa = Double.parseDouble(priceField.getText().trim());
                }

                Varaus varaus = new Varaus(
                        nameField.getText().trim(),
                        emailField.getText().trim(),
                        phoneField.getText().trim(),
                        henkiloLkm,
                        mokkiField.getText().trim(),
                        "Kyllä".equals(lisaSankyBox.getValue()),
                        "Kyllä".equals(cleaningBox.getValue()),
                        "Kyllä".equals(lateCOBox.getValue()),
                        summa,
                        cardNumberField.getText().trim(),
                        validityField.getText().trim(),
                        securityField.getText().trim(),
                        startDatePicker.getValue(),
                        endDatePicker.getValue()
                );

                addVarausToDatabase(varaus);
                loadVarausFromDatabase();
            } catch (NumberFormatException ex) {
                System.out.println("Virheellinen numeroarvo (henkilömäärä tai summa).");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button deleteButton = new Button("Poista varaus");
        deleteButton.setOnAction(e -> {
            Varaus selectedVaraus = table.getSelectionModel().getSelectedItem();
            if (selectedVaraus != null) {
                System.out.println("Poistettava varaus id: " + selectedVaraus.getId());
                deleteVarausFromDatabase(selectedVaraus.getId());
                data.remove(selectedVaraus);
            } else {
                System.out.println("Ei valittua varausta poistettavaksi.");
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

        Scene scene = new Scene(vbox, 1500, 600);

        loadVarausFromDatabase();
        addSampleVaraus();

        return scene;
    }

    private void etsiVaraus() {
        String nimiSearch = (nameField.getText() != null) ? nameField.getText().toLowerCase() : "";
        String sahkopostiSearch = (emailField.getText() != null) ? emailField.getText().toLowerCase() : "";
        String puhelinSearch = (phoneField.getText() != null) ? phoneField.getText().toLowerCase() : "";
        String peopleSearch = (peopleField.getText() != null) ? peopleField.getText() : "";
        String mokkiSearch = (mokkiField.getText() != null) ? mokkiField.getText().toLowerCase() : "";
        String lisaSankySearch = (lisaSankyBox.getValue() != null) ? lisaSankyBox.getValue() : "";
        String siivousSearch = (cleaningBox.getValue() != null) ? cleaningBox.getValue() : "";
        String lateCOSearch = (lateCOBox.getValue() != null) ? lateCOBox.getValue() : "";
        String summaSearch = (priceField.getText() != null) ? priceField.getText() : "";
        String korttiNumeroSearch = (cardNumberField.getText() != null) ? cardNumberField.getText() : "";
        String voimassaoloaikaSearch = (validityField.getText() != null) ? validityField.getText() : "";
        String turvakoodiSearch = (securityField.getText() != null) ? securityField.getText() : "";
        String saapumispvmSearch = (startDatePicker.getValue() != null) ? startDatePicker.getValue().toString() : "";
        String lahtopvmSearch = (endDatePicker.getValue() != null) ? endDatePicker.getValue().toString() : "";

        ObservableList<Varaus> filteredData = FXCollections.observableArrayList();

        for (Varaus varaus : data) {
            boolean matches = true;

            if (!nimiSearch.isEmpty() && (varaus.getNimi() == null || !varaus.getNimi().toLowerCase().contains(nimiSearch))) {
                matches = false;
            }

            if (!sahkopostiSearch.isEmpty() && (varaus.getSahkoposti() == null || !varaus.getSahkoposti().toLowerCase().contains(sahkopostiSearch))) {
                matches = false;
            }

            if (!puhelinSearch.isEmpty() && (varaus.getPuhelin() == null || !varaus.getPuhelin().toLowerCase().contains(puhelinSearch))) {
                matches = false;
            }

            if (!peopleSearch.isEmpty()) {
                try {
                    int henkiloLkmSearch = Integer.parseInt(peopleSearch);
                    if (varaus.getHenkiloLkm() != henkiloLkmSearch) {
                        matches = false;
                    }
                } catch (NumberFormatException ex) {
                    matches = false;
                }
            }

            if (!mokkiSearch.isEmpty() && (varaus.getMokki() == null || !varaus.getMokki().toLowerCase().contains(mokkiSearch))) {
                matches = false;
            }

            if (!lisaSankySearch.isEmpty()) {
                boolean lisaSankyBool = "Kyllä".equalsIgnoreCase(lisaSankySearch);
                if (varaus.isLisaSanky() != lisaSankyBool) {
                    matches = false;
                }
            }

            if (!siivousSearch.isEmpty()) {
                boolean siivousBool = "Kyllä".equalsIgnoreCase(siivousSearch);
                if (varaus.isSiivous() != siivousBool) {
                    matches = false;
                }
            }

            if (!lateCOSearch.isEmpty()) {
                boolean lateCOBool = "Kyllä".equalsIgnoreCase(lateCOSearch);
                if (varaus.isMyohainenUloskirjautuminen() != lateCOBool) {
                    matches = false;
                }
            }

            if (!summaSearch.isEmpty()) {
                try {
                    double summaDouble = Double.parseDouble(summaSearch);
                    if (Double.compare(varaus.getSumma(), summaDouble) != 0) {
                        matches = false;
                    }
                } catch (NumberFormatException ex) {
                    matches = false;
                }
            }

            if (!korttiNumeroSearch.isEmpty() && (varaus.getKorttiNumero() == null || !varaus.getKorttiNumero().contains(korttiNumeroSearch))) {
                matches = false;
            }

            if (!voimassaoloaikaSearch.isEmpty() && (varaus.getVoimassaoloaika() == null || !varaus.getVoimassaoloaika().contains(voimassaoloaikaSearch))) {
                matches = false;
            }

            if (!turvakoodiSearch.isEmpty() && (varaus.getTurvakoodi() == null || !varaus.getTurvakoodi().contains(turvakoodiSearch))) {
                matches = false;
            }

            if (!saapumispvmSearch.isEmpty()) {
                if (varaus.getSaapumispvm() == null || !varaus.getSaapumispvm().toString().contains(saapumispvmSearch)) {
                    matches = false;
                }
            }

            if (!lahtopvmSearch.isEmpty()) {
                if (varaus.getLahtopvm() == null || !varaus.getLahtopvm().toString().contains(lahtopvmSearch)) {
                    matches = false;
                }
            }

            if (matches) {
                filteredData.add(varaus);
            }
        }

        table.setItems(filteredData);
    }

    public void loadVarausFromDatabase() {
        data.clear();

        String sql = "SELECT * FROM varaus";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

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

                data.add(varaus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(data);  // Päivitä TableView UI
    }

    /*
    public void addVarausToDatabase(Varaus varaus) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO varaus (nimi, sahkoposti, puhelin, henkilo_lkm, mokki, lisa_sanky, siivous, myohainen_uloskirjautuminen, summa, kortti_numero, voimassaoloaika, turvakoodi, saapumispvm, lahtopaivamaara) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Stringit
            statement.setString(1, isNullOrEmpty(varaus.getNimi()) ? null : varaus.getNimi());
            statement.setString(2, isNullOrEmpty(varaus.getSahkoposti()) ? null : varaus.getSahkoposti());
            statement.setString(3, isNullOrEmpty(varaus.getPuhelin()) ? null : varaus.getPuhelin());

            // int
            statement.setInt(4, varaus.getHenkiloLkm()); // oletetaan että on aina annettu
            statement.setString(5, isNullOrEmpty(varaus.getMokki()) ? null : varaus.getMokki());

            // boolean (ei voi olla null, käytä false oletuksena)
            statement.setBoolean(6, varaus.isLisaSanky());
            statement.setBoolean(7, varaus.isSiivous());
            statement.setBoolean(8, varaus.isMyohainenUloskirjautuminen());

            // Double – tarkista jos nolla/tyhjä -> setNull
            if (varaus.getSumma() == 0.0) {
                statement.setNull(9, java.sql.Types.DOUBLE);
            } else {
                statement.setDouble(9, varaus.getSumma());
            }

            statement.setString(10, isNullOrEmpty(varaus.getKorttiNumero()) ? null : varaus.getKorttiNumero());
            statement.setString(11, isNullOrEmpty(varaus.getVoimassaoloaika()) ? null : varaus.getVoimassaoloaika());
            statement.setString(12, isNullOrEmpty(varaus.getTurvakoodi()) ? null : varaus.getTurvakoodi());

            if (varaus.getSaapumispvm() == null) {
                statement.setNull(13, java.sql.Types.DATE);
            } else {
                statement.setDate(13, java.sql.Date.valueOf(varaus.getSaapumispvm()));
            }

            if (varaus.getLahtopvm() == null) {
                statement.setNull(14, java.sql.Types.DATE);
            } else {
                statement.setDate(14, java.sql.Date.valueOf(varaus.getLahtopvm()));
            }

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/

    public void addVarausToDatabase(Varaus varaus) {
        String sql = "INSERT INTO varaus (nimi, sahkoposti, puhelin, henkilo_lkm, mokki, lisa_sanky, siivous, myohainen_uloskirjautuminen, summa, kortti_numero, voimassaoloaika, turvakoodi, saapumispvm, lahtopaivamaara) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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
            statement.setDate(13, java.sql.Date.valueOf(varaus.getSaapumispvm()));
            statement.setDate(14, java.sql.Date.valueOf(varaus.getLahtopvm()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Varauksen lisääminen epäonnistui, ei rivejä lisätty.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    varaus.setId(generatedKeys.getInt(1));  // TÄRKEÄ: asetetaan id varaukselle!
                } else {
                    throw new SQLException("Varauksen ID:n luonti epäonnistui.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void deleteVarausFromDatabase(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM varaus WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            System.out.println("Deleted rows: " + rowsAffected);
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
                    {"Erkki Nyström", "erkki.nystrom@gmail.com", "Erik", "210.00", "Ei", "Kyllä", "Ei", "2025-06-01", "2025-06-07", "3654 8032 0034 3856", "01/28", "985", "4", "0401234567"},
                    {"Heikki Jokinen", "heikki.jokinen@gmail.com", "Pilvi", "275.00", "Kyllä", "Ei", "Kyllä", "2025-06-03", "2025-06-07", "2255 6435 7987 7762", "05/27", "553", "2", "0509876543"},
                    {"Jussi Korhonen", "jussi.korhonen@icloud.com", "Liisa", "290.00", "Kyllä", "Kyllä", "Ei", "2025-06-01", "2025-06-10", "8785 0066 4653 6980", "06/29", "330", "3", "0447654321"}
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

                Varaus varaus = new Varaus(
                        nimi,
                        sahkoposti,
                        puhelin,
                        henkiloLkm,
                        mokki,
                        lisaSanky,
                        siivous,
                        myohainenLahto,
                        summa,
                        korttiNumero,
                        voimassaoloaika,
                        turvakoodi,
                        saapumispvm,
                        lahtopvm
                );

                //addVarausToDatabase(varaus);
            }

            loadVarausFromDatabase();
        }
    }
}
