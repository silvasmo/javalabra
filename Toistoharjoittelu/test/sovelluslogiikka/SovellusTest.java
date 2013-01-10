package sovelluslogiikka;

import logiikka.Sanapari;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SovellusTest {
    private Sovellus sovellus;
    
    public SovellusTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        this.sovellus = new Sovellus();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tarkastaTarkastaaOikein() {
        Sanapari pari = sovellus.annaSanapari();
        boolean tarkastus = sovellus.tarkasta(pari.getSana2());
        assertEquals(true, tarkastus);
    }
    
    @Test
    public void tarkastusVaarinKunPitaakin() {
        boolean tarkastus = sovellus.tarkasta("maito");
        assertEquals(false, tarkastus);
    }
    
    @Test
    public void tarkastaVaihtaaSeuraavaanSanaanKunOikein() {
        Sanapari pari = sovellus.annaSanapari();
        sovellus.tarkasta(pari.getSana2());
        Sanapari seuraavaPari = sovellus.annaSanapari();
        assertEquals(false, pari.getSana1().equals(seuraavaPari.getSana1()));
    }
    
    @Test
    public void tarkastaVaihtaaSeuraavaanSanaanKunVaarin() {
        Sanapari pari = sovellus.annaSanapari();
        sovellus.tarkasta("maito");
        Sanapari seuraavaPari = sovellus.annaSanapari();
        assertEquals(false, pari.getSana1().equals(seuraavaPari.getSana1()));
    }
    
    @Test
    public void josViimeinenSanapariVaarinKysyyUudelleen() {
        for (int i = 1; i < 8; i++) {
            Sanapari pari = sovellus.annaSanapari();
            sovellus.tarkasta(pari.getSana2());
        }
        sovellus.tarkasta("maito");
        assertEquals(false, sovellus.annaSanapari().getSana1().equals(""));
    }
    
    @Test
    public void tarkastaSanaToimiiKunOikein() {
        Sanapari pari = new Sanapari("maito", "milk");
        boolean tarkastus = sovellus.tarkastaSana(pari, "milk");
        assertEquals(true, tarkastus);
    }
    
    @Test
    public void tarkastaSanaToimiiKunVaarin() {
        Sanapari pari = new Sanapari("maito", "milk");
        boolean tarkastus = sovellus.tarkastaSana(pari, "maito");
        assertEquals(false, tarkastus);
    }
    
    @Test
    public void annaSanapariAntaaTyhjanParinKunListatTyhjat() {
        for (int i = 0; i < 8; i++) {
            Sanapari pari = sovellus.annaSanapari();
            sovellus.tarkasta(pari.getSana2());
        }
        Sanapari pari = sovellus.annaSanapari();
        assertEquals("", pari.getSana1());
    }
    
    @Test
    public void annaSanapariEiAnnaTyhjaaJosVaarinArvattuja() {
        for (int i = 0; i < 8; i++) {
            sovellus.tarkasta("maito");
        }
        Sanapari pari = sovellus.annaSanapari();
        assertEquals(false, pari.getSana1().equals(""));
    }
    
    @Test
    public void setToistovaliToimiiPienellaSyotteella() {
        sovellus.setToistovali(4);
        assertEquals(4, sovellus.getToistovali());
    }
    
    @Test
    public void setToistovaliEiToimiNagatiivisellaSyotteella() {
        sovellus.setToistovali(-3);
        assertEquals(3, sovellus.getToistovali());
    }
    
    @Test
    public void annaPisteetAntaaOikeanMerkkijononAlussa() {
        String pisteet = sovellus.annaPisteet();
        assertEquals("pisteitä: 0/8", pisteet);
    }
    
    @Test
    public void annaPisteetAntaaOikeanMerkkijonoOikeanArvauksenJalkeen() {
        sovellus.tarkasta(sovellus.annaSanapari().getSana2());
        String pisteet = sovellus.annaPisteet();
        assertEquals("pisteitä: 1/8", pisteet);
    }
    
    @Test
    public void annaPisteetAntaaOikeanMerkkijononVaaranArvauksenJalkeen() {
        sovellus.tarkasta("milk");
        String pisteet = sovellus.annaPisteet();
        assertEquals("pisteitä: 0/8", pisteet);
    }
    
    @Test
    public void paivitaPisteetToimiiKunOikein() {
        sovellus.paivitaPisteet(true);
        String pisteet = sovellus.annaPisteet();
        assertEquals("pisteitä: 1/8", pisteet);
    }
    
    @Test
    public void paivitaPisteetEiTeeMitaanKunVaarin() {
        sovellus.paivitaPisteet(false);
        String pisteet = sovellus.annaPisteet();
        assertEquals("pisteitä: 0/8", pisteet);
    }
    
}
