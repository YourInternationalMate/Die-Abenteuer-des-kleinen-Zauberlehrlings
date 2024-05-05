package model;

import java.util.ArrayList;

public class Main {

    private ArrayList<Enemies> enemies = new ArrayList<>();
    private ArrayList<Spells> spells = new ArrayList<>();
    private Player player;
    private Level[] level = new Level[1]; // Anfangs nur 1 Level (festgelegte Anzahl an Leveln, deswegen Array)

    private int currentLevel = 0;

    public Main() {
        this.player = new Player(5, 0, 200); // Startposition muss noch angepasst werden + Speed
        createLevel(5); // Anzahl an Gegnern pro Level
        spawnEnemies(level[currentLevel].getEnemyCount());

        while (true) {
            moveEnemies();
            moveSpells();
            isEnemieHit();

            if (level[currentLevel].isCompleted()) {
                System.out.println("Level " + level[currentLevel].getLevelNumber() + " geschafft!");
                currentLevel++;
                break;
            }

            for (Enemies enemy : enemies) {
                if (enemy.isOffScreen()) {
                    System.out.println("Game Over!");
                    restart();
                }
            }

            // Steuerung Spieler?
        }


    }

    public void createLevel(int enemyCount) {
        for (int levelNumber = 0; levelNumber < level.length; levelNumber++) { //Anzahl an Level wird oben festgelegt
            level[levelNumber] = new Level(levelNumber, enemyCount);
        }
    }

    public void spawnEnemies(int enemyCount) {
        for (int i = 0; i < enemyCount; i++){
            enemies.add(new Enemies(10, 5, 700, 0)); // für nur einen Gegnertypen, X anpassen
        }
    }

    public void moveEnemies() { // Bewegt alle Gegner
        for (Enemies enemy : enemies) {
            enemy.move();
        }
    }

    public void moveSpells() { // Bewegt alle Spells
        for (Spells spell : spells) {
            spell.move();
        }
    }

    public void shootSpell() {
        Spells spell = new Spells(10, 10, player.getX(), player.getY()); //Erstellt Spell an Position von Spieler, X muss angepasst werden
        spells.add(spell);
    }

    public void isEnemieHit() {
        for (Enemies enemy : enemies) { // jeden Gegner mit Pos von jedem Spell vergleichen
            for (Spells spell : spells) {
                if (enemy.getX() == spell.getX() && enemy.getY() == spell.getY()) { // Wenn Pos übereinstimmt
                    enemy.takeDamage(spell.damage);
                    if (enemy.isAlive()) { // Überprüfen, ob Gegner nach Schaden noch lebt
                        level[currentLevel].enemyKilled();
                    }
                }
            }
        }
    }

    public void restart() {
        level[currentLevel].reset();
        player.reset();
        enemies.clear();
        spells.clear();
        spawnEnemies(level[currentLevel].getEnemyCount());
    }
}
