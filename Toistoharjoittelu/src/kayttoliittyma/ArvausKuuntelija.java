package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logiikka.Sanapari;
import sovelluslogiikka.Sovellus;

/**
 * Käyttöliittymäluokka, kuuntelija arvaa-napille
 * @author Silva Smolander
 */
public class ArvausKuuntelija implements ActionListener {

    private JLabel pisteet;
    private JLabel sana1;
    private JTextField sana2;
    private Sovellus sovellus;
    private JFrame frame;

    /**
     * Luo ArvausKuuntelijan
     * @param pisteet Ilmoittaa pisteet
     * @param sana1 Ilmoittaa arvattavan sanan
     * @param sana2 Käyttäjän syöte
     * @param sovellus Sovellus
     * @param frame Frame
     */
    public ArvausKuuntelija(JLabel pisteet, JLabel sana1, JTextField sana2, Sovellus sovellus, JFrame frame) {
        this.pisteet = pisteet;
        this.sana1 = sana1;
        this.sana2 = sana2;
        this.sovellus = sovellus;
        this.frame = frame;
    }

    /**
     * Tarkastaa käyttäjän vastauksen oikeellisuuden.
     * Päivittää käyttöliittymään pisteet ja seuraavan arvattavan sanan.
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        String toinenSana = sana2.getText();
        boolean oikein = sovellus.tarkasta(toinenSana);
        tuloksenIlmoitus(oikein);
        pisteet.setText(sovellus.getPisteet());
        Sanapari seuraavaPari = sovellus.annaSanapari();
        if (seuraavaPari.getSana1().equals("")) {
            harjoittelunLopetus();
            seuraavaPari = sovellus.annaSanapari();
        }
        sana1.setText(seuraavaPari.getSana1());
        sana2.setText("");

    }

    /**
     * Tekee ikkunan, jossa ilmoitetaan käyttäjälle vastauksen oikeellisuus
     * @param oikein Käyttäjän vastauksen oikeellisuus
     */
    public void tuloksenIlmoitus(boolean oikein) {
        if (oikein) {
            JOptionPane.showMessageDialog(frame, "Oikein!", "Tulos",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Väärin.", "Tulos",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void harjoittelunLopetus() {
        Object[] vaihtoehdot = {"Lopetan",
            "Aloitan alusta"};
        int vastaus = JOptionPane.showOptionDialog(frame,
                "Onneksi olkoon! Tiesit kaikki sanat.\n\n" +
                "Haluatko lopettaa harjoittelun, vai aloittaa alusta?",
                "Onneksi olkoon!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                vaihtoehdot, //the titles of buttons
                vaihtoehdot[0]); //default button title
        if (vastaus == JOptionPane.NO_OPTION) {
            valitseTiedosto();
        } else {
            return;
        }
    }
    
    public void valitseTiedosto() {
        Object[] vaihtoehdot = {"paakaupunkeja.txt"};
        String tiedosto = (String) JOptionPane.showInputDialog(
                frame,
                "Valitse tiedosto, jota \n"
                + "haluat harjoitella",
                "Tiedoston valinta",
                JOptionPane.PLAIN_MESSAGE,
                null,
                vaihtoehdot,
                "paakaupunkeja.txt");
        this.sovellus = new Sovellus(tiedosto);
    }
    
}
