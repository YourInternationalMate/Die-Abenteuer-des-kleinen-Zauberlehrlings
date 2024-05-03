package model;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Main {

    private Enemies[] enemies;
    private ArrayList<Spells> spells;
    private Player player;
    private Level[] level = new Level[1]; // Anfangs nur 1 Level

    public Main() {
//        this.enemies = new Enemies[]; // Feld solang, wie die Anzahl der Gegner in bestimmten Level
//        this.spells = new ArrayList<>();
//        this.player = new Player(5, 0, 0); // Startposition muss noch angepasst werden + Speed
//        this.level[0] = new Level(1, 5); // Levelnummer und Anzahl der Gegner
    }

    public void damageEnemy(Enemies enemy, Spells spell) {
        // Wenn der Spell den Gegner trifft, ziehe dem Gegner Schaden ab
    }

    public boolean isEnemyDead(Enemies enemy) {
        // Überprüfe, ob der Gegner tot ist
        return false;
    }

    public void moveEnemies(ArrayList<Enemies> enemies) {
        // Bewege alle Gegner
    }

    public void moveSpells(ArrayList<Spells> spells) {
        // Bewege alle Spells
    }

    public boolean isLevelCompleted(Level level) {
        // Überprüfe, ob das Level abgeschlossen ist
        return false;
    }

    public boolean lostLevel(ArrayList<Enemies> enemies) {
        // Überprüfe, ob der Spieler das Level verloren hat
        return false;
    }
}
