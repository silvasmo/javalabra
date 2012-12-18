package logiikka;


import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SanalistaTest {
    
    private Sanalista sanalista;
    private ArrayList<Sanapari> lista;
    private File tiedosto;
    
    public SanalistaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        tiedosto = new File("paakaupunkeja.txt");
        try {
            sanalista = new Sanalista(tiedosto);
            lista = sanalista.annaLista();
        } catch (FileNotFoundException ex) {
            lista = new ArrayList<Sanapari>();
            System.out.println("Tiedostoa ei löytynyt");
        }
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void konstruktoriLisaaSanojaListaan() {
        assertEquals(false, lista.isEmpty());
    }
    
    @Test
    public void konstruktoriLisaaOikeanMaaranSanapareja() throws FileNotFoundException {
        int pituus = 0;
        Scanner lukija = new Scanner(tiedosto);
        while (lukija.hasNextLine()) {
            pituus++;
            lukija.nextLine();
        }
        int sanamaara = lista.size();
        
        assertEquals(pituus, sanamaara);
    }
    
    @Test
    public void sekoitaPalauttaaYhtaPitkanListan() {
        int alkupituus = lista.size();
        sanalista.sekoita();
        ArrayList<Sanapari> uusilista = sanalista.annaLista();
        int uusipituus = uusilista.size();
        
        assertEquals(alkupituus, uusipituus);
    }
    
    @Test
    public void poistaaOikeanAlkionListalta() {
        Sanapari pari = new Sanapari("Helsinki", "Suomi");
        sanalista.poistaListalta(pari);
        ArrayList<Sanapari> uusilista = sanalista.annaLista();
        boolean loyto = false;
        for (Sanapari sanapari : uusilista) {
            if (sanapari.equals(pari)) {
                loyto =true;
                break;
            }
        }

        assertEquals(false, loyto);
    }
    
    @Test
    public void annaSanapariPoistaaAlkionListalta() {
        Sanapari pari = sanalista.annaSanapari();
        ArrayList<Sanapari> uusilista = sanalista.annaLista();
        boolean loyto = false;
        for (Sanapari sanapari : uusilista) {
            if (sanapari.equals(pari)) {
                loyto =true;
                break;
            }
        }
        assertEquals(false, loyto);
    }
    
    @Test
    public void poistaListaltaEiPoistaJosAlkiotaEiListalla() {
        Sanapari pari = new Sanapari("tuli", "fire");
        sanalista.poistaListalta(pari);
        ArrayList<Sanapari> uusilista = sanalista.annaLista();
        assertEquals(lista.size(), uusilista.size());
    }
    
    @Test
    public void onTyhjaEiAnnaTrueKunAlkioitaListalla() {
        boolean tyhja = sanalista.onTyhja();
        assertEquals(false, tyhja);
    }
    
    @Test
    public void onTyhjaAntaaTrueKunListaTyhja() {
        for (int i = 0; i < 8; i++) {
            sanalista.annaSanapari();
        }
        boolean tyhja = sanalista.onTyhja();
        assertEquals(true, tyhja);
    }
    
    @Test
    public void listalleLisaysKasvattaaListanKokoa() {
        sanalista.lisaaSanapari(new Sanapari("maito", "milk"));
        ArrayList<Sanapari> uusilista = sanalista.annaLista();
        assertEquals(9, uusilista.size());
    }
    
    @Test
    public void listalleLisaysLisaaHalutunAlkion() {
        Sanapari pari = new Sanapari("maito", "milk");
        Sanalista uusiSanalista = new Sanalista();
        uusiSanalista.lisaaSanapari(pari);
        assertEquals(pari.getSana1(), uusiSanalista.annaSanapari().getSana1());
    }
    
    @Test
    public void listalleEiVoiLisataNullArvoa() {
        sanalista.lisaaSanapari(null);
        assertEquals(8, sanalista.annaLista().size());
    }
    
}
