
package logiikka;

/**
 * Luokka sanaparien tallentamiseen
 * @author Silva Smolander
 */
public class Sanapari {
    private String sana1;
    private String sana2;
    
    /**
     * Luo Sanaparin, jossa on parametreina annetut merkkijonot.
     * @param sana1
     * @param sana2 
     */
    public Sanapari(String sana1, String sana2) {
        this.sana1 = sana1;
        this.sana2 = sana2;
    }
    
    /**
     * Metodi palauttaa ensimmäisen sanaparin sanoista
     * @return ensimmäinen sana
     */
    public String getSana1() {
        return this.sana1;
    }
    
    /**
     * Metodi palauttaa toisen sanaparin sanoista
     * @return toinen sana
     */
    public String getSana2() {
        return this.sana2;
    }
    
}
