package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logiikka.Sanapari;
import sovelluslogiikka.KayttoliittymaSovellus;

public class ArvausKuuntelija implements ActionListener {

    private JLabel sana1;
    private JTextField sana2;
    private KayttoliittymaSovellus sovellus;
    private JFrame frame;

    public ArvausKuuntelija(JLabel sana1, JTextField sana2, KayttoliittymaSovellus sovellus, JFrame frame) {
        this.sana1 = sana1;
        this.sana2 = sana2;
        this.sovellus = sovellus;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String toinenSana = sana2.getText();
        boolean oikein = sovellus.tarkasta(toinenSana);
        if (oikein) {
            JOptionPane.showMessageDialog(frame, "Oikein!", "Tulos",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Väärin.", "Tulos",
                    JOptionPane.PLAIN_MESSAGE);
        }

        Sanapari seuraavaPari = sovellus.annaSanapari();
        if (seuraavaPari.getSana1().equals("")) {
            sana1.setText("");
            sana2.setText("");
            return;
        }
        sana1.setText(seuraavaPari.getSana1());
        sana2.setText("");

    }
}
