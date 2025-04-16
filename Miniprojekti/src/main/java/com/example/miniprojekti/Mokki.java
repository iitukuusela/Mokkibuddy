package com.example.miniprojekti;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mokki {
    private final StringProperty henkiloMaara;
    private final StringProperty etaisyys;
    private final StringProperty sauna;
    private final StringProperty poreamme;
    private final StringProperty hintaPerYo;

    public Mokki(String vuokraushinta, String henkiloMaara, String sijainti, String text, String priceFieldText) {
        this.henkiloMaara = new SimpleStringProperty(henkiloMaara);
        this.etaisyys = new SimpleStringProperty(sijainti);
        this.sauna = new SimpleStringProperty(sauna);
        this.poreamme = new SimpleStringProperty(poreamme);
        this.hintaPerYo = new SimpleStringProperty(hintaPerYo);
    }

    public String getHenkiloMaara() {
        return henkiloMaara.get();
    }

    public StringProperty henkiloMaaraProperty() {
        return henkiloMaara;
    }

    public String getEtaisyys() {
        return etaisyys.get();
    }

    public StringProperty etaisyysProperty() {
        return etaisyys;
    }

    public String getSauna() {
        return sauna.get();
    }

    public StringProperty saunaProperty() {
        return sauna;
    }

    public String getPoreamme() {
        return poreamme.get();
    }

    public StringProperty poreammeProperty() {
        return poreamme;
    }

    public String getHintaPerYo() {
        return hintaPerYo.get();
    }

    public StringProperty hintaPerYoProperty() {
        return hintaPerYo;
    }
}
