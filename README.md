# Pomodoro óra

Egyszerű Pomodoro időzítő Java Swing felülettel. A program munka- és szünetidőket kezel. A negyedik befejezett munkakör után hosszú szünetet indít.

## Követelmények

- Java 17 vagy újabb
- Visual Studio Code és az Extension Pack for Java
- JUnit 5 a modelltesztek futtatásához

## Projekt felépítése

```text
.
├── lib/
│   └── junit-platform-console-standalone-<verzio>.jar
├── src/
│   ├── controller/
│   │   └── PomodoroController.java
│   ├── model/
│   │   ├── Allapot.java
│   │   ├── PomodoroFigyelo.java
│   │   └── PomodoroModel.java
│   ├── view/
│   │   ├── BeallitasokDialogus.java
│   │   └── PomodoroView.java
│   └── Main.java
├── test/
│   └── model/
│       └── PomodoroModelTest.java
├── pomodoro.puml
└── README.md


Futtatás

VS Code
1. Nyisd meg a projekt mappáját a VS Code-ban.
2. Nyisd meg a src/Main.java fájlt.
3. Kattints a main metódus fölötti Run hivatkozásra.
Terminál
A projekt gyökérmappájából fordítsd le és indítsd el az alkalmazást:
javac -d out src/model/*.java src/view/*.java src/controller/*.java src/Main.java
java -cp out Main


Használat
- Start elindítja vagy folytatja az időzítőt.
- Szünet megállítja a visszaszámlálást.
- Reset visszaállítja a munkaidőt, és nullázza a befejezett körök számát.
- Beállítások megnyitja az időtartamokat módosító párbeszédablakot.


Alapértelmezett időtartamok:
- Munka: 25 perc
- Rövid szünet: 5 perc
- Hosszú szünet: 15 perc


Minden befejezett munkakör után rövid szünet következik. A negyedik befejezett munkakör után hosszú szünet indul.


Modelltesztek
A modell Swing felület nélkül tesztelhető. A tesztek ellenőrzik:
- a kezdeti állapotot és a hátralévő időt,
- a szüneteltetést és folytatást,
- a munka utáni rövid szünetet,
- a negyedik kör utáni hosszú szünetet,
- a reset működését.
A PomodoroModel nem importál javax.swing osztályokat. Az időzítést a controllerben lévő javax.swing.Timer végzi.


UML osztálydiagram
A PlantUML osztálydiagram a pomodoro.puml fájlban található. A diagramon látható, hogy a PomodoroModel a PomodoroFigyelo interfészen keresztül értesíti a feliratkozókat a tikkelésről és az állapotváltásról.