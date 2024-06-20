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

    public boolean isCompleted() {
        return killedEnemies == enemyCount; // Das Level ist abgeschlossen, wenn alle Gegner getötet wurden
    }

    public void enemyKilled() {
        killedEnemies++;
    }

    public void reset() {
        killedEnemies = 0;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public int getEnemySpeed() {
        return enemySpeed;
    }

    public Image getImage() {
        return image;
    }
}