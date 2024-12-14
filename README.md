# Algoritmusok és adatszerkezetek gyakorlat házi feladat
## Makszim Balázs Imre ENSGO3

Feladat eredeti leírása:
https://www.spoj.com/problems/PALIN/

# PALIN - A következő palindrom

Egy pozitív egész szám **palindrom**, ha annak tizedes számrendszerbeli ábrázolása ugyanaz, ha balról jobbra, illetve jobbról balra olvassuk

Adott egy **K** pozitív egész szám, amely legfeljebb 1000000 számjegyből áll, írjuk ki a **K**-nál nagyobb legkisebb palindromot. A számokat mindig bevezető nullák nélkül kell kiírni.

### Bemenet

Az első sorban egy egész szám, **t** szerepel, amely a tesztesetek számát jelzi. Az ezt követő **t** sorban a **K** számok szerepelnek.

### Kimenet

Minden **K**-ra írjuk ki a **K**-nál nagyobb legkisebb palindromot.

### Példa

**Input:**

```java
2
808
2133
```

**Output:**

```java
818
2222
```

# Megoldás

A `main` függvény értelmezi az **input**ot. Majd minden `k` **input**ra meghívja a `findNextPalindrome` függvényt, amely vissza adja a megoldást. A függvény tömböket alkalmaz a gyors futás érdekében.

## 1. Tükrözés (szimmetria létrehozása)
A számot két részre osztjuk: egy bal és egy jobb felére. A palindrom egyik kulcsfontosságú jellemzője, hogy a jobb oldali számjegyek pontosan tükrözik a bal oldali számjegyeket.

Például, ha a bemenet: `2133`, akkor a bal oldalt (itt `21`) tükrözzük a jobb oldalra, hogy a `2112`-t kapjuk. 

**Miért tükrözzük?**  
A tükrözés az első próbálkozás, hogy megkapjuk a következő legkisebb palindromot. Ha a tükrözött szám nagyobb, mint az eredeti, akkor az a válasz. Tehát az algoritmus először próbálkozik azzal, hogy egyszerűen csak tükrözi a számot.

## 2. Összehasonlítás
Ezután összehasonlítjuk a tükrözött számot az eredetivel. Ha a tükrözött szám nagyobb, mint a bemeneti szám, akkor kész is vagyunk, mert megtaláltuk a következő legkisebb palindromot. Például:

- Ha a bemenet: `808`, akkor tükrözzük: `818`. Mivel `818` > `808`, ez a válasz.
- Ha a bemenet: `2133`, akkor tükrözzük: `2112`. Mivel `2112` < `2133`, folytatjuk a következő lépéssel.

## 3. Növelés
Ha a tükrözött szám kisebb vagy egyenlő az eredetivel, akkor növelnünk kell a bal oldalt, hogy biztosan nagyobb legyen a palindrom. 

Itt jön a számláló növelésének folyamata: a bal oldali számot (vagy az egész bal oldali felet, ha szükséges) megnöveljük, és újra tükrözzük. Ha a bal oldalon találunk 9-eseket, akkor ezek nullává válnak, és átvitel történik, tehát a következő számot növeljük (mint amikor egy 999-ből 1000-et csinálunk). 

**Például:**
- A bemenet: `2133`, a tükrözött szám: `2112`. Mivel `2112` < `2133`, most a bal oldalt kell növelnünk: `22`. Az új palindrom `2222`, amely már nagyobb, mint `2133`.

## 4. A 9-esek speciális esete
Mi van, ha a növelt szám egy 9-es? Például, ha a bemenet `199999`, akkor a tükrözés `199991`-et ad. Itt már nem lehet egyszerűen növelni, hanem `0`-ra állítjuk, tükrözzük, és egy új szám keletkezik: `190091`. Ezt kifelé haladva megtesszük, mindaddig, amíg `9`-esekkel talákozunk. Így keletkezik az `100001`.
Ekkor, ha minden számjegy 9, akkor új szám keletkezik, például `999` -> `1001`, vagy `99999` -> `100001`.
Egyéb esetben pedig növeljük kifelé haladva az első számot, ami nem `9`, jelen esetben az `1`-et, majd tükrözzük. Így a megoldás: `200002`
