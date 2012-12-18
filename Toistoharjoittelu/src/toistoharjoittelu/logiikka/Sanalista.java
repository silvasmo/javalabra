
package toistoharjoittelu.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sanalista {
    private ArrayList<Sanapari> sanalista;
    
    public Sanalista(File tiedosto) throws FileNotFoundException {
        Scanner lukija = new Scanner(tiedosto);
        this.sanalista = new ArrayList<Sanapari>();
        
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            String[] sanat = rivi.split(",");
            this.sanalista.add(new Sanapari(sanat[0], sanat[1]));
        }
        
    }
    
    public Sanalista() {
        sanalista = new ArrayList<Sanapari>();
    }
    
    public Sanapari annaSanapari() {
        Sanapari pari = this.sanalista.get(0);
        poistaListalta(pari);
        return pari;
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
        if (pari == null) {
            return;
        }
        this.sanalista.add(pari);
    }
    
}
