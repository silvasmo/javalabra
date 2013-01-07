
package toistoharjoittelu;

import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import sovelluslogiikka.TekstiSovellus;

/**
 * 
 * @author Silva Smolander
 */
public class Toistoharjoittelu {

    public static void main(String[] args) {
        // new TekstiSovellus().suorita();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
         
              
    }
}
