package com.example.miniprojekti;

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
    private LocalDate lahtopaivamaara;

    // Konstruktori ilman ID:tä
    public Varaus(String nimi, String sahkoposti, String puhelin, int henkiloLkm, int mokkiId, boolean lisaSanky, boolean siivous, boolean myohainenUloskirjautuminen, double summa, String korttiNumero, String voimassaoloaika, String turvakoodi, LocalDate saapumispvm, LocalDate lahtopaivamaara) {
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
        this.lahtopaivamaara = lahtopaivamaara;

}
