package model;

import java.util.ArrayList;
import java.awt.Point;

public class MainModel {

    private int currentLevel = 0;

    private ArrayList<Enemies> enemies = new ArrayList<>();
    private ArrayList<Spells> spells = new ArrayList<>();
    private Player player;
    private Level[] level = new Level[4]; //Anzahl an Level

    public MainModel() {
        this.player = new Player(5, 0, 360); // Speed evtl noch anpassen
        createLevel(4); // Start Anzahl an Gegnern pro Level; jedes Level+1
        spawnEnemies(level[currentLevel].getEnemyCount());
    }

    public void createLevel(int enemyCount) {
        for (int levelNumber = 0; levelNumber < level.length; levelNumber++) { //Anzahl an Level wird oben festgelegt
            level[levelNumber] = new Level(levelNumber, enemyCount+levelNumber, 1); // Enemyspeed noch änderbar
        }
    }

    public void spawnEnemies(int enemyCount) {
        randomizeEnemySpawn(calculatePossiblePositions()); // Spawnt Gegner an zufälligen Positionen
    }

    public ArrayList<Point> calculatePossiblePositions() {
        ArrayList<Point> possiblePositions = new ArrayList<>();
        for (int x = 1280 - Enemies.getWIDTH(); x >= 1280 - 2*Enemies.getWIDTH(); x -= Enemies.getWIDTH()) {
            for (int y = 0; y <= 720 - Enemies.getHEIGHT(); y += Enemies.getHEIGHT()) {
                possiblePositions.add(new Point(x, y)); // zwei Reihen für Gegner = 16 Gegner max
            }
        }
        return possiblePositions;
    }

    public void randomizeEnemySpawn(ArrayList<Point> positionsToUse) {
        for (int i = 0; i < level[currentLevel].getEnemyCount(); i++) {
            int index = (int) (Math.random() * positionsToUse.size()); // Zufälligen Index wählen
            Point position = positionsToUse.remove(index); // Position entfernen, gegen doppeltes spawnen
            enemies.add(new Enemies(10, level[currentLevel].getEnemySpeed(), position.x, position.y));
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
        Spells spell = new Spells(10, 10, player.getX(), player.getY()+20); //Erstellt Spell an Position von Spieler
        spells.add(spell);
    }

    public void movePlayerUp() {
        player.moveUp();
    }

    public void movePlayerDown() {
        player.moveDown();
    }

    public int getPlayerX() {
        return player.getX();
    }

    public int getPlayerY() {
        return player.getY();
    }

    public void isEnemieHit() {
        ArrayList<Enemies> copyOfEnemies = new ArrayList<>(enemies);
        for (Enemies enemy : copyOfEnemies) { // jeden Gegner mit Pos von jedem Spell vergleichen
            for (Spells spell : spells) {
                if (isCollision(enemy, spell)) { // Wenn Pos übereinstimmt
                    enemy.takeDamage(spell.getDamage());
                    spells.remove(spell); // Spell entfernen, wenn Gegner getroffen
                    if (!enemy.isAlive()) { // Überprüfen, ob Gegner nach Schaden noch lebt
                        level[currentLevel].enemyKilled();
                        enemies.remove(enemy); // Gegner entfernen, wenn tot
                    }
                    break;
                }
            }
        }
    }

    public boolean isCollision(Enemies enemy, Spells spell) {
        int enemyX = enemy.getX();
        int enemyY = enemy.getY();
        int enemyWidth = Enemies.getWIDTH();
        int enemyHeight = Enemies.getHEIGHT();

        int spellX = spell.getX();
        int spellY = spell.getY();
        int spellWidth = Spells.getWIDTH();
        int spellHeight = Spells.getHEIGHT();

        return enemyX < spellX + spellWidth &&
                enemyX + enemyWidth > spellX &&
                enemyY < spellY + spellHeight &&
                enemyY + enemyHeight > spellY;
    }

    public void nextLevel() {
        level[currentLevel].reset(); // Killcount auf 0 setzen
        player.reset(); // Startposition
        enemies.clear();
        spells.clear();
        spawnEnemies(level[currentLevel].getEnemyCount());
    }

    public ArrayList<Spells> getSpells() {
        return spells;
    }

    public ArrayList<Enemies> getEnemies() {
        return enemies;
    }

    public Level[] getLevel() {
        return level;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void increaseCurrentLevel() {
        this.currentLevel += 1;
    }
}
