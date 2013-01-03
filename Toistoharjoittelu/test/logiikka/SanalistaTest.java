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
    public void toinenKonstruktoriLuoTyhjanListan() {
        Sanalista toinenLista = new Sanalista();
        assertEquals(true, toinenLista.annaLista().isEmpty());
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
    public void seuraavaPariPoistaaAlkionListalta() {
        Sanapari pari = sanalista.annaSanapari();
        sanalista.seuraavaPari();
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
    public void seuraavanParinJalkeenNykyinenPariVaihtunut() {
        Sanapari pari = sanalista.annaSanapari();
        sanalista.seuraavaPari();
        Sanapari toinenPari = sanalista.annaSanapari();
        assertEquals(false, pari.equals(toinenPari));
    }
    
    @Test
    public void seuraavaPariAntaaTyhjanSanaparinJosListaTyhja() {
        Sanalista uusiLista = new Sanalista();
        uusiLista.seuraavaPari();
        Sanapari pari = uusiLista.annaSanapari();
        assertEquals("", pari.getSana1());
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
            sanalista.seuraavaPari();
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
        sanalista.lisaaSanapari(pari);
        assertEquals(true, sanalista.annaLista().contains(pari));
    }
    
    @Test
    public void listalleEiVoiLisataNullArvoa() {
        sanalista.lisaaSanapari(null);
        assertEquals(8, sanalista.annaLista().size());
    }
    
    @Test
    public void lisaaminenTyhjalleListalleMuuttaaNykyistaParia() {
        Sanapari pari = new Sanapari("maito", "milk");
        Sanalista uusiSanalista = new Sanalista();
        uusiSanalista.lisaaSanapari(pari);
        assertEquals("maito", uusiSanalista.annaSanapari().getSana1());
    }
    
    @Test
    public void lisaaminenEiTyhjalleListalleEiVaihdaNykyistaParia() {
        Sanapari pari = new Sanapari("maito", "milk");
        sanalista.lisaaSanapari(pari);
        assertEquals(false, sanalista.annaSanapari().getSana1().equals("maito"));
    }
    
    @Test
    public void lisaysOnnistuuListanKeskelle() {
        Sanapari pari = new Sanapari("maito", "milk");
        sanalista.lisaaSanapari(2, pari);
        sanalista.seuraavaPari();
        sanalista.seuraavaPari();
        assertEquals(true, sanalista.annaSanapari().getSana1().equals("maito"));
    }
    
    @Test
    public void lisaysOnnistuuListanEnsimmaiseksi() {
        Sanapari pari = new Sanapari("maito", "milk");
        sanalista.lisaaSanapari(0, pari);
        assertEquals(true, sanalista.annaLista().get(0).getSana1().equals("maito"));
    }
    
    @Test
    public void lisaysOnnistuuVaikkaPaikkaSuurempiKuinLista() {
        Sanapari pari = new Sanapari("maito", "milk");
        sanalista.lisaaSanapari(10, pari);
        assertEquals(true, sanalista.annaLista().get(8).getSana1().equals("maito"));
    }
    
}
