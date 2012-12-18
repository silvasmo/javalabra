package sovelluslogiikka;



import logiikka.Sanapari;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SovellusTest {
    Sovellus sovellus;
    
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
        sovellus = new Sovellus();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tarkastusOikeinKunPitaakinOlla() {
        Sanapari pari = new Sanapari("Helsinki", "Suomi");
        boolean tulos = sovellus.tarkasta(pari, "Suomi");
        assertEquals(true, tulos);
    }
    
    @Test
    public void tarkastusVaarinKunPitaakinOlla() {
        Sanapari pari = new Sanapari("Helsinki", "Suomi");
        boolean tulos = sovellus.tarkasta(pari, "Ruotsi");
        assertEquals(false, tulos);
    }
    
    @Test
    public void haeSanapariPalauttaaEnsimmaisenSanaparinEnsimmaisellaKerralla() {
        Sanapari pari = sovellus.haeSanapari(3, 1);
        assertEquals("Helsinki", pari.getSana1());
    }
    
    
}
