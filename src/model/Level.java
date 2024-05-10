package model;

import java.awt.*;

public class Level {
    private Image image;
    private int levelNumber;
    private int enemyCount;
    private int killedEnemies;
    private int enemySpeed;

    public Level(Image image, int levelNumber, int enemyCount, int enemySpeed) {
        this.image = image;
        this.levelNumber = levelNumber;
        this.enemyCount = enemyCount;
        this.enemySpeed = enemySpeed;
        this.killedEnemies = 0;
    }

    public boolean isCompleted() { // wenn alle Gegner besiegt wurden, ist das Level geschafft
        return killedEnemies == enemyCount;
    }

    public void enemyKilled() {
        killedEnemies++;
    }

    public void reset() { // falls man Level neustarten möchte
        killedEnemies = 0;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public int getEnemySpeed(){
        return enemySpeed;
    }

    public Image getImage() {
        return image;
    }
}
