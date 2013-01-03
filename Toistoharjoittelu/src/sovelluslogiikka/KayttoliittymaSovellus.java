package sovelluslogiikka;

import java.io.File;
import java.io.FileNotFoundException;
import logiikka.Sanalista;
import logiikka.Sanapari;

public class KayttoliittymaSovellus {

    private Sanalista sanalista;
    private File tiedosto;
    private int toistovali;

    public KayttoliittymaSovellus() {
        this("paakaupunkeja.txt");
    }
    
    public KayttoliittymaSovellus(String tiedostonNimi) {
        this.tiedosto = new File(tiedostonNimi);
        try {
            this.sanalista = new Sanalista(tiedosto);
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedoston lukeminen epäonnistui. Virhe: " + ex.getMessage());
            return;
        }
        this.sanalista.sekoita();
        this.toistovali = 3;
    }

    public boolean tarkasta(String sana2) {
        Sanapari pari = sanalista.annaSanapari();
        sanalista.seuraavaPari();
        return tarkastaSana(pari, sana2);
    }


    public boolean tarkastaSana(Sanapari pari, String sana2) {
        if (sana2.equalsIgnoreCase(pari.getSana2())) {
            return true;
        }
        this.sanalista.lisaaSanapari(toistovali, pari);
        return false;
    }
    

    public Sanapari annaSanapari() {
        if (this.sanalista.onTyhja()) {
            return new Sanapari("", "");
        }
        return this.sanalista.annaSanapari();
    }

    public void setToistovali(int vali) {
        this.toistovali = vali;
    }
    
    
}
