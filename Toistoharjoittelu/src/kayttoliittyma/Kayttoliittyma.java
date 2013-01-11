package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import sovelluslogiikka.Sovellus;

/**
 * Luokka graafiselle käyttöliittymälle
 * @author Silva Smolander
 */
public class Kayttoliittyma implements Runnable {

    /**
     * Graafisen käyttöliittymän frame
     */
    private JFrame frame;
    /**
     * Sovelluslogiikan sisältävä olio
     */
    private Sovellus sovellus;

    /**
     * Kysyy käyttäjältä, mitä tiedostoa halutaan harjoitella.
     * Luo sovelluksen valittu tiedosto parametrinä
     */
    public Kayttoliittyma() {
        valitseTiedosto();
    }

    @Override
    public void run() {
        frame = new JFrame("Toistoharjoittelu");
        frame.setPreferredSize(new Dimension(400, 150));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        if (this.sovellus == null) {
            frame.dispose();
            return;
        }

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo komponentit käyttöliittymään, layoutin ja kuuntelijat.
     * @param container 
     */
    private void luoKomponentit(Container container) {

        GroupLayout layout = new GroupLayout(container);
        container.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        String sana = sovellus.annaSanapari().getSana1();
        JLabel sana1 = new JLabel(sana);
        JTextField sana2 = new JTextField(10);
        JButton arvaa = new JButton("Arvaa");
        JButton asetukset = new JButton("Asetukset");
        JLabel ilmoitus = new JLabel("Arvaa sanan pari");
        JLabel tyhja = new JLabel("");
        JLabel pisteet = new JLabel(sovellus.annaPisteet());

        ArvausKuuntelija arvausKuuntelija = new ArvausKuuntelija(this, pisteet, sana1, sana2, sovellus, frame);
        arvaa.addActionListener(arvausKuuntelija);
        arvaa.setDefaultCapable(true);
        frame.getRootPane().setDefaultButton(arvaa);

        AsetusKuuntelija asetusKuuntelija = new AsetusKuuntelija(frame, this);
        asetukset.addActionListener(asetusKuuntelija);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sana1)
                        .addComponent(tyhja)
                        .addComponent(asetukset))
                    .addComponent(ilmoitus)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sana2)
                        .addComponent(arvaa)
                        .addComponent(pisteet)));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addComponent(ilmoitus)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(sana1)
                        .addComponent(sana2))
                    .addComponent(tyhja)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(asetukset)
                        .addComponent(arvaa))
                    .addComponent(pisteet));

    }

    /**
     * Antaa käyttäjän valita harjoiteltavan tiedoston.
     * Luo uuden sovellus-olion valinnan mukaan ja
     * laittaa sovellus-muuttujan viittaamaan siihen.
     */
    public final void valitseTiedosto() {
        Object[] vaihtoehdot = {"paakaupunkeja.txt", "alkuaineet.txt"};
        String tiedosto = (String) JOptionPane.showInputDialog(
                frame,
                "Valitse tiedosto, jota \n"
                + "haluat harjoitella",
                "Tiedoston valinta",
                JOptionPane.PLAIN_MESSAGE,
                null,
                vaihtoehdot,
                "paakaupunkeja.txt");
        boolean loytyy = false;
        for (int i = 0; i < vaihtoehdot.length; i++) {
            if (vaihtoehdot[i].equals(tiedosto)) {
                loytyy = true;
            }
        }
        if (loytyy == false) {
            return;
        }
        this.sovellus = new Sovellus(tiedosto);

    }
    
    public JFrame getFrame() {
        return this.frame;
    }
    
    public void setSovellus(Sovellus s) {
        this.sovellus = s;
    }
    
    public Sovellus getSovellus() {
        return this.sovellus;
    }
    
}
