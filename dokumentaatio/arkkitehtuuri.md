# Arkkitehtuurikuvaus 

### Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:  

![ark](/dokumentaatio/kuvat/arkkitehtuuri1.1.png)  
  
Pakkaus slotamachine.ui sisältää JavaFX:llä toteutetun käyttöliittymän ja slotmachine.domain sovelluslogiikan. slotamchine.dao vastaa databasen käsittelystä.  

### Käyttöliittymä

Käyttöliittymässä on kolme eri näkymää; kirjautuminen, uuden käyttäjän luominen ja itse peli.  
Jokainen näistä on oma Scene-olionsa sijoitettuna sovelluksen stageen yksi kerrallaan ja käyttöliittymä on rakennettu slotmachine.ui.UI luokassa.  
  
Sovelluslogiikka on pyritty ulkoistamaan SlotMachineServicelle, jonka metodeja käyttöliittymä kutsuu.  

### Sovelluslogiikka

Luokka SlotMachineService vastaa ohjelman toiminnallisuuksista. SlotMachineService pääse käsiksi käyttäjiin, sekä databasen hallinnasta vastaavaan pakkaukseen slotmachine.dao.  
  
SlotMachineServicen ja muiden pakkausten välistä suhdetta kuvaa seuraava luokkakaavio:  
![luok](/dokumentaatio/kuvat/luokkakaavio.png)  

### Päätoiminnallisuudet

**Kirjautuminen**  
  
Käyttäjän kirjautuessa sisään etenee sovelluksen kontrolli seuraavasti:  

![seklog](/dokumentaatio/kuvat/sekvenssikaavio_kirjautuminen.png)  
  
**Uuden käyttäjän luominen**
  
Uutta käyttäjää luodessa etenee sovelluksen kontrolli seuraavasti:  

![sekcre](/dokumentaatio/kuvat/sekvenssikaavio_create1.png)  

  
