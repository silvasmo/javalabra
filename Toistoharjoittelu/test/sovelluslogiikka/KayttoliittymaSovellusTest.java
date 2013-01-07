package sovelluslogiikka;

import logiikka.Sanapari;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KayttoliittymaSovellusTest {
    private Sovellus sovellus;
    
    public KayttoliittymaSovellusTest() {
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
    public void annaSanapariAntaaEnsimmaisenSanaparin() {
        Sanapari pari = sovellus.annaSanapari();
        assertEquals("Helsinki", pari.getSana1());
    }
    
    @Test
    public void annaSanapariEiAnnaTyhjaaJosVaarinArvattuja() {
        for (int i = 0; i < 8; i++) {
            sovellus.tarkasta("maito");
        }
        Sanapari pari = sovellus.annaSanapari();
        assertEquals(false, pari.getSana1().equals(""));
    }
    
}
