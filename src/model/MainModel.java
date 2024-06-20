package model;

import data.SQLite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainModel {

    private int currentLevel;
    private ArrayList<Enemies> enemies = new ArrayList<>(); // Liste der Gegner
    private ArrayList<Spells> spells = new ArrayList<>(); // Liste der Zaubersprüche
    private Player player; // Spieler-Objekt
    private Level[] level = new Level[4]; // Array der Level

    private boolean multiplayer = false;

    private Image playerImage;
    private Image spellImage;
    private Image[] enemyImages = new Image[7]; // Array der Gegnerbilder
    private Image[] backgroundImages = new Image[4]; // Array der Hintergrundbilder

    public MainModel(String name, boolean multiplayer) {
        this.multiplayer = multiplayer;
        loadImages();
        getLevel(name); // Level aus der Datenbank laden
        this.player = new Player(playerImage, 7, 0, 360); // Spieler initialisieren
        createLevel();
        if (!multiplayer) {
            spawnEnemies(); // Gegner spawnen, falls nicht im Multiplayer-Modus
        }
    }

    // Getter und Setter

    public int getPlayerX() {
        return player.getX();
    }

    public int getPlayerY() {
        return player.getY();
    }

    public ArrayList<Spells> getSpells() {
        return spells;
    }

    public ArrayList<Enemies> getEnemies() {
        return enemies;
    }

    public Level[] getLevels() {
        return level;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void increaseCurrentLevel() {
        this.currentLevel += 1;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level[currentLevel];
    }

    // Bewegung

    public void movePlayerUp() {
        player.moveUp();
    }

    public void movePlayerDown() {
        player.moveDown();
    }

    public void moveEnemies() {
        enemies.forEach(Enemies::move);
    }

    public void moveSpells() {
        spells.forEach(Spells::move);
    }

    // Zauber spawnen

    public void shootSpell() {
        spells.add(new Spells(spellImage, Math.min(currentLevel + 8, 10), 10, player.getX() + 115, player.getY() + 35));
    }

    // Gegner spawnen

    public void spawnEnemies() {
        randomizeEnemySpawn(calculatePossiblePositions());
    }

    public void placeEnemies(ArrayList<SerializablePoint> positionsToUse) { // Gegner an bestimmten Positionen platzieren
        for (int i = 0; i < positionsToUse.size(); i++) {
            enemies.add(new Enemies(enemyImages[(int) (Math.random() * enemyImages.length)], 10, level[currentLevel].getEnemySpeed(), positionsToUse.get(i).x, positionsToUse.get(i).y));
        }
    }

    public ArrayList<Point> calculatePossiblePositions() {
        ArrayList<Point> possiblePositions = new ArrayList<>();
        for (int x = 1280 - Enemies.getWIDTH(); x >= 1280 - 2 * Enemies.getWIDTH(); x -= Enemies.getWIDTH()) {
            for (int y = 0; y <= 720 - Enemies.getHEIGHT(); y += Enemies.getHEIGHT()) {
                possiblePositions.add(new Point(x, y));
            }
        }
        return possiblePositions;
    }

    public void randomizeEnemySpawn(ArrayList<Point> positionsToUse) {
        for (int i = 0; i < level[currentLevel].getEnemyCount(); i++) {
            int index = (int) (Math.random() * positionsToUse.size());
            Point position = positionsToUse.remove(index);
            enemies.add(new Enemies(enemyImages[(int) (Math.random() * enemyImages.length)], 10, level[currentLevel].getEnemySpeed(), position.x, position.y));
        }
    }

    // Allgemeine Logik

    public void isEnemieHit() {
        ArrayList<Enemies> copyOfEnemies = new ArrayList<>(enemies);
        for (Enemies enemy : copyOfEnemies) {
            for (Spells spell : spells) {
                if (isCollision(enemy, spell)) {
                    enemy.takeDamage(spell.getDamage());
                    spells.remove(spell);
                    if (!enemy.isAlive()) {
                        level[currentLevel].enemyKilled();
                        enemies.remove(enemy);
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

    // Level Logik

    public void createLevel() {
        for (int levelNumber = 0; levelNumber < level.length; levelNumber++) {
            level[levelNumber] = new Level(backgroundImages[levelNumber], levelNumber, Math.min(4 + levelNumber, 16), Math.min(levelNumber + 1, 5));
        } // nur bis zur Maximalen Anzahl an Gegnern + nicht zu schnell
    }

    public void nextLevel() {
        level[currentLevel].reset();
        player.reset();
        enemies.clear();
        spells.clear();
        spawnEnemies();
    }

    // Datenbank Methoden

    public void saveLevel(String name) {
        try {
            SQLite.updateLevel("UPDATE score SET level = " + currentLevel + " WHERE name = '" + name + "'");
        } catch (Exception e) {
            System.err.println("Fehler beim Speichern des Levels in der Datenbank: " + e.getMessage());
        }
    }

    public void resetLevel(String name) {
        try {
            SQLite.updateLevel("UPDATE score SET level = 0 WHERE name = '" + name + "'");
        } catch (Exception e) {
            System.err.println("Fehler beim Zurücksetzen des Levels in der Datenbank: " + e.getMessage());
        }
    }

    public void getLevel(String name) {
        try {
            String level = SQLite.getLevelNumber("SELECT * FROM score WHERE name = '" + name + "'");

            if (!level.isEmpty()) {
                this.currentLevel = Integer.parseInt(level);
            } else {
                this.currentLevel = 0;
                insertLevel(name);
            }
        } catch (Exception e) {
            System.err.println("Fehler beim Abrufen des Levels aus der Datenbank: " + e.getMessage());
            this.currentLevel = 0;
        }
    }

    public void insertLevel(String name) { // User anlegen
        try {
            SQLite.insertLevel("INSERT INTO score (name, level) VALUES ('" + name + "', 0)");
        } catch (Exception e) {
            System.err.println("Fehler beim Einfügen des Levels in die Datenbank: " + e.getMessage());
        }
    }

    // Bilder

    public void loadImages() {
        playerImage = new ImageIcon("src/resources/game/player.png").getImage();
        spellImage = new ImageIcon("src/resources/game/spell.png").getImage();

        for (int i = 0; i <= 6; i++) {
            enemyImages[i] = new ImageIcon("src/resources/game/enemies/enemy" + i + ".png").getImage();
        }

        for (int i = 0; i <= 3; i++) {
            backgroundImages[i] = new ImageIcon("src/resources/game/backgrounds/level" + i + ".jpg").getImage();
        }
    }
}