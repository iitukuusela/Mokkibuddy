package com.example.miniprojekti;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Varaus {

    private IntegerProperty id;
    private StringProperty nimi;
    private StringProperty sahkoposti;
    private StringProperty puhelin;
    private IntegerProperty henkiloLkm;
    private IntegerProperty mokkiId;
    private BooleanProperty lisaSanky;
    private BooleanProperty siivous;
    private BooleanProperty myohainenUloskirjautuminen;
    private DoubleProperty summa;
    private StringProperty korttiNumero;
    private StringProperty voimassaoloaika;
    private StringProperty turvakoodi;
    private StringProperty saapumispvm;
    private StringProperty lahtopvm;

    // Konstruktori ilman ID:tä
    public Varaus(int id, String nimi, String sahkoposti, String puhelin, int henkiloLkm, int mokkiId, boolean lisaSanky, boolean siivous, boolean myohainenUloskirjautuminen, double summa, String korttiNumero, String voimassaoloaika, String turvakoodi, LocalDate saapumispvm, LocalDate lahtopvm) {
        this.id = new SimpleIntegerProperty(0);
        this.nimi = new SimpleStringProperty(nimi);
        this.sahkoposti = new SimpleStringProperty(sahkoposti);
        this.puhelin = new SimpleStringProperty(puhelin);
        this.henkiloLkm = new SimpleIntegerProperty(henkiloLkm);
        this.mokkiId = new SimpleIntegerProperty(mokkiId);
        this.lisaSanky = new SimpleBooleanProperty(lisaSanky);
        this.siivous = new SimpleBooleanProperty(siivous);
        this.myohainenUloskirjautuminen = new SimpleBooleanProperty(myohainenUloskirjautuminen);
        this.summa = new SimpleDoubleProperty(summa);
        this.korttiNumero = new SimpleStringProperty(korttiNumero);
        this.voimassaoloaika = new SimpleStringProperty(voimassaoloaika);
        this.turvakoodi = new SimpleStringProperty(turvakoodi);
        this.saapumispvm = new SimpleStringProperty(saapumispvm.toString());
        this.lahtopvm = new SimpleStringProperty(lahtopvm.toString());

}

    public Varaus(String nimi, String sahkoposti, String puhelin, int henkiloLkm, int mokkiId, boolean lisaSanky, boolean siivous, boolean myohainenUloskirjautuminen, double summa, String korttiNumero, String voimassaoloaika, String turvakoodi, LocalDate saapumispvm, LocalDate lahtopvm) {
        this.id = new SimpleIntegerProperty(0);
        this.nimi = new SimpleStringProperty(nimi);
        this.sahkoposti = new SimpleStringProperty(sahkoposti);
        this.puhelin = new SimpleStringProperty(puhelin);
        this.henkiloLkm = new SimpleIntegerProperty(henkiloLkm);
        this.mokkiId = new SimpleIntegerProperty(mokkiId);
        this.lisaSanky = new SimpleBooleanProperty(lisaSanky);
        this.siivous = new SimpleBooleanProperty(siivous);
        this.myohainenUloskirjautuminen = new SimpleBooleanProperty(myohainenUloskirjautuminen);
        this.summa = new SimpleDoubleProperty(summa);
        this.korttiNumero = new SimpleStringProperty(korttiNumero);
        this.voimassaoloaika = new SimpleStringProperty(voimassaoloaika);
        this.turvakoodi = new SimpleStringProperty(turvakoodi);
        this.saapumispvm = new SimpleStringProperty(saapumispvm.toString());
        this.lahtopvm = new SimpleStringProperty(lahtopvm.toString());

    }

public int getId() { return id.get(); }
public String getNimi() { return nimi.get(); }
public String getSahkoposti() { return sahkoposti.get(); }
public String getPuhelin() { return puhelin.get(); }
public int getHenkiloLkm() { return henkiloLkm.get(); }
public int getMokkiId() { return mokkiId.get(); }
public boolean isLisaSanky() { return lisaSanky.get(); }
public boolean isSiivous() { return siivous.get(); }
public boolean isMyohainenUloskirjautuminen() { return myohainenUloskirjautuminen.get(); }
public double getSumma() { return summa.get(); }
public String getKorttiNumero() { return korttiNumero.get(); }
public String getVoimassaoloaika() { return voimassaoloaika.get(); }
public String getTurvakoodi() { return turvakoodi.get(); }
public LocalDate getSaapumispvm() { return LocalDate.parse(saapumispvm.get()); }
public LocalDate getLahtopvm() { return LocalDate.parse(lahtopvm.get()); }

public void setId(int id) { this.id.set(id); }
public void setNimi(String nimi) { this.nimi.set(nimi); }
public void setSahkoposti(String sahkoposti) { this.sahkoposti.set(sahkoposti); }
public void setPuhelin(String puhelin) { this.puhelin.set(puhelin); }
public void getHenkiloLkm(int henkiloLkm) { this.henkiloLkm.set(henkiloLkm); }
public void setMokkiId(int mokkiId) { this.mokkiId.set(mokkiId); }
public void setLisaSanky(boolean lisaSanky) { this.lisaSanky.set(lisaSanky); }
public void setSiivous(boolean siivous) { this.siivous.set(siivous); }
public void isMyohainenUloskirjautuminen(boolean myohainenUloskirjautuminen) { this.myohainenUloskirjautuminen.set(myohainenUloskirjautuminen); }
public void setSumma(double summa) { this.summa.set(summa); }
public void getKorttiNumero(String korttiNumero) { this.korttiNumero.set(korttiNumero); }
public void getVoimassaoloaika(String voimassaoloaika) { this.voimassaoloaika.set(voimassaoloaika); }
public void getTurvakoodi(String turvakoodi) { this.turvakoodi.set(turvakoodi); }
public void getSaapumispvm(LocalDate saapumispvm) { this.saapumispvm.set(String.valueOf(saapumispvm)); }
public void setLahtopvm(LocalDate lahtopvm) { this.lahtopvm.set(String.valueOf(lahtopvm)); }

public IntegerProperty idProperty() { return id; }
public StringProperty nimiProperty() { return nimi; }
public StringProperty sahkopostiProperty() { return sahkoposti; }
public StringProperty puhelinProperty() { return puhelin; }
public IntegerProperty henkiloLkmProperty() { return henkiloLkm; }
public IntegerProperty mokkiIdProperty() { return mokkiId; }
public BooleanProperty lisaSankyProperty() { return lisaSanky; }
public BooleanProperty siivousProperty() { return siivous; }
public DoubleProperty summaProperty() { return summa; }
public StringProperty korttiNumeroProperty() { return korttiNumero; }
public StringProperty voimassaoloaikaProperty() { return voimassaoloaika; }
public StringProperty turvakoodiProperty() { return turvakoodi; }
public StringProperty saapumispvmProperty() { return saapumispvm; }
public StringProperty lahtopvmProperty() { return lahtopvm; }}