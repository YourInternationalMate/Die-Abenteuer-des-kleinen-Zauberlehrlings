package model;

import java.awt.*;

public class Level {
    private Image image; // Bild für das Level
    private int levelNumber; // Levelnummer
    private int enemyCount; // Anzahl der Gegner im Level
    private int killedEnemies; // Anzahl der getöteten Gegner
    private int enemySpeed; // Geschwindigkeit der Gegner

    public Level(Image image, int levelNumber, int enemyCount, int enemySpeed) { // Konstruktor
        this.image = image; // Initialisiert das Bild des Levels
        this.levelNumber = levelNumber; // Initialisiert die Levelnummer
        this.enemyCount = enemyCount; // Initialisiert die Anzahl der Gegner
        this.enemySpeed = enemySpeed; // Initialisiert die Geschwindigkeit der Gegner
        this.killedEnemies = 0; // Setzt die Anzahl der getöteten Gegner auf 0
    }

    public boolean isCompleted() { // Überprüft, ob das Level abgeschlossen ist
        return killedEnemies == enemyCount; // Das Level ist abgeschlossen, wenn alle Gegner getötet wurden
    }

    public void enemyKilled() { // Erhöht die Anzahl der getöteten Gegner
        killedEnemies++;
    }

    public void reset() { // Setzt die Anzahl der getöteten Gegner zurück
        killedEnemies = 0;
    }

    public int getLevelNumber() { // Getter für die Levelnummer
        return levelNumber;
    }

    public int getEnemyCount() { // Getter für die Anzahl der Gegner
        return enemyCount;
    }

    public int getEnemySpeed() { // Getter für die Geschwindigkeit der Gegner
        return enemySpeed;
    }

    public Image getImage() { // Getter für das Bild des Levels
        return image;
    }
}