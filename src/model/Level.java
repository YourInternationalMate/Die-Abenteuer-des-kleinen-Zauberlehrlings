package model;

public class Level {
    private int levelNumber;
    private int enemyCount;
    private int killedEnemies;
    private int enemySpeed;

    public Level(int levelNumber, int enemyCount, int enemySpeed) {
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
}
