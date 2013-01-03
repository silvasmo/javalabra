package logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sanalista {

    private ArrayList<Sanapari> sanalista;
    private Sanapari nykyinenPari;

    public Sanalista(File tiedosto) throws FileNotFoundException {
        Scanner lukija = new Scanner(tiedosto);
        this.sanalista = new ArrayList<Sanapari>();

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            String[] sanat = rivi.split(",");
            this.sanalista.add(new Sanapari(sanat[0], sanat[1]));
        }
        this.nykyinenPari = this.sanalista.get(0);

    }

    public Sanalista() {
        sanalista = new ArrayList<Sanapari>();
    }

    public Sanapari annaSanapari() {
        return this.nykyinenPari;
    }

    public void seuraavaPari() {
        poistaListalta(this.nykyinenPari);
        if (!this.onTyhja()) {
            this.nykyinenPari = this.sanalista.get(0);
        } else {
            this.nykyinenPari = new Sanapari("", "");
        }
    }

    public void sekoita() {
        Collections.shuffle(sanalista);
    }

    public void poistaListalta(Sanapari sanapari) {
        this.sanalista.remove(sanapari);
    }

    public ArrayList<Sanapari> annaLista() {
        return this.sanalista;
    }

    public boolean onTyhja() {
        if (this.sanalista.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void lisaaSanapari(Sanapari pari) {
        lisaaSanapari(this.annaLista().size(), pari);
    }
    
    public void lisaaSanapari(int paikka, Sanapari pari) {
        if (pari == null) {
            return;
        }
        if (paikka < this.annaLista().size()) {
            this.sanalista.add(paikka, pari);
        } else {
            this.sanalista.add(pari);
        }
        if (this.nykyinenPari == null) {
            this.nykyinenPari = pari;
        }
    }
    
}
