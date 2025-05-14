package com.example.miniprojekti;

import com.calendarfx.model.Entry;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import com.calendarfx.view.CalendarView;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Calendar.Style;
import java.time.LocalDate;
import java.time.LocalTime;


public class Yleiskalenteri extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(createScene(primaryStage));
        primaryStage.setTitle("Yleiskalenteri");
        primaryStage.show();
    }

    public Scene createScene(Stage primaryStage) {

        CalendarView calendarView = new CalendarView(); //luo alenterin näkymän

        //Paluu etusivulle -painike
        Button btPaluu = new Button("Palaa etusivulle");
         btPaluu.setOnAction(e -> {
         primaryStage.setScene(new Main().createScene(primaryStage));
         });

        //hbox yläriville, jotta saadaan paluunappi sijoitettua, vaikka calendarFX on suht suljettu näkymä
        HBox ylarivi = new HBox(btPaluu);
        ylarivi.setPadding(new Insets(10));
        ylarivi.setAlignment(Pos.CENTER_RIGHT);
        ylarivi.setStyle("-fx-background-color: #f0f0f0;");

        //borderpane, jotta saadaan nappi lisättyä kalenterinäkymään
        BorderPane root = new BorderPane();
        root.setTop(ylarivi);
        root.setCenter(calendarView);

        //mökkien kalenterit
        Calendar yleinen = new Calendar("yleinen");
        Calendar helmi = new Calendar("Villa Helmi");
        Calendar liisa = new Calendar<>("Villa Liisa");
        Calendar pisara = new Calendar<>("Villa Pisara");
        Calendar erik = new Calendar<>("Villa Erik");
        Calendar esko = new Calendar<>("Villa Esko");
        Calendar heino = new Calendar<>("Villa Heino");
        Calendar jukka = new Calendar<>("Villa Jukka");
        Calendar aurinko = new Calendar<>("Villa Aurinko");
        Calendar pilvi = new Calendar<>("Villa Pilvi");
        Calendar kumpu = new Calendar<>("Villa Kumpu");

        yleinen.setStyle(Style.STYLE1);
        helmi.setStyle(Style.STYLE1);
        liisa.setStyle(Style.STYLE2);
        pisara.setStyle(Style.STYLE3);
        erik.setStyle(Style.STYLE4);
        //esko.setStyle(Style.STYLE5);
        heino.setStyle(Style.STYLE6);
        jukka.setStyle(Style.STYLE7);
        //aurinko.setStyle(Style.STYLE2);  //tässä loppu tyyli kesken nii luovutaan kolmesta viimesestä mökistä
        pilvi.setStyle(Style.STYLE5);
        //kumpu.setStyle(Style.STYLE4);

        CalendarSource myCalendarSource = new CalendarSource("Kalenterit");
        myCalendarSource.getCalendars().addAll(helmi, liisa, pisara, erik, pilvi, heino, jukka);

        //testimerkintä kalenteriin eli entry
        Entry varaus = new Entry<>("Varaus: Nyström, 2hlö");
        varaus.setInterval(LocalDate.of(2025, 8, 6), LocalDate.of(2025, 8, 16));
        erik.addEntries(varaus);

        Entry varaus2 = new Entry<>("Varaus: Jokinen, 5hlö");
        pilvi.addEntries(varaus2);

        Entry varaus3 = new Entry<>("Varaus: Korhonen, 15hlö");
        liisa.addEntries(varaus3);

        calendarView.getCalendarSources().addAll(myCalendarSource);

        calendarView.setRequestedTime(LocalTime.now());

        //säie, jolla päivitetään kalenteria
        Thread updateTimeThread = new Thread("Kalenteri: päivitä kalenteri -thread") {

            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        };


        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();

        Scene scene = new Scene(root, 1000, 700);
        return scene;


    }
}
