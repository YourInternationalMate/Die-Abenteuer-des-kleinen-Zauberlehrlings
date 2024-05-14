# Die Abenteuer des kleinen Zauberlehrlings

Willkommen zu "Die Abenteuer des kleinen Zauberlehrlings", einem von Space Invaders inspirierten Spiel mit einem magischen Twist! Dieses Spiel wurde mit Java und Java Swing als Universitätsprojekt entwickelt.

## Inhaltsverzeichnis
- [Features](#features)
- [Installation](#installation)
- [Spielanleitung](#spielanleitung)
- [Code-Struktur](#code-struktur)
- [Ordnerstruktur](#ordnerstruktur)
- [Mitwirkende](#mitwirkende)
- [Beitragende](#beitragende)
- [Lizenz](#lizenz)

## Features
- **Magisches Thema**: Spiele als Zauberer, der sich gegen Wellen von magischen Kreaturen verteidigt.
- **Klassisches Gameplay**: Inspiriert vom klassischen Space Invaders Spiel, jedoch mit einer einzigartigen Wendung.
- **Benutzeroberfläche**: Entwickelt mit Java Swing für eine reaktionsschnelle und interaktive UI.
- **Spielzustände**: Beinhaltet verschiedene Spielzustände wie Menü, Spielen, Gewinnen und Verlieren.
- **Punktestandverfolgung**: Verfolge deine Highscores und versuche, sie zu überbieten.

## Installation
Um "Die Abenteuer des kleinen Zauberlehrlings" auf deinem lokalen Rechner zu spielen, folge diesen Schritten:

1. **Repository klonen**:
    ```bash
    git clone https://github.com/YourInternationalMate/Die-Abenteuer-des-kleinen-Zauberlehrlings.git
    cd Die-Abenteuer-des-kleinen-Zauberlehrlings
    ```

2. **Spiel kompilieren**:
    Stelle sicher, dass JDK installiert ist. Kompiliere dann das Spiel mit folgendem Befehl:
    ```bash
    javac -d bin src/*.java
    ```

3. **Spiel starten**:
    ```bash
    java -cp bin Main
    ```

## Spielanleitung
1. **Spiel starten**: Starte das Spiel und drücke 'Start' im Hauptmenü.
2. **Steuerung**:
    - **Nach oben bewegen**: Verwende die Pfeil-nach-oben-Taste.
    - **Nach unten bewegen**: Verwende die Pfeil-nach-unten-Taste.
    - **Schießen**: Drücke die Leertaste, um einen Zauber zu wirken.
3. **Ziel**: Besiege alle ankommenden Wellen von magischen Kreaturen, ohne dass sie dich erreichen. Versuche, den höchsten Punktestand zu erzielen.

## Code-Struktur
- **Main.java**: Einstiegspunkt des Spiels, initialisiert das Hauptfenster und verwaltet Spielzustandsübergänge.
- **Controller.java**: Verwalten der Spiel-Logik, Benutzereingaben und Spielschleifen.
- **MainModel.java**: Enthält Spieldaten und Logik, einschließlich Spieler, Gegner, Zauber und Level.
- **Player.java**: Repräsentiert den Spielercharakter mit Attributen und Bewegungsmethoden.
- **Enemies.java**: Repräsentiert die Gegnercharaktere mit Attributen und Bewegungsmethoden.
- **Spells.java**: Repräsentiert die vom Spieler gewirkten Zauber mit Attributen und Bewegungsmethoden.
- **Level.java**: Repräsentiert die Level des Spiels.
- **SQLite.java**: Verwaltet Datenbankinteraktionen zum Speichern und Laden von Spieldaten.
- **GUI.java**: Zeichnet die Spielkomponenten auf dem Bildschirm, einschließlich des Spielers, der Zauber und der Gegner.
- **Menu.java**: Beinhaltet das Hauptmenü des Spiels mit Optionen zum Starten und Beenden des Spiels.
- **Lose.java**: Zeigt den Verlustbildschirm an, wenn der Spieler das Spiel verliert.
- **Win.java**: Zeigt den Gewinnbildschirm an, wenn der Spieler das Spiel gewinnt.
- **Story.java**: Zeigt die Hintergrundgeschichte des Spiels an.

## Ordnerstruktur
Hier ist die vollständige Ordnerstruktur des Projekts:

src
├── controller
│ └── Controller.java
├── data
│ └── SQLite.java
├── interfaces
│ └── Redirector.java
├── model
│ ├── Enemies.java
│ ├── Level.java
│ ├── MainModel.java
│ ├── Player.java
│ └── Spells.java
├── resources
│ ├── game
│ │ ├── backgrounds
│ │ │ ├── level0.jpg
│ │ │ ├── level1.jpg
│ │ │ ├── level2.jpg
│ │ │ └── level3.jpg
│ │ ├── ending
│ │ │ ├── lose.jpg
│ │ │ └── win.jpg
│ │ ├── enemies
│ │ │ ├── enemy0.png
│ │ │ ├── enemy1.png
│ │ │ ├── enemy2.png
│ │ │ ├── enemy3.png
│ │ │ ├── enemy4.png
│ │ │ ├── enemy5.png
│ │ │ └── enemy6.png
│ │ ├── icon.jpg
│ │ ├── player.png
│ │ ├── spell.png
│ │ └── story.jpg
│ └── menu
│ ├── background.jpg
│ ├── button_exit.png
│ ├── button_play.png
│ └── button_resume.png
├── view
│ ├── GUI.java
│ ├── Lose.java
│ ├── Menu.java
│ ├── Story.java
│ └── Win.java
└── Main.java


## Mitwirkende
- **Mika Königsmann**
- **Friederike Rusch**

## Beitragende
Beiträge sind willkommen! Wenn du zu diesem Projekt beitragen möchtest, folge bitte diesen Schritten:

1. Repository forken.
2. Einen neuen Branch erstellen (`git checkout -b feature-branch`).
3. Deine Änderungen vornehmen.
4. Deine Änderungen committen (`git commit -m 'Add some feature'`).
5. Den Branch pushen (`git push origin feature-branch`).
6. Einen Pull Request öffnen.

## Lizenz
Dieses Projekt ist unter der MIT Lizenz lizenziert. Siehe die [LICENSE](LICENSE) Datei für Details.