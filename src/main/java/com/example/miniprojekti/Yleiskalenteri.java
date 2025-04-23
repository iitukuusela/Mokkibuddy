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

        //Calendar

    }
}
