package com.example.miniprojekti;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Varaus {

    private IntegerProperty id;
    private StringProperty nimi;
    private StringProperty sahkoposti;
    private StringProperty puhelin;
    private IntegerProperty henkiloLkm;
    private StringProperty mokki;
    private BooleanProperty lisaSanky;
    private BooleanProperty siivous;
    private BooleanProperty myohainenUloskirjautuminen;
    private DoubleProperty summa;
    private StringProperty korttiNumero;
    private StringProperty voimassaoloaika;
    private StringProperty turvakoodi;
    private ObjectProperty<LocalDate> saapumispvm;
    private ObjectProperty<LocalDate> lahtopvm;

    // Konstruktori, jossa käytetään LocalDate-muotoisia päivämääriä
    public Varaus(int id, String nimi, String sahkoposti, String puhelin, int henkiloLkm, String mokki, boolean lisaSanky, boolean siivous, boolean myohainenUloskirjautuminen, double summa, String korttiNumero, String voimassaoloaika, String turvakoodi, LocalDate saapumispvm, LocalDate lahtopvm) {
        this.id = new SimpleIntegerProperty(0);
        this.nimi = new SimpleStringProperty(nimi);
        this.sahkoposti = new SimpleStringProperty(sahkoposti);
        this.puhelin = new SimpleStringProperty(puhelin);
        this.henkiloLkm = new SimpleIntegerProperty(henkiloLkm);
        this.mokki = new SimpleStringProperty(mokki);  // Käytetään StringPropertyt'ä
        this.lisaSanky = new SimpleBooleanProperty(lisaSanky);
        this.siivous = new SimpleBooleanProperty(siivous);
        this.myohainenUloskirjautuminen = new SimpleBooleanProperty(myohainenUloskirjautuminen);
        this.summa = new SimpleDoubleProperty(summa);
        this.korttiNumero = new SimpleStringProperty(korttiNumero);
        this.voimassaoloaika = new SimpleStringProperty(voimassaoloaika);
        this.turvakoodi = new SimpleStringProperty(turvakoodi);
        this.saapumispvm = new SimpleObjectProperty<>(saapumispvm);  // Saapumispäivämäärä
        this.lahtopvm = new SimpleObjectProperty<>(lahtopvm);  // **Muokattu: LocalDate**
    }

    public Varaus(String nimi, String sahkoposti, String puhelin, int henkiloLkm, String mokki,
                  boolean lisaSanky, boolean siivous, boolean myohainenUloskirjautuminen, boolean cleaning,
                  double summa, String korttiNumero, String voimassaoloaika, String turvakoodi,
                  LocalDate saapumispvm, LocalDate lahtopvm) {
        this.id = new SimpleIntegerProperty(0);
        this.nimi = new SimpleStringProperty(nimi);
        this.sahkoposti = new SimpleStringProperty(sahkoposti);
        this.puhelin = new SimpleStringProperty(puhelin);
        this.henkiloLkm = new SimpleIntegerProperty(henkiloLkm);
        this.mokki = new SimpleStringProperty(mokki);
        this.lisaSanky = new SimpleBooleanProperty(lisaSanky);
        this.siivous = new SimpleBooleanProperty(siivous);
        this.myohainenUloskirjautuminen = new SimpleBooleanProperty(myohainenUloskirjautuminen);
        this.summa = new SimpleDoubleProperty(summa);
        this.korttiNumero = new SimpleStringProperty(korttiNumero);
        this.voimassaoloaika = new SimpleStringProperty(voimassaoloaika);
        this.turvakoodi = new SimpleStringProperty(turvakoodi);
        this.saapumispvm = new SimpleObjectProperty<>(LocalDate.parse(saapumispvm.toString()));
        this.lahtopvm = new SimpleObjectProperty<>(LocalDate.parse(lahtopvm.toString()));
    }

    public int getId() { return id.get(); }
    public String getNimi() { return nimi.get(); }
    public String getSahkoposti() { return sahkoposti.get(); }
    public String getPuhelin() { return puhelin.get(); }
    public int getHenkiloLkm() { return henkiloLkm.get(); }
    public String getMokki() { return mokki.get(); }
    public boolean isLisaSanky() { return lisaSanky.get(); }
    public boolean isSiivous() { return siivous.get(); }
    public boolean isMyohainenUloskirjautuminen() { return myohainenUloskirjautuminen.get(); }
    public double getSumma() { return summa.get(); }
    public String getKorttiNumero() { return korttiNumero.get(); }
    public String getVoimassaoloaika() { return voimassaoloaika.get(); }
    public String getTurvakoodi() { return turvakoodi.get(); }
    public LocalDate getSaapumispvm() { return saapumispvm.get(); }
    public LocalDate getLahtopvm() { return lahtopvm.get(); }

    public void setId(int id) { this.id.set(id); }
    public void setNimi(String nimi) { this.nimi.set(nimi); }
    public void setSahkoposti(String sahkoposti) { this.sahkoposti.set(sahkoposti); }
    public void setPuhelin(String puhelin) { this.puhelin.set(puhelin); }
    public void setHenkiloLkm(int henkiloLkm) { this.henkiloLkm.set(henkiloLkm); }
    public void setMokki(String mokki) { this.mokki.set(mokki); }
    public void setLisaSanky(boolean lisaSanky) { this.lisaSanky.set(lisaSanky); }
    public void setSiivous(boolean siivous) { this.siivous.set(siivous); }
    public void setMyohainenUloskirjautuminen(boolean myohainenUloskirjautuminen) { this.myohainenUloskirjautuminen.set(myohainenUloskirjautuminen); }
    public void setSumma(double summa) { this.summa.set(summa); }
    public void setKorttiNumero(String korttiNumero) { this.korttiNumero.set(korttiNumero); }
    public void setVoimassaoloaika(String voimassaoloaika) { this.voimassaoloaika.set(voimassaoloaika); }
    public void setTurvakoodi(String turvakoodi) { this.turvakoodi.set(turvakoodi); }
    public void setSaapumispvm(LocalDate saapumispvm) { this.saapumispvm.set(saapumispvm); }
    public void setLahtopvm(LocalDate lahtopvm) { this.lahtopvm.set(lahtopvm); }

    public IntegerProperty idProperty() { return id; }
    public StringProperty nimiProperty() { return nimi; }
    public StringProperty sahkopostiProperty() { return sahkoposti; }
    public StringProperty puhelinProperty() { return puhelin; }
    public IntegerProperty henkiloLkmProperty() { return henkiloLkm; }
    public StringProperty mokkiProperty() { return mokki; }
    public BooleanProperty lisaSankyProperty() { return lisaSanky; }
    public BooleanProperty siivousProperty() { return siivous; }
    public BooleanProperty myohainenUloskirjautuminenProperty() { return myohainenUloskirjautuminen; }
    public DoubleProperty summaProperty() { return summa; }
    public StringProperty korttiNumeroProperty() { return korttiNumero; }
    public StringProperty voimassaoloaikaProperty() { return voimassaoloaika; }
    public StringProperty turvakoodiProperty() { return turvakoodi; }
    public ObjectProperty saapumispvmProperty() { return saapumispvm; }
    public ObjectProperty lahtopvmProperty() { return lahtopvm; }
}
