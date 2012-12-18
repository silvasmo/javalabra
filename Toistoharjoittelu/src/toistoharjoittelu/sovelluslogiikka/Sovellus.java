
package toistoharjoittelu.sovelluslogiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import toistoharjoittelu.logiikka.Sanalista;
import toistoharjoittelu.logiikka.Sanapari;

public class Sovellus {
    Sanalista sanalista;
    File tiedosto;
    Scanner lukija;
    
    public Sovellus() {
        this.lukija = new Scanner(System.in);
        this.tiedosto = new File("paakaupunkeja.txt");
        try {
            this.sanalista = new Sanalista(tiedosto);
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedoston lukeminen epäonnistui. Virhe: " + ex.getMessage());
            return;
        }
    }
    
    public void suorita() {
        System.out.println("Tervetuloa harjoittelemaan toiston avulla!\n");
        System.out.println("Jos haluat lopettaa, jätä tyhjä rivi.");
        System.out.println("Kirjoita seuraavien sanojen parit:\n");
        this.sanalista.sekoita();
        boolean jatkuu = true;
        while (jatkuu) {
            jatkuu = kysele();
        }
        System.out.println("\nTervetuloa harjoittelemaan uudelleen!");
    }
    
    public boolean kysele() {
        if (this.sanalista.tyhja()) return false;
        Sanapari pari = this.sanalista.annaSanapari();
        System.out.println(pari.getSana1());
        System.out.print("Sanan pari:  ");
        System.out.println("");
        String syote = lukija.nextLine();
        if (syote.isEmpty()) return false;
        boolean tulostus = tarkasta(pari, syote);
        if (tulostus) {
            System.out.println("Oikein!\n");
        } else {
            System.out.println("Väärin!\n");
        }
        return true;
    }
    
    public boolean tarkasta(Sanapari pari, String syote) {
        if (syote.equals(pari.getSana2())) {
            return true;
        } else {
            return false;
        }
    }
    
}
