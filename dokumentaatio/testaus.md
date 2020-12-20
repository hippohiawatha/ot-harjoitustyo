# Testausdokumentti

Ohjelmaa on yksikkö- ja integraatiotestattu JUnitilla, sekä manuaalisesti.  

## Yksikkö- ja integraatiotestaus

#### Sovelluslogiikka
Automatiosoidut testit testaavat domain kansiosta löytyviä sovelluksen logiikasta huolehtivia luokkia, sekä databasen kanssa kommunikoinnista vastaavia doa kansiosta löytyviä luokkia.  

#### DAO-luokat
Dao-luokkien testaukseen automatisoidut testit luovat oman testi databasen, joka poistetaan testien lopuksi.

## Testauskattavauus
Käyttöliittymäluokkaa lukuunottamatta testien rivikattavuus on 76% ja haarautumakattavuus 62%  
Testikattavuus kärsii kahdesta metodista, jotka palauttavat Imagen ja ImageViewn, näiden testaus ei onnistunut.  
Rivikattavuus kärsii myös hieman try/catcheistä, joiden catchit eivät tule testatuiksi.  


## Järjestelmätestaus
Järjestelmätestaus on suoritettu manuaalisesti

#### Asennus ja konfiguraatio
Sovellusta on testattu sekä macilla, windowsilla, että etätyöpöydän cubbli linuxilla.  
Cubbli sulkee database, jos ohjelman käynnistää terminaalin kautta.  

#### Toiminnallisuudet
Määrittelydokumentin toiminnallisuudet on testattu. Myös virhetilanteet on otettu huomioon.
