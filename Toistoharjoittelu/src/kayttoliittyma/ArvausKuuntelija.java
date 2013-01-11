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

    /**
     * Pisteet ilmoittava label
     */
    private JLabel pisteet;
    /**
     * Arvattavan sanan ilmoittava label
     */
    private JLabel sana1;
    /**
     * Tekstikenttä käyttäjän arvaukselle
     */
    private JTextField sana2;
    /**
     * Sovelluslogiikan sisältävä olio
     */
    private Sovellus sovellus;
    /**
     * Ilmoituksia varten tarvittava frame käyttöliittymästä
     */
    private JFrame frame;
    
    /**
     * Käyttöliittymaluokan olio tiedostonValinta-metodia varten
     */
    private Kayttoliittyma kayttoliittyma;

    /**
     * Luo ArvausKuuntelijan
     * @param pisteet Ilmoittaa pisteet
     * @param sana1 Ilmoittaa arvattavan sanan
     * @param sana2 Käyttäjän syöte
     * @param sovellus Sovellus
     * @param frame Frame
     */
    public ArvausKuuntelija(Kayttoliittyma kayttoliittyma, JLabel pisteet, JLabel sana1, JTextField sana2, Sovellus sovellus, JFrame frame) {
        this.pisteet = pisteet;
        this.sana1 = sana1;
        this.sana2 = sana2;
        this.sovellus = sovellus;
        this.frame = frame;
        this.kayttoliittyma = kayttoliittyma;
    }

    /**
     * Tarkastaa käyttäjän vastauksen oikeellisuuden.
     * Päivittää käyttöliittymään pisteet ja seuraavan arvattavan sanan.
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.sovellus = kayttoliittyma.getSovellus();
        Sanapari pari = sovellus.annaSanapari();
        String toinenSana = sana2.getText();
        boolean oikein = sovellus.tarkasta(toinenSana);
        tuloksenIlmoitus(oikein, pari);
        Sanapari seuraavaPari = sovellus.annaSanapari();
        if (seuraavaPari.getSana1().equals("")) {
            harjoittelunLopetus();
            seuraavaPari = sovellus.annaSanapari();
        }
        sana1.setText(seuraavaPari.getSana1());
        sana2.setText("");
        pisteet.setText(sovellus.annaPisteet());

    }

    /**
     * Tekee ikkunan, jossa ilmoitetaan käyttäjälle vastauksen oikeellisuus
     * ja jos väärin niin lisäksi myös oikea vastaus
     * @param oikein Käyttäjän vastauksen oikeellisuus
     */
    public void tuloksenIlmoitus(boolean oikein, Sanapari pari) {
        if (oikein) {
            JOptionPane.showMessageDialog(frame, "Oikein!", "Tulos",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Väärin.\nOikea vastaus olisi ollut: "
                    + pari.getSana2(), "Tulos",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Kysyy käyttäjältä haluaako hän lopettaa harjoittelun,
     * vai aloittaa ohjelman alusta.
     */
    public void harjoittelunLopetus() {
        Object[] vaihtoehdot = {"Lopetan",
            "Aloitan alusta"};
        int vastaus = JOptionPane.showOptionDialog(frame,
                "Onneksi olkoon! Tiesit kaikki sanat.\n\n"
                + "Haluatko lopettaa harjoittelun, vai aloittaa alusta?",
                "Onneksi olkoon!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                vaihtoehdot, //the titles of buttons
                vaihtoehdot[0]); //default button title
        if (vastaus == JOptionPane.NO_OPTION) {
            kayttoliittyma.valitseTiedosto();
            this.sovellus = kayttoliittyma.getSovellus();
        } else {
            frame.dispose();
        }
    }
    
}
