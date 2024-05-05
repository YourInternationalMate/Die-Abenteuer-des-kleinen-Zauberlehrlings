package model;

public class Level {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;

    public int levelNumber;
    public int enemyCount;
    public int killedEnemies;

    public Level(int levelNumber, int enemyCount) {
        this.levelNumber = levelNumber;
        this.enemyCount = enemyCount;
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
}
