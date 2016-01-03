![Insuright](https://raw.githubusercontent.com/matejperejda/Insuright-docImages/master/pics/logo.png)

#Obsah 
1. [O programe](#o-programe)
    - [Systémové požiadavky](#systémové-požiadavky)
        - [Rozlíšenie](#rozlíšenie)
        - [Softvérové požiadavky](#softvérové-požiadavky)
    - [Právne informácie](#právne-informácie)
        - [Vlastník](#vlastník)
        - [Technická podpora](#technická-podpora)
2. [Inštalácia](#inštalácia)
3. [Používanie](#používanie)
    - [Popis používateľského prostredia](#popis-používateľského-prostredia)
		- [Hlavné okno](#hlavné-okno)
		- [Formulár s výberom pripoistenia](#formulár-s-výberom-pripoistenia)
    - [Začíname](#začíname)
		- [Výber poistenia](#výber-poistenia)
		- [Vyplňovanie formulára](#vyplňovanie-formulára)
		- [Dostupnosť ponúk](#dostupnosť-ponúk)

##O programe
_Insuright_ (poradca pre rizikové životné poistenie) je desktopová Java aplikácia, ktorá vznikla na základe tímového projektu predmetu 
Softvérové inžinierstvo na Univerzite Pavla Jozefa Šafárika v Košiciach.

Hlavným cieľom tohto softvéru je poskytnúť jeho užívateľom možnosť porovnania cien aktuálne dostupných rizikových životných poistení 
v rôznych poisťovacích spoločnostiach podľa vopred zadefinovaných kritérií.
###Systémové požiadavky
Pre bezproblémový chod aplikácie je potrebné splniť nasledujúce požiadavky.

####Rozlíšenie
Pre korektné zobrazenie aplikácie je požadované minimálne rozlíšenie **750x600 px**. Pri nižšom rozlíšení nie je garantované
korektné zobrazenie a plná využiteľnosť funkcionality aplikácie.

####Softvérové požiadavky
Táto aplikácia je vytvorená v jazyku Java, takže jej funkčnosť je garantovaná pre operačné systémy Windows 7, Windows 8, Linux, iOS, Solaris a pod.   

_Pozn.:_ V niektorých typoch operačných systémov sa môžu vyskytnúť problémy pri načítaní obrázkov.  

###Právne informácie 
Všetky práva vyhradené. Žiadna časť tejto publikácie nesmie byť reprodukovaná žiadnym prostriedkom, ani distribuovaná akýmkoľvek spôsobom 
bez predchádzajúceho písomného povolenia autora. Autor si vyhradzuje právo zmien programových produktov popísaných v tejto publikácii bez 
predchádzajúceho upozornenia. V texte použité názvy programových produktov, firiem a pod., môžu byť ochrannými známkami alebo registrovanými 
ochrannými známkami príslušných vlastníkov.

####Vlastník
Tím projektu Insuright (poradca pre rizikové životné poistenie):

 - Alica Kačengová - projektový manažér
 - Milan Chrastina - správca systému
 - Marián Opiela - databázový architekt
 - Šimon Javorský - programátor
 - Kristián Vraštiak - programátor
 - Andrej Teťák - tester
 - Lucia Matušíková - analytik
 - Denisa Dupláková - analytik
 - Matej Perejda - dokumentarista

####Technická podpora
 - oficiálna webstránka: [Insuright](http://s.ics.upjs.sk/~swi_poistovna/)
 - email: insurightupjs@gmail.com

##Inštalácia
Aplikáciu je možné voľne stiahnuť z oficiálnej stránky [Insuright](http://s.ics.upjs.sk/~swi_poistovna/#first). 

Po stiahnutí a otvorení súboru _Insuright.jar_ sa softvér automatický spustí a je okamžite pripravený na používanie.

##Používanie

###Popis používateľského prostredia

####Hlavné okno
![hlavneOkno](https://raw.githubusercontent.com/matejperejda/Insuright-docImages/master/pics/mainWindow.png)

**A - Panel tlačidiel**

_1_ - Hlavné (úvodné) okno

_2_ - Odkaz na oficiálnu webstránku aplikácie

_3_ - Vymazať vyplnený formulár (Zrušiť)

_4_ - Potvrdiť

**B - Ponuka výberu poistenia**

_5_ - Výber poistenia s fixnou sumou

_6_ - Výber poistenia s klesajúcou sumou

#### Formulár s výberom pripoistenia
![formular](https://raw.githubusercontent.com/matejperejda/Insuright-docImages/master/pics/formular1_edited4.png)

**A - Formulár**

**B - Ponuka pripoistenia**

**C - Tlačidlo pre rozbalenie ponuky pripoistenia**

###Začíname 

####Výber poistenia 
V úvodnom okne aplikácie je možný výber z dvoch typov poistení:

 - poistenie s fixnou sumou 
 - poistenie s klesajúcou sumou
 
Užívateľ je povinný vybrať si práve jedno z nich. Výber uskutoční kliknutím ľavým tlačidlom myši na zvolené poistenie. Po samotnom výbere je okamžite presmerovaný na okno s formulárom.

####Vyplňovanie formulára
Formulár obsahuje povinné údaje, ktoré používateľ musí vyplniť, aby bolo možné presne určiť sumu poistného. 

 - rok narodenia 
 - doba poistenia *(v rokoch)*
 - riziková skupina
 - druh pracovného pomeru
 - poistná suma 

Ešte pred potvrdením údajov je užívateľovi ponúknuta možnosť výberu pripoistenia kliknutím na šípku pod formulárom. Táto ponuka je aktívna v prípade, ak sú všetky povinné položky správne vyplnené. Používateľ má pri pripoistení možnosť výberu:

 - smrť spôsobená úrazom
 - trvalé následky úrazu
 - trvalé následky úrazu s progresívnym plnením
 - denné odškodné za nevyhnutnú liečbu úrazu
 - denná dávka v prípade práceneschopnosti
 - denná dávka v prípade hospitalizácie
 - kritické choroby

Pre potvrdenie zadaných informácii je nutné kliknúť na ikonu **Potvrdiť** v pravom hornom rohu okna.
Všetky vyplnené textové polia formulára je možné vymazať kliknutím na ikonu **Zrušiť**.
V prípade zmeny typu poistenia kliknite na ikonu **Domov**.

_Pozn.:_ Bližšie informácie o správnom vyplnení konkrétneho poľa sa zobrazia po prejdení myšou nad znakom otáznika.

####Dostupnosť ponúk

Okno s dostupnými ponukami po výpočte na základe zadaných kritérii.

![ponuky](https://raw.githubusercontent.com/matejperejda/Insuright-docImages/master/pics/dostupnost_edited2.png)

**A - Sumy poistení**

**B - Logá poisťovní**
