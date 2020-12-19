# Slotmachine

Sovellus on perinteinen hedelmäpeli. Kehitysvaiheessa olevasta pelistä puuttuu vielä oleellisia osia, kuten voittojen tallentuminen pelaajalle. Toistaiseksi voi siis katsoa, kun hedelmien kuvat vaihtuvat.

### Dokumentaatio

[Työaikakirjanpito](https://github.com/hippohiawatha/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)  
[Vaatimusmäärittely](https://github.com/hippohiawatha/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)  
[Arkkitehtuuri](https://github.com/hippohiawatha/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)  
[Käyttöohje](https://github.com/hippohiawatha/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)
### Realaset

[Viikko 5](https://github.com/hippohiawatha/ot-harjoitustyo/releases/tag/Viikko5)  
[Viikko 6](https://github.com/hippohiawatha/ot-harjoitustyo/releases/tag/viikko6)
[(Tästä pääsee releaseen, jossa kuvat toimivat)](https://github.com/hippohiawatha/ot-harjoitustyo/releases/tag/Viikko6.1.2)

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

Tiedoston checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

    mvn jxr:jxr checkstyle:checkstyle
    
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html  
  
**JavaDoc**

JavaDocin generointi suoritetaan komennolla  

    mvn javadoc:javadoc

JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html
