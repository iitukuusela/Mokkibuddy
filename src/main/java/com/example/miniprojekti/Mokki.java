package com.example.miniprojekti;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mokki {
    private final IntegerProperty id;
    private final StringProperty henkiloMaara;
    private final StringProperty etaisyys;
    private final StringProperty sauna;
    private final StringProperty poreamme;
    private final StringProperty hintaPerYo;

    public Mokki(String henkiloMaara, String sijainti, String sauna, String poreamme, String hintaPerYo) {
        this.id = new SimpleIntegerProperty(0);
        this.henkiloMaara = new SimpleStringProperty(henkiloMaara);
        this.etaisyys = new SimpleStringProperty(sijainti);
        this.sauna = new SimpleStringProperty(sauna);
        this.poreamme = new SimpleStringProperty(poreamme);
        this.hintaPerYo = new SimpleStringProperty(hintaPerYo);
    }

    public Mokki(int id, String henkiloMaara, String sijainti, String sauna, String poreamme, String hintaPerYo) {
        this.id = new SimpleIntegerProperty(id);
        this.henkiloMaara = new SimpleStringProperty(henkiloMaara);
        this.etaisyys = new SimpleStringProperty(sijainti);
        this.sauna = new SimpleStringProperty(sauna);
        this.poreamme = new SimpleStringProperty(poreamme);
        this.hintaPerYo = new SimpleStringProperty(hintaPerYo);
    }

    public int getId() { return id.get(); }

    public void setId(int id) { this.id.set(id); }

    public IntegerProperty idProperty() { return id; }

    public String getHenkiloMaara() {
        return henkiloMaara.get();
    }

    public void setHenkiloMaara(String henkiloMaara) { this.henkiloMaara.set(henkiloMaara); }

    public StringProperty henkiloMaaraProperty() {
        return henkiloMaara;
    }

    public String getEtaisyys() {
        return etaisyys.get();
    }

    public void setEtaisyys(String etaisyys) { this.etaisyys.set(etaisyys); }

    public StringProperty etaisyysProperty() {
        return etaisyys;
    }

    public String getSauna() {
        return sauna.get();
    }

    public void setSauna(String sauna) { this.sauna.set(sauna); }

    public StringProperty saunaProperty() {
        return sauna;
    }

    public String getPoreamme() {
        return poreamme.get();
    }

    public void setPoreamme(String poreamme) { this.poreamme.set(poreamme); }

    public StringProperty poreammeProperty() {
        return poreamme;
    }

    public String getHintaPerYo() {
        return hintaPerYo.get();
    }

    public void setHintaPerYo(String hintaPerYo) { this.hintaPerYo.set(hintaPerYo); }

    public StringProperty hintaPerYoProperty() {
        return hintaPerYo;
    }
}
