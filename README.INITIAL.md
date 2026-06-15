# Mri Demo

## Fogalmak
 - A **Param**-típusú osztályok numerikus, szöveges értékek és numerikus listák tárolására alkalmas
    - Ezeket az értékeket a sequence-ekben és a process pluginokban tudjuk felhasználni
 - A **Sequence** a mérés módját meghatározó osztály
    - A param-ok segítségével tudjuk felparaméterezni őket
    - A HardwareDriver segítségével tudjuk a Hardware simulator-nak socket-en keresztül elküldeni őket, ahonnan a mérési adatokat kapjuk vissza.
- A mérési adatokon azután a **process plugin**-ok segítségével végezhetünk műveleteket.
- A **HardwareDriver** a socket kommunikációban a cliens szerepét látja el, a szerverhez kapcsolódás után a szekvenciadiagramból elküld egy oszlopot majd 128 adatpontot fogad.
- A **HardwareSimulator** feladata egyfelől, hogy a szerver feladatát ellássa a socket kommunikációban, illetve a mérés szimulálását is elvégzi, tesztadatot generál.
- Az **Instrument** a hardware konfigurációt olvassa ki az instrument.txt-ből, illetve lehetőséget ad a konfiguráció/hardware paraméterek futás közbeni módosítására.
- Az **AcquisitionManager** a mérés megindításától a process pluginok futtatását át a teljes folyamatot vezérli, illetve az adatok megjelenítéséért felel.


## Használata
 - A program indításakor megjelenik a fő ablak, amin egy legördülő listából lehet kiválasztani egy szekvenciát, majd egy gomb segítségével meg lehet nyitni az _Acquisition Manager_ című ablakot.
 - Ebben az ablakban láthatjuk a paraméterek értékeit, tudjuk őket változtatni, meg tudjuk tekinteni a mérési diagramot (_Show sequence_ gomb), illetve el tudjuk indítani a mérést.
 - A mérés indításakor egy új ablak ugrik fel, amiben folyamatosan láthatjuk a beérkező adatból egy-egy részt, majd a process pluginok futásának eredményét is itt láthatjuk.
