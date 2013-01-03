
package toistoharjoittelu;

import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import sovelluslogiikka.Sovellus;


public class Toistoharjoittelu {

    public static void main(String[] args) {
        // new Sovellus().suorita();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
         
              
    }
}
