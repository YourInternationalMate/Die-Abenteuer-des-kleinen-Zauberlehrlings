# Die Abenteuer des kleinen Zauberlehrlings

Willkommen zu "Die Abenteuer des kleinen Zauberlehrlings", einem von Space Invaders inspirierten Spiel mit einem magischen Twist! Dieses Spiel wurde mit Java und Java Swing als Universitätsprojekt entwickelt.

## Inhaltsverzeichnis
- [Features](#features)
- [Installation](#installation)
- [Spielanleitung](#spielanleitung)
- [Code-Struktur](#code-struktur)
- [Ordnerstruktur](#ordnerstruktur)
- [Quellen](#quellen)
- [Mitwirkende](#mitwirkende)
- [Beitragende](#beitragende)
- [Lizenz](#lizenz)

## Features
- **Magisches Thema**: Spiele als Zauberer, der sich gegen Wellen von magischen Kreaturen verteidigt.
- **Klassisches Gameplay**: Inspiriert vom klassischen Space Invaders Spiel, jedoch mit einer einzigartigen Wendung.
- **Benutzeroberfläche**: Entwickelt mit Java Swing für eine reaktionsschnelle und interaktive UI.
- **Spielzustände**: Beinhaltet verschiedene Spielzustände wie Menü, Spielen, Gewinnen und Verlieren.
- **Multiplayer**: Erlebe gemeinsam mit deinem Freund das Abenteuer und stellt euch den Herausforderungen zusammen.
- **Levelwiedereinstieg**: Starte in dem Level, in dem du aufgehört hast zu spielen.

## Installation
Um "Die Abenteuer des kleinen Zauberlehrlings" auf deinem lokalen Rechner zu spielen, folge diesen Schritten:

1. **Repository klonen**:
    ```bash
    git clone https://github.com/YourUsername/Die-Abenteuer-des-kleinen-Zauberlehrlings.git
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

## Spielanleitung - Die Abenteuer des kleinen Zauberlehrlings

**Startmenü**

In Abbildung 1 ist das Startmenü mit seinen verschiedenen Auswahlmöglichkeiten dargestellt. Der Benutzer wird aufgefordert, einen Namen einzugeben, um den Spielstand zu speichern. Mit dem "Play"-Button wird das Einzelspielerspiel gestartet. Der "Multiplayer"-Button führt zum Mehrspieler-Modus. Mit der Schaltfläche "Exit" wird das Spiel beendet.
 
 
**Playermodus**

In Abbildung 2 wird die Zaubererwelt gezeigt, in der der Spieler seinen Charakter, einen Zauberlehrling auf einem Besen, auf der linken Seite navigiert. Der Spieler muss Monster besiegen, um voranzukommen. Das Spiel umfasst vier Level, die nacheinander durchgespielt werden. Mit jedem Level wird der Schwierigkeitsgrad erhöht. Die Benutzeroberfläche ist benutzerfreundlich und einfach gestaltet.
 
 
**Darstellung der Zaubererwelt**

Das Spiel wird in einem rechteckigen, zweidimensionalen Fenster angezeigt. Die Bildgröße ist festgelegt und zeigt die aktuellen Positionen des Spielers sowie der Monster.
 
 
**Singleplayer**
 
   - **Steuerung des Spielers:**
    Der Spieler bewegt seinen Charakter am linken Bildschirmrand durch Drücken der Pfeiltasten nach oben und unten. Mit der Leertaste kann der Spieler         einen Zauberspruch wirken.
 
 
   - **Bewegung der Monster:**
    Die Monster bewegen sich unabhängig vom Spieler. Ihre Höhe wird zufällig festgelegt und bleibt konstant, während sie sich von links nach rechts            bewegen. Mit jedem Level steigen die Geschwindigkeit und die Lebenspunkte der Monster nehmen ab. Werden die Monster von Zaubersprüchen getroffen,          verlieren sie Lebenspunkte. Sobald ihre Lebenspunkte aufgebraucht sind, verschwinden die Monster.
 
 
**Multiplayer**
 
   - **Rollen auswählen:**
    Im Multiplayermenü startet Spieler 1 das Spiel, indem er "Play" drückt. Spieler 2 wählt "Control" aus, um die Monster zu steuern.
 
   - **Steuerung der Monster:**
    Zuerst startet Spieler 1 das Spiel durch Drücken von "Play", um den Server zu öffnen. Anschließend kann Spieler 2 die Anzahl und Position der Monster      frei bestimmen, wobei die Anzahl auf vier Objekte begrenzt ist. Einmal ausgewählte Objekte können nicht abgewählt werden. Mit dem "Place"-Button setzt     Spieler 2 die Anzahl und Platzierung der Monster fest.  Zudem muss die IP-Adresse vom Spieler 1 eingetragen werden. Beide Spieler bestreiten das           Abenteuer gemeinsam in einem Level.
 
   - **Steuerung des Spielers:**
    Die Steuerung des Spielers erfolgt wie im Einzelspielermodus.
 
 
**Kollision von Spieler und Monster**

Erreicht ein Monster den linken Spielfeldrand, verliert der Spieler das Spiel und ein Gameover-Screen erscheint. Eine direkte Kollision zwischen Monster und Spieler ist dafür nicht erforderlich. Mit der ESC-Taste gelangt man zurück zum Startmenü.
 
 
**Speichern und Laden**

Wird das Spiel unterbrochen, wird der aktuelle Spielstand automatisch gespeichert. Beim Wiederaufnehmen des Spiels startet man im selben Level, in dem das Spiel gestoppt wurde, von vorne.

**Abbildung 1: Startmenü**

![Screenshot 2024-06-13 173205](https://github.com/YourInternationalMate/Die-Abenteuer-des-kleinen-Zauberlehrlings/assets/155901809/0ea15076-fd78-47e8-bc83-6136b32cf1a7)

**Abbildung 2: Playermodus**

![Screenshot 2024-06-13 173221](https://github.com/YourInternationalMate/Die-Abenteuer-des-kleinen-Zauberlehrlings/assets/155901809/fb698da5-1116-46fd-9b02-f2c567b4db56)


## UML
![Prog2_Rusch_Koenigsmann_UML](https://github.com/YourInternationalMate/Die-Abenteuer-des-kleinen-Zauberlehrlings/assets/73663569/f8956b81-1008-4396-bfa0-d200663801d7)


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

```
src
├── Main.java
├── Server
│   ├── GameClient.java
│   └── GameServer.java
├── controller
│   └── Controller.java
├── data
│   ├── SQLite.java
│   └── game.db
├── interfaces
│   └── Redirector.java
├── model
│   ├── Enemies.java
│   ├── Level.java
│   ├── MainModel.java
│   ├── Player.java
│   ├── SerializablePoint.java
│   └── Spells.java
├── resources
│   ├── controll
│   │   ├── button_enemy.png
│   │   └── button_place.png
│   ├── game
│   │   ├── backgrounds
│   │   │   ├── level0.jpg
│   │   │   ├── level1.jpg
│   │   │   ├── level2.jpg
│   │   │   └── level3.jpg
│   │   ├── ending
│   │   │   ├── lose.jpg
│   │   │   └── win.jpg
│   │   ├── enemies
│   │   │   ├── enemy0.png
│   │   │   ├── enemy1.png
│   │   │   ├── enemy2.png
│   │   │   ├── enemy3.png
│   │   │   ├── enemy4.png
│   │   │   ├── enemy5.png
│   │   │   └── enemy6.png
│   │   ├── icon.jpg
│   │   ├── player.png
│   │   ├── spell.png
│   │   └── story.jpg
│   └── menu
│       ├── background.jpg
│       ├── background_clean.jpg
│       ├── button_back.png
│       ├── button_controll.png
│       ├── button_exit.png
│       ├── button_multiplayer.png
│       ├── button_play.png
│       └── button_resume.png
└── view
    ├── Control.java
    ├── GUI.java
    ├── Lose.java
    ├── Menu.java
    ├── Multiplayer.java
    ├── Story.java
    └── Win.java
```

## Quellen
* Screen Storyline: Adobe Photoshop AI
* UML mit Intellij

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
