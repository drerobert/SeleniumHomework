
## Homework jegyzetek

## Case 1:

A glitch user miatt a kitöltendő user mező checkoutnál nem elég, ha csak látszik. Rendesen be kell töltenie.

Ezért a waitElementToBeClickable-t használtam: ha már betöltött és lehet kattintani, akkor jó lesz.

Maga a projektstruktúra: Egyszerű POM-ot (Page Object Model) használtam. Minden weboldal, amihez tartozott feladat, külön package-ben van a pages könyvtár alatt. Maguk a tesztek a weboldal flow-jához vannak kötve, így egyértelműen elválnak a nem egymáshoz tartozók.

JSON credentials: A DTO-t választottam, mert azt gondoltam modernebb megoldásnak. Itt hozzáteszem, hogy Java 17-et használok, ezért tudtam volna DTO recordot is használni, de mivel ezt most találtam, így azt a megoldást használtam, amit ismerek.

WaitHelper létrehozása: Hogy egyszerűbb legyen használni a várakozásokat (wait), illetve custom időtartammal is lehessen paraméterezni mindegyiket. Kb. 10 másodpercet adtam neki, de amint láttam a loginnál, ez kellett is, mert eltartott egy ideig ezzel a felhasználóval.

## Case 2:

A test case hiányosan van leírva. Kiegészítem olyan path-okkal, amiket logikusnak gondolok. Feltételezem, régebben írták a test case-t, de a lényege a footer validáció, így a jelenlegi évre fogok ráellenőrizni.

Valamiért a sima clear() nem működött, szóval vissza kell törölni manuálisan: passwordElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);

A második case-nél bejött egy új user, szóval szerettem volna azt is a credentials.json-ben tárolni. Ezért átírtam a JSON parsert, hogy több usert is tudjon kezelni. A JSON formátuma lehetne { credentials[{user1,pw1},{user2,pw2}] }, de inkább kihagytam a wrapper DTO-t.

## Case 3:

A nehézség itt az volt, hogy nem a gombokat akartam használni, mivel a bold és aláhúzott szöveget JavaScripttel is lehet injektálni.

Illetve a test case csak annyit mondott, hogy a szöveget validáljuk, hogy megjelenik-e.

Azt nem kötötte az orrunkra, hogy azt is validáljuk-e, hogy alá van-e húzva vagy bold-e az a rész, ami kell, így a teszt végül azt is validálja.

## Case 4:

Nagy fejfájást okozott ez a teszt. A title és a gomb is teljesen más azóta, amióta ezt a tesztet megírták.

A másik probléma, hogy az alap oldal viszonylag sokat töltött nálam (volt, hogy a végtelenségig, hiába volt timeout), ezért alkalmaztam a Chrome options beállításokban a következőt: options.setPageLoadStrategy(PageLoadStrategy.EAGER);

Ezáltal nem várja meg, amíg minden betölt, csak magukat a HTML elemeket.

A gomb szövege és színe is teljesen más volt; a jelenleg leírásnak megfelelőt az oldal aljához közeli gombnál választottam, de ezt mindenképp verifikálni kell egy meeting keretében. :) 

Itt is a pop-upok kikapcsolása segített, mert manuálisan észrevettem, hogy feljön egy. 

Úgy döntöttem, itt nem használok több page-et, elég a home page, mivel amikor átmegyek az új tabra, akkor csak a címet (title) nézem meg. 

A másiknál meg csak a gombot. Egyelőre feleslegesnek tartottam többet.

## Case 5:

Bár JSON formátumban kérték, hogy parse-oljam a választ, én mégis a DTO mellett döntöttem. Egyrészt mert már a többi tesztesetnél is ezt használtam, másrészt, ha jól tudom, ez az egyik legjobb megoldás skálázhatóság (scalability) miatt is. Vettem a bátorságot és mást használtam.

A másik, hogy először Rest Assuredet használtam, ami nem tudom, megfelel-e a követelményeknek (bár mint megtudtam, ez is OkHttp-re épül), de a jobb a békesség visszaváltottam sima OkHttp-re. Így is eltértem a DTO-val a követelménytől.

## Alapvető stratégia:

Próbáltam mindenhol ID-kat használni, ahol tudtam. Például a Case 4 esetében XPath-ot is kellett használnom a menühöz. Van, amikor így a legegyszerűbb. Néhol szöveg ellenőrzésnél "contains"-t használtam, de alapvetően nem tartom jó gyakorlatnak, mivel szöveg-checknél lehet false positive eredmény. 

A WaitHelpernél van default timeout, de vannak függvények, amik Fluent Waitet használnak.

Maga a projektstruktúra úgy néz ki, hogy van egy BaseUiTest, amiből származnak a tesztek. Ez azért kell, hogy ne kelljen mindig a loggert setupolni. 

Az API testing modul ettől eltér, mert szerettem volna külön kezelni azokat a teszteket. 

Ha több teszt lenne, valószínűleg lenne egy másik BaseApiClient vagy hasonló.

Igyekeztem úgy kezelni ezt az egészet, mint egy normál projektet, ezért a formázásra is próbáltam ügyelni, hogy konzisztens legyen. 

Minden teszt osztály úgy épül fel, hogy a konstansok vannak a tetején (ezt is szét lehetett volna választani). 

Ugyanez igaz a page osztályokra is.

Nem mindegyik wait-hez adtam Fluent Waites verziót. Ennek az oka, hogy feleslegesnek éreztem ehhez a projekthez. 

Van wait, amit csak egyszer használok egy adott test case-ben. Ettől függetlenül a WaitHelperbe soroltam, de ezt később még tovább lehetne bontani.

Logolási stratégia:

Próbáltam úgy logolni mindent, hogy jól látszódjon, melyik osztályban mi történik. Debugolási szempontból nagy segítség volt.
