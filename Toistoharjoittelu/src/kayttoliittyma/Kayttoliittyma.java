package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import sovelluslogiikka.KayttoliittymaSovellus;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private KayttoliittymaSovellus sovellus;

    public Kayttoliittyma() {
        this.sovellus = new KayttoliittymaSovellus();
    }

    @Override
    public void run() {
        frame = new JFrame("Toistoharjoittelu");
        frame.setPreferredSize(new Dimension(400, 130));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

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

        ArvausKuuntelija arvausKuuntelija = new ArvausKuuntelija(sana1, sana2, sovellus, frame);
        arvaa.addActionListener(arvausKuuntelija);

        AsetusKuuntelija asetusKuuntelija = new AsetusKuuntelija(frame, sovellus);
        asetukset.addActionListener(asetusKuuntelija);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(sana1)
                    .addComponent(tyhja)
                    .addComponent(arvaa))
                .addComponent(ilmoitus)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(sana2)
                    .addComponent(asetukset))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(ilmoitus)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sana1)
                    .addComponent(sana2))
                .addComponent(tyhja)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(arvaa)
                    .addComponent(asetukset))
        );

    }

    public JFrame getFrame() {
        return frame;
    }
}
