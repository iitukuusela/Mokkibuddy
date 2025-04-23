package com.example.miniprojekti;

import javafx.application.Application;
import javafx.application.Platform;
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
    public void start(Stage primaryStage) throws Exception{

        CalendarView calendarView = new CalendarView(); //luo alenterin näkymän

        Calendar yleinen = new Calendar("yleinen");
        Calendar helmi = new Calendar("Villa Helmi");

        yleinen.setStyle(Style.STYLE1);
        helmi.setStyle(Style.STYLE2);

        CalendarSource myCalendarSource = new CalendarSource("Kalenterit");
        myCalendarSource.getCalendars().addAll(yleinen, helmi);

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




    }
}
