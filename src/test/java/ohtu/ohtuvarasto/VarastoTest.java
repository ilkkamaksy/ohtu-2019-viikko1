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
    Varasto varastoSaldolla;
    Varasto epakelpoVarasto;
    Varasto taysiVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
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
    public void lisaysLisaaSaldoaNegatiivisellaLuvulla() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(-1.0);

        // saldon pitäisi olla sama kun aiemmin 
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaVarastoTayteen() {
        double tilaa = varasto.getTilavuus() + 1;
        varasto.lisaaVarastoon(tilaa);

        // tilaa pitäisi olla 0
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        // saldon pitäisi olla tilavuus 
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
        
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenNegatiivinenLukuPalauttaaNolla() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenEnemmanKuinSaldoPalauttaaKaikkiMitaVoidaan() {
        varasto.lisaaVarastoon(8);
        
        double saldo = varasto.getSaldo();
        double lkm = saldo*2;
        
        double saatuMaara = varasto.otaVarastosta(lkm);

        assertEquals(saldo, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @After
    public void tearDownTyhjaVarasto() {
        varasto = null;
        assertNull(varasto);
    }
    
    @Before
    public void setUpVarastoKahdellaParametrilla() {
        double tilavuus = 10;
        double saldo = 5;
        varastoSaldolla = new Varasto(tilavuus, saldo);
    }

    @Test
    public void konstruktoriLuoVarastonSaldolla() {
        assertEquals(5, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @After
    public  void tearDownSaldollinenVarasto() {
        varastoSaldolla = null;
        assertNull(varastoSaldolla);
    }
    
    @Before
    public void setUpVarastoNollatilavuudellaJaNegatiivisellaSaldolla() {
        double tilavuus = 0.0;
        double saldo = -5;
        epakelpoVarasto = new Varasto(tilavuus, saldo);
    }
    
    @Test
    public void konstruktoriLuoKayttokelvottomanVaraston() {
        assertEquals(0, epakelpoVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, epakelpoVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @After
    public void tearDownEpaKelpoVarastoNollaTilavuudellaKahdellaParametrilla() {
        epakelpoVarasto = null;
        assertNull(epakelpoVarasto);
    }
    
    @Before
    public void setUpVarastoNollatilavuudella() {
        double tilavuus = 0.0;
        epakelpoVarasto = new Varasto(tilavuus);
    }
    
    @Test
    public void konstruktoriLuoKayttokelvottomanVarastonNollaTilavuudella() {
        // varaston tilavuus on 0
        assertEquals(0, epakelpoVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @After
    public void tearDownEpaKelpoVarastoNollaTilavuudellaYhdellaParametrilla() {
        epakelpoVarasto = null;
        assertNull(epakelpoVarasto);
    }
    
    @Before
    public void setUpVarastoTayteen() {
        // varasto menee täyteen
        double tilavuus = 10;
        double saldo = tilavuus*2;
        taysiVarasto = new Varasto(tilavuus, saldo);
    }
    
    @Test
    public void konstruktoriLuoTaydenVaraston() {
        assertEquals(10, taysiVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(taysiVarasto.getTilavuus(), taysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringPalauttaaMerkkijonon() {
        double saldo = taysiVarasto.getSaldo();
        double tilaa = taysiVarasto.paljonkoMahtuu();
        String teksti = "saldo = " + saldo + ", vielä tilaa " + tilaa;
        assertEquals(teksti, taysiVarasto.toString());
    }
}