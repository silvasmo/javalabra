package sovelluslogiikka;

import java.io.File;
import java.io.FileNotFoundException;
import logiikka.Sanalista;
import logiikka.Sanapari;

/**
 * Luokka sovelluksen toteuttamiseen graafisen käyttöliittymän avuksi
 * @author Silva Smolander
 */
public class Sovellus {

    /**
     * Sanalista-olio, joka sisältää harjoiteltavat sanaparit
     */
    private Sanalista sanalista;

    /**
     * Väli, jolla väärin arvattuja sanoja kysytään uudelleen
     */
    private int toistovali;
    
    /**
     * Käyttäjän pisteet, tiedettyjen sanojen määrä
     */
    private int pisteet;
    
    /**
     * Sanalistan koko eli kyseltävien sanojen yhteismäärä
     */
    private int koko;

    /**
     * Luo sovelluksen "paakaupunkeja.txt"-tiedoston harjoittelemiseen
     */
    public Sovellus() {
        this("paakaupunkeja.txt");
    }

    /**
     * Luo sovelluksen parametrina annattavan merkkijonon
     * nimisen tiedoston hajoitteluun.
     * @param tiedostonNimi Harjoiteltavan tiedoston nimi
     */
    public Sovellus(String tiedostonNimi) {
        try {
            this.sanalista = new Sanalista(new File(tiedostonNimi));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedoston lukeminen epäonnistui. Virhe: " + ex.getMessage());
            return;
        }
        this.toistovali = 3;
        this.pisteet = 0;
        this.koko = this.sanalista.annaLista().size();
    }

    /**
     * Tarkastaa vuorossa olevan sanaparin ja
     * parametrinä annatun sanan samuuden.
     * Vaihtaa sanalistassa vuorossa olevan parin seuraavaan.
     * Palauttaa true jos oikein, false jos väärin.
     * @param sana2 Tarkastettava sana
     * @return Totuusarvo
     */
    public boolean tarkasta(String sana2) {
        Sanapari pari = sanalista.annaSanapari();
        sanalista.seuraavaPari();
        return tarkastaSana(pari, sana2);
    }

    /**
     * Tarkastaa parametrina olevan merkkijonon ja sanaparin toisen sanan samuuden.
     * Ei ota huomioon ylimääräisiä välilyöntejä ja isoja kirjaimia.
     * Päivittää pisteet, lisää sanaparin oikeaan kohtaan listaa jos vastaus väärin.
     * Palauttaa true jos oikein, false jos väärin.
     * @param pari Vuorossa oleva sanapari
     * @param sana2 Arvattu sana
     * @return Totuusarvo
     */
    public boolean tarkastaSana(Sanapari pari, String sana2) { 
        if (sana2.trim().equalsIgnoreCase(pari.getSana2().trim())) {
            paivitaPisteet(true);
            return true;
        }
        this.sanalista.lisaaSanapari(toistovali, pari);
        paivitaPisteet(false);
        if (sanalista.annaSanapari().getSana1().equals("")) {
            sanalista.seuraavaPari();
        }
        return false;
    }

    /**
     * Palauttaa sanalistassa vuorossa olevan parin.
     * Jos sanalista on tyhjä, palauttaa tyhjän sanaparin.
     * @return Vuorossa oleva sanapari
     */
    public Sanapari annaSanapari() {
        if (this.sanalista.onTyhja()) {
            return new Sanapari("", "");
        }
        return this.sanalista.annaSanapari();
    }

    /**
     * Asettaa toistovali-muuttujaan parametrin arvon.
     * Ei tee mitään jos arvo on pienempi tai yhtä suuri kuin 0.
     * @param vali Uusi toistovali
     */
    public void setToistovali(int vali) {
        if (vali > 0) {
            this.toistovali = vali;
        }
    }

    /**
     * Palauttaa toistovali-muuttujan.
     * @return toistovali
     */
    public int getToistovali() {
        return this.toistovali;
    }

    /**
     * Palauttaa pisteet muuttujan merkkijonona muodossa:
     * "pisteitä: pisteet/sanojen määrä"
     * @return pisteet Käyttäjän pisteet
     */
    public String annaPisteet() {
        return "pisteitä: " + this.pisteet + "/" + this.koko;
    }

    /**
     * Päivittää pisteet-muuttujan arvoa.
     * Jos parametri oikein true, lisää pisteitä yhdellä
     * @param oikein Totuusarvo
     */
    public void paivitaPisteet(boolean oikein) {
        if (oikein) {
            pisteet++;
        }
    }
}
