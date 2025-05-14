package com.example.miniprojekti;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Yhteysluokka {
    private Connection yhteys;
    final static String url = "jdbc:mysql://localhost:3306/mokkidb";
    final static String user = "java_user";
    final static String password = "HirttoKoysi150!";

    //alustaja, joka yrittää luo yhteyden
    public Yhteysluokka() {
        try {
            yhteys = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //getteri jolla saadaan yhteys
    public  Connection getYhteys() {
        return yhteys;
    }

    public static void main(String[] args) {

    }
}
