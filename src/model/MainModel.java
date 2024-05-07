package model;

import java.util.ArrayList;
import java.awt.Point;

public class MainModel {

    private ArrayList<Enemies> enemies = new ArrayList<>();
    private ArrayList<Spells> spells = new ArrayList<>();
    private Player player;
    private Level[] level = new Level[1]; // Anfangs nur 1 Level (festgelegte Anzahl an Leveln, deswegen Array)
    private int currentLevel = 0;
    private ArrayList<Point> possiblePositions = new ArrayList<>();

    public MainModel() {
        this.player = new Player(5, 0, 360); // Startposition muss noch angepasst werden + Speed
        createLevel(5); // Anzahl an Gegnern pro Level
        spawnEnemies(level[currentLevel].getEnemyCount());
    }

    public void createLevel(int enemyCount) {
        for (int levelNumber = 0; levelNumber < level.length; levelNumber++) { //Anzahl an Level wird oben festgelegt
            level[levelNumber] = new Level(levelNumber, enemyCount, levelNumber*2); // Enemyspeed noch änderbar
        }
    }

    public void spawnEnemies(int enemyCount) {
        calculatePossiblePositions();  // Stelle sicher, dass Positionen berechnet sind
        for (int i = 0; i < enemyCount && i < possiblePositions.size(); i++) {
            Point position = possiblePositions.get(i);
            enemies.add(new Enemies(10, level[currentLevel].getEnemySpeed(), position.x, position.y));
        }
    }

    public void moveEnemies() { // Bewegt alle Gegner
        for (Enemies enemy : enemies) {
            enemy.move();
        }
    }

    public void calculatePossiblePositions() {
        for (int x = 1280 - Enemies.getWIDTH(); x >= 1280 - 2*Enemies.getWIDTH(); x -= Enemies.getWIDTH()) {
            for (int y = 0; y <= 720 - Enemies.getHEIGHT(); y += Enemies.getHEIGHT()) {
                possiblePositions.add(new Point(x, y)); // zwei Reihen für Gegner = 16 Gegner
            }
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
        for (Enemies enemy : enemies) { // jeden Gegner mit Pos von jedem Spell vergleichen
            for (Spells spell : spells) {
                if (isCollision(enemy, spell)) { // Wenn Pos übereinstimmt
                    enemy.takeDamage(spell.getDamage());
                    spells.remove(spell); // Spell entfernen, wenn Gegner getroffen
                    if (!enemy.isAlive()) { // Überprüfen, ob Gegner nach Schaden noch lebt
                        level[currentLevel].enemyKilled();
                        enemies.remove(enemy); // Gegner entfernen, wenn tot
                    }
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


    public void restart() {
        level[currentLevel].reset();
        player.reset();
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
}
