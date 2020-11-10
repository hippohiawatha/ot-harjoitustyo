package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

  Kassapaate kassa;
  Maksukortti kortti;
  Maksukortti koyha;

    @Before
    public void setUp() {
      kassa = new Kassapaate();
      kortti = new Maksukortti(500);
      koyha = new Maksukortti(100);
    }

    //init
    @Test
    public void kassaOn() {
      assertTrue(kassa!=null);
    }

    @Test
    public void korttiOn() {
      assertTrue(kortti!=null);
    }

    @Test
    public void koyhaOn() {
      assertTrue(koyha!=null);
    }

    @Test
    public void latausToimiiKortilla() {
      kassa.lataaRahaaKortille(kortti, 100);
      assertEquals(600, kortti.saldo());
    }

    @Test
    public void negatiivinenLatausEiToimiiKortilla() {
      kassa.lataaRahaaKortille(kortti, -100);
      assertEquals(500, kortti.saldo());
    }

    @Test
    public void latausToimiiKassassa() {
      kassa.lataaRahaaKortille(kortti, 100);
      assertEquals(100100, kassa.kassassaRahaa());
    }

    @Test
    public void negatiivinenLatausEiToimiiKassassa() {
      kassa.lataaRahaaKortille(kortti, -100);
      assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void rahaAlussaOikein() {
      assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void lounaitaMyytyAlussaOikein() {
      assertEquals(0, kassa.maukkaitaLounaitaMyyty() + kassa.edullisiaLounaitaMyyty());
    }


    //maukkaat
    @Test
    public void maukasPalautusToimiiKateisella() {
      int change = kassa.syoMaukkaasti(500);
      assertEquals(100, change);
    }

    @Test
    public void kassaKasvaaOikeinMaukas() {
      kassa.syoMaukkaasti(500);
      assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void myytyjenMaukkaidenMaaraKasvaaOikein() {
      kassa.syoMaukkaasti(500);
      assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void liianVahanKateistaPalautusToimiiMaukas() {
      int change = kassa.syoMaukkaasti(300);
      assertEquals(300, change);
    }

    @Test
    public void kassaEiKasvaKunLiaanVahanKateistaMaukas() {
      kassa.syoMaukkaasti(100);
      assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void myytyjenMaukkaidenMaaraEiKasvaKunLiianVahanKateista() {
      kassa.syoMaukkaasti(100);
      assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    //edulliset
    @Test
    public void edullinenPalautusToimiiKateisella() {
      int change = kassa.syoEdullisesti(300);
      assertEquals(60, change);
    }

    @Test
    public void kassaKasvaaOikeinEdullinen() {
      kassa.syoEdullisesti(300);
      assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void myytyjenEdullistenMaaraKasvaaOikein() {
      kassa.syoEdullisesti(500);
      assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void liianVahanKateistaPalautusToimiiEdullinen() {
      int change = kassa.syoEdullisesti(100);
      assertEquals(100, change);
    }

    @Test
    public void kassaEiKasvaKunLiaanVahanKateistaEdullinen() {
      kassa.syoEdullisesti(100);
      assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void myytyjenEdullistenMaaraEiKasvaKunLiianVahanKateista() {
      kassa.syoEdullisesti(100);
      assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    //korttiMaukas
    @Test
    public void korttiVeloitusTrueMaukas() {
      assertTrue(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void kortiltaVeloitetaanMaukas() {
      kassa.syoMaukkaasti(kortti);
      assertEquals(100, kortti.saldo());
    }

    @Test
    public void myytyjenMaukkaidenMaaraKasvaa() {
      kassa.syoMaukkaasti(kortti);
      assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void myyntiEiNostaKassaaMaukas() {
      kassa.syoMaukkaasti(kortti);
      assertEquals(100000, kassa.kassassaRahaa());
    }

    //korttiEdullinen
    @Test
    public void korttiVeloitusTrueEdullinen() {
      assertTrue(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void kortiltaVeloitetaanEdullinen() {
      kassa.syoEdullisesti(kortti);
      assertEquals(260, kortti.saldo());
    }

    @Test
    public void myytyjenEdullistenMaaraKasvaa() {
      kassa.syoEdullisesti(kortti);
      assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void myyntiEiNostaKassaaEdullinen() {
      kassa.syoEdullisesti(kortti);
      assertEquals(100000, kassa.kassassaRahaa());
    }

    //koyhaMaukas
    @Test
    public void koyhaltaVeloitusFalseMaukas() {
      assertFalse(kassa.syoMaukkaasti(koyha));
    }

    @Test
    public void koyhaltaEiVeloitetaMaukas() {
      kassa.syoMaukkaasti(koyha);
      assertEquals(100, koyha.saldo());
    }

    @Test
    public void koyhaEiNostaMyyntiaMaukas() {
      kassa.syoMaukkaasti(koyha);
      assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void koyhaEiNostaKassaaMaukas() {
      kassa.syoMaukkaasti(koyha);
      assertEquals(100000, kassa.kassassaRahaa());
    }

    //koyhaEdullinen
    @Test
    public void koyhaltaVeloitusFalseEdullinen() {
      assertFalse(kassa.syoEdullisesti(koyha));
    }

    @Test
    public void koyhaltaEiVeloitetaEdullinen() {
      kassa.syoEdullisesti(koyha);
      assertEquals(100, koyha.saldo());
    }

    @Test
    public void koyhaEiNostaMyyntiaEdullinen() {
      kassa.syoEdullisesti(koyha);
      assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void koyhaEiNostaKassaaEdullinen() {
      kassa.syoEdullisesti(koyha);
      assertEquals(100000, kassa.kassassaRahaa());
    }

}
