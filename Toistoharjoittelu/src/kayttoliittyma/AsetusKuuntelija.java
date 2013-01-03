package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sovelluslogiikka.KayttoliittymaSovellus;

public class AsetusKuuntelija implements ActionListener {

    private JFrame frame;
    private KayttoliittymaSovellus sovellus;

    public AsetusKuuntelija(JFrame frame, KayttoliittymaSovellus sovellus) {
        this.frame = frame;
        this.sovellus = sovellus;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object[] possibilities = null;
        String vastaus = (String) JOptionPane.showInputDialog(
                frame,
                "Kuinka monen sanan välein haluat\n"
                + "väärin arvattujen sanojen toistuvan",
                "Asetukset",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "3");
        int toistovali = Integer.parseInt(vastaus);
        sovellus.setToistovali(toistovali);
    }
}
