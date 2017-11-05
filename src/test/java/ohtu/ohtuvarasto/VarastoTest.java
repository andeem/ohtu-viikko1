package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void testEiLuodaNegTaiNollavarastoa() {
        varasto2 = new Varasto(0.0);
        assertEquals(varasto2.getTilavuus(), 0.0, vertailuTarkkuus);
    }

    @Test
    public void testLuodaanVarasto1() {
        Varasto varasto3 = new Varasto(10, 5);
        assertEquals(10, varasto3.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, varasto3.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void testLuodaanVirheellinenVarsto() {
        Varasto varasto4 = new Varasto(-10, 5);
        assertEquals(0, varasto4.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto4.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void testLuodaanVirheellinenVarsto2() {
        Varasto varasto4 = new Varasto(5, -5);
        assertEquals(5, varasto4.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto4.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void testLuodaanLiianTaysiVarasto() {
        Varasto varasto1 = new Varasto(10,20);
        assertEquals(10, varasto1.getSaldo(), vertailuTarkkuus);
        
    }
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void testEiLisataYliTIlavuuden() {
        varasto.lisaaVarastoon(10000000);
        assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void testEiLisataNegMaaraa() {
        double maara = varasto.getSaldo();
        varasto.lisaaVarastoon(-19);
        assertEquals(maara, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testEiOtetaNegMaaraa() {
        double maara = varasto.getSaldo();
        varasto.otaVarastosta(-199);
        assertEquals(maara, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testOtetaanEnemmanMitaVarastossa() {
        double varastossa = varasto.getSaldo();
        double otettu = varasto.otaVarastosta(199999);
        assertEquals(varastossa, otettu, vertailuTarkkuus);
    }
    
    @Test
    public void testToString() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }

}
