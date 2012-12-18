package toistoharjoittelu.sovelluslogiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import toistoharjoittelu.logiikka.Sanalista;
import toistoharjoittelu.logiikka.Sanapari;

public class Sovellus {

    private Sanalista sanalista;
    private Sanalista vaarinArvatut;
    private File tiedosto;
    private Scanner lukija;

    public Sovellus() {
        this.vaarinArvatut = new Sanalista();
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
        System.out.println("Anna väärin arvattujen sanojen toistoväli:");
        int toistovali = Integer.parseInt(lukija.nextLine());
        System.out.println("Jos haluat lopettaa, jätä tyhjä rivi.");
        System.out.println("Kirjoita seuraavien sanojen parit:\n");
        this.sanalista.sekoita();
        boolean jatkuu = true;
        int kerta = 1;
        while (jatkuu) {
            Sanapari pari = haeSanapari(toistovali, kerta);
            if (kerta == toistovali) kerta = 0;
            else kerta++;
            if (pari == null) break;
            jatkuu = kysele(pari);
        }
        System.out.println("\nTervetuloa harjoittelemaan uudelleen!");
    }
    
    public Sanapari haeSanapari(int toistovali, int kerta) {
        if (this.sanalista.onTyhja()) {
            if (this.vaarinArvatut.onTyhja()) {
                return null;
            }
            return this.vaarinArvatut.annaSanapari();
        }
        if (toistovali == kerta) {
            if (this.vaarinArvatut.onTyhja()) {
                return this.sanalista.annaSanapari();
            }
            return this.vaarinArvatut.annaSanapari();
        }
        return this.sanalista.annaSanapari();
    }

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

    public boolean tarkasta(Sanapari pari, String syote) {
        if (syote.equals(pari.getSana2())) {
            return true;
        } else {
            vaarinArvatut.lisaaSanapari(pari);
            return false;
        }
    }
}
