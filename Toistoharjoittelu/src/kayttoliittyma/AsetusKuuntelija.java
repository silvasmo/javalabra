package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sovelluslogiikka.Sovellus;

/**
 * Käyttöliittymäluokka, kuuntelija asetukset-napille
 * @author Silva Smolander
 */
public class AsetusKuuntelija implements ActionListener {

    /**
     * Käyttöliittymän frame
     */
    private JFrame frame;
    
    /**
     * Sovelluslogiikan sisältävä olio
     */
    private Sovellus sovellus;
    
    /**
     * Kayttoliittyma-luokan ilmetymä
     */
    private Kayttoliittyma kayttoliittyma;

    /**
     * Luo kuuntelijan asetusnapille
     * @param frame
     * @param sovellus 
     */
    public AsetusKuuntelija(JFrame frame, Kayttoliittyma kayttoliittyma) {
        this.frame = frame;
        this.kayttoliittyma = kayttoliittyma;
        this.sovellus = kayttoliittyma.getSovellus();
    }

    /**
     * Kysyy toistoväliä käyttäjältä.
     * Päivittää toistovälin sovellukseen.
     * Ei tee mitään jos syöte käyttäjältä ei ole kokonaisluku.
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object[] possibilities = null;
        this.sovellus = kayttoliittyma.getSovellus();
        int toistovali = sovellus.getToistovali();
        String vastaus = (String) JOptionPane.showInputDialog(
                frame,
                "Kuinka monen sanan välein haluat\n"
                + "väärin arvattujen sanojen toistuvan",
                "Asetukset",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "" + toistovali);
        try {
            toistovali = Integer.parseInt(vastaus);
            sovellus.setToistovali(toistovali);
        } catch (NumberFormatException ex) {
            return;
        }
    }
}
