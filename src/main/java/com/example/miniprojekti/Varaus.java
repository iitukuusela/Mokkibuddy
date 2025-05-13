package com.example.miniprojekti;

import java.time.LocalDate;

public class Varaus {

    private int id;
    private String nimi;
    private String sahkoposti;
    private String puhelin;
    private int henkiloLkm;
    private int mokkiId;
    private boolean lisaSanky;
    private boolean siivous;
    private boolean myohainenUloskirjautuminen;
    private double summa;
    private String korttiNumero;
    private String voimassaoloaika;
    private String turvakoodi;
    private LocalDate saapumispvm;
    private LocalDate lahtopvm;

    // Konstruktori ilman ID:tä
    public Varaus(String nimi, String sahkoposti, String puhelin, int henkiloLkm, int mokkiId, boolean lisaSanky, boolean siivous, boolean myohainenUloskirjautuminen, double summa, String korttiNumero, String voimassaoloaika, String turvakoodi, LocalDate saapumispvm, LocalDate lahtopvm) {
        this.nimi = nimi;
        this.sahkoposti = sahkoposti;
        this.puhelin = puhelin;
        this.henkiloLkm = henkiloLkm;
        this.mokkiId = mokkiId;
        this.lisaSanky = lisaSanky;
        this.siivous = siivous;
        this.myohainenUloskirjautuminen = myohainenUloskirjautuminen;
        this.summa = summa;
        this.korttiNumero = korttiNumero;
        this.voimassaoloaika = voimassaoloaika;
        this.turvakoodi = turvakoodi;
        this.saapumispvm = saapumispvm;
        this.lahtopvm = lahtopvm;

}

public String getNimi() { return nimi; }
public String getSahkoposti() { return sahkoposti; }
public String getPuhelin() { return puhelin; }
public int getHenkiloLkm() { return henkiloLkm; }
public int getMokkiId() { return mokkiId; }
public boolean isLisaSanky() { return lisaSanky; }
public boolean isSiivous() { return siivous; }
public boolean isMyohainenUloskirjautuminen() { return myohainenUloskirjautuminen; }
public double getSumma() { return summa; }
public String getKorttiNumero() { return korttiNumero; }
public String getVoimassaoloaika() { return voimassaoloaika; }
public String getTurvakoodi() { return turvakoodi; }
public LocalDate getSaapumispvm() { return saapumispvm; }
public LocalDate getLahtopvm() { return lahtopvm; }

public void setNimi(String nimi) { this.nimi = nimi; }
public void setSahkoposti(String sahkoposti) { this.sahkoposti = sahkoposti; }
public void setPuhelin(String puhelin) { this.puhelin = puhelin; }
public void getHenkiloLkm(int henkiloLkm) { this.henkiloLkm = henkiloLkm; }
public void setMokkiId(int mokkiId) { this.mokkiId = mokkiId; }
public void setLisaSanky(boolean lisaSanky) { this.lisaSanky = lisaSanky; }
public void setSiivous(boolean siivous) { this.siivous = siivous; }
public void isMyohainenUloskirjautuminen(boolean myohainenUloskirjautuminen) { this.myohainenUloskirjautuminen = myohainenUloskirjautuminen; }
public void setSumma(double summa) { this.summa = summa; }
public void getKorttiNumero(String korttiNumero) { this.korttiNumero = korttiNumero; }
public void getVoimassaoloaika(String voimassaoloaika) { this.voimassaoloaika = voimassaoloaika; }
public void getTurvakoodi(String turvakoodi) { this.turvakoodi = turvakoodi; }
public void getSaapumispvm(LocalDate saapumispvm) { this.saapumispvm = saapumispvm; }
public void setLahtopvm(LocalDate lahtopvm) { this.lahtopvm = lahtopvm; }