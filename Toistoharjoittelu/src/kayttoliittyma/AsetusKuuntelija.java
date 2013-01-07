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

    private JFrame frame;
    private Sovellus sovellus;

    /**
     * Luo kuuntelijan asetusnapille
     * @param frame
     * @param sovellus 
     */
    public AsetusKuuntelija(JFrame frame, Sovellus sovellus) {
        this.frame = frame;
        this.sovellus = sovellus;
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
