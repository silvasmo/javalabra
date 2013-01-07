package logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Luokka sanapareista koostuvan listan tallentamiseen
 * @author Silva Smolander
 */
public class Sanalista {

    private ArrayList<Sanapari> sanalista;
    private Sanapari nykyinenPari;

    /**
     * Luo sanalistan, jonka sisältö luetaan parametrinä
     * tulevasta tiedostosta.
     * @param tiedosto
     * @throws FileNotFoundException 
     */
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

    /**
     * Luo tyhjän sanalista
     */
    public Sanalista() {
        sanalista = new ArrayList<Sanapari>();
    }

    /**
     * Palauttaa ensimmäisenä vuorossa olevan sanaparin
     * @return nykyinen sanapari
     */
    public Sanapari annaSanapari() {
        return this.nykyinenPari;
    }

    /**
     * Vaihtaa vuorossa olevan sanaparin seuraavaan
     * ja poistaa nykyisen parin listalta.
     * Listan ollessa tyhjä luo uuden tyhjän sanaparin
     * ja asettaa sen seuraavaksi.
     */
    public void seuraavaPari() {
        poistaListalta(this.nykyinenPari);
        if (!this.onTyhja()) {
            this.nykyinenPari = this.sanalista.get(0);
        } else {
            this.nykyinenPari = new Sanapari("", "");
        }
    }

    /**
     * Sekoittaa sanalista järjestyksen.
     */
    public void sekoita() {
        Collections.shuffle(sanalista);
    }

    /**
     * Poistaa parametrina annetun sanaparin listalta.
     * @param sanapari Poistettava sanapari
     */
    public void poistaListalta(Sanapari sanapari) {
        this.sanalista.remove(sanapari);
    }

    /**
     * Palauttaa sanalistan ArrayList-muodossa
     * @return tallennettu sanalista
     */
    public ArrayList<Sanapari> annaLista() {
        return this.sanalista;
    }

    /**
     * Palauttaa true jos sanalistassa ei ole alkioita,
     * muuten false.
     * @return tyhjyyden totuusarvo
     */
    public boolean onTyhja() {
        if (this.sanalista.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Lisää sanaparin sanalista loppuun
     * @param pari Lisättävä sanapari
     */
    public void lisaaSanapari(Sanapari pari) {
        lisaaSanapari(this.annaLista().size(), pari);
    }
    
    /**
     * Lisää sanaparin parametrissä paikka määriteltyyn kohtaan sanalistassa
     * @param paikka Indeksi listassa johon sanapari lisätään
     * @param pari Lisättävä sanapari
     */
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
