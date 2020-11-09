package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);
    }

    @Test
    public void saldoAlussaOikein(){
      assertTrue(kortti.saldo() == 10);
    }

    @Test
    public void toStringOikein(){
      assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void saldoKasvaaOikein(){
      kortti.lataaRahaa(5);
      assertTrue(kortti.saldo() == 15);
    }

    @Test
    public void rahanOttaminenToimiiJosTarpeeksi(){
      assertTrue(kortti.otaRahaa(9));
    }

    @Test
    public void rahanOttaminenToimiiJosEiTarpeeksi(){
      assertFalse(kortti.otaRahaa(11));
    }

}
