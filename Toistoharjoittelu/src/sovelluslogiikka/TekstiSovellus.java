package sovelluslogiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import logiikka.Sanalista;
import logiikka.Sanapari;

/**
 * Tekstikäyttöliittymäluokka
 * @author Silva Smolander
 */
public class TekstiSovellus {

    /**
     * Arvattavat sanaparit sisältävä tietorakenne
     */
    private Sanalista sanalista;
    
    /**
     * Tiedoston lukemiseen tarvittava Scanner-olio
     */
    private Scanner lukija;
    
    /**
     * Kuinka usein väärin arvatut sanat toistuvat
     */
    private int toistovali;

    /**
     * Mahdollistaa harjoittelun paakaupunkeja.txt tiedostolle
     */
    public TekstiSovellus() {
        this.lukija = new Scanner(System.in);
        try {
            this.sanalista = new Sanalista(new File("paakaupunkeja.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedoston lukeminen epäonnistui. Virhe: " + ex.getMessage());
            return;
        }
        this.toistovali = 3;
    }

    /**
     * Pyytää käyttäjältä syötettä ja antaa ohjeita.
     * Jatkaa suorittamista kunnes syöte on tyhjä tai sanat loppuvat.
     */
    public void suorita() {
        System.out.println("Tervetuloa harjoittelemaan toiston avulla!\n");
        System.out.println("Anna väärin arvattujen sanojen toistoväli:");
        toistovali = Integer.parseInt(lukija.nextLine());
        System.out.println("Jos haluat lopettaa, jätä tyhjä rivi.");
        System.out.println("Kirjoita seuraavien sanojen parit:\n");
        boolean jatkuu = true;
        while (jatkuu) {
            Sanapari pari = sanalista.annaSanapari();
            if (pari.getSana1().equals("")) break;
            jatkuu = kysele(pari);
        }
        System.out.println("\nTervetuloa harjoittelemaan uudelleen!");
    }


    /**
     * Kysyy käyttäjältä sanan paria.
     * Palauttaa true, jos suoritusta pitää jatkaa, false jos ei.
     * @param pari Kyseltävä pari
     * @return Suorituksen jatkuminen
     */
    public boolean kysele(Sanapari pari) {
        System.out.println(pari.getSana1());
        System.out.print("Sanan pari:  ");
        System.out.println("");
        String syote = lukija.nextLine();
        if (syote.isEmpty()) {
            return false;
        }
        boolean tulostus = tarkasta(pari, syote);
        if (tulostus) {
            System.out.println("Oikein!\n");
        } else {
            System.out.println("Väärin!\n");
        }
        return true;
    }

    /**
     * Tarkastaa vastaako syöte oikeaa vastausta.
     * Jos vastaus on väärin, lisää sanaparin oikealle kohdalle listassa.
     * @param pari Arvattava pari
     * @param syote Käyttäjän syöte
     * @return Onko syöte oikein
     */
    public boolean tarkasta(Sanapari pari, String syote) {
        sanalista.seuraavaPari();
        if (syote.equalsIgnoreCase(pari.getSana2())) {
            return true;
        } else {
            sanalista.lisaaSanapari(toistovali, pari);
            return false;
        }
    }
}
