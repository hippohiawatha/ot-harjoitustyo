# Slotmachine

Sovellus on perinteinen hedelmäpeli. Kehitysvaiheessa olevasta pelistä puuttuu vielä oleellisia osia, kuten voittojen tallentuminen pelaajalle. Toistaiseksi voi siis katsoa, kun hedelmien kuvat vaihtuvat.

### Dokumentaatio

[Työaikakirjanpito](https://github.com/hippohiawatha/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)  
[Vaatimusmäärittely](https://github.com/hippohiawatha/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)  
[Arkkitehtuuri](https://github.com/hippohiawatha/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

### Realaset

[Viikko 5](https://github.com/hippohiawatha/ot-harjoitustyo/releases/tag/Viikko5)  

### Komentorivitoiminnot

**Testaus**

Testit suoritetaan komennolla

    mvn test
    
Testikattavuusraportti luodaan komennolla

    mvn jacoco:report
    
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

**Suoritettavan jarin generointi**

Komento

    mvn package
    
generoi hakemistoon target suoritettavan jar-tiedoston SlotMachine-1.0-SNAPSHOT.jar

**Checkstyle**

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

    mvn jxr:jxr checkstyle:checkstyle
    
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html

