package controller;

import interfaces.Redirector;
import model.Enemies;
import model.MainModel;
import model.Level;
import view.GUI;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Controller extends KeyAdapter {
    private String name; // Name des Spielers
    private MainModel model; // Modell, das den Spielzustand verwaltet
    private GUI view; // Ansicht zur Darstellung des Spiels
    private Redirector redirector; // Interface zur Navigation zwischen verschiedenen Ansichten
    private Timer timer; // Timer für das Spiel-Loop
    private Level[] level; // Array der Levels
    private boolean win = false; // Status, ob das Spiel gewonnen wurde
    private boolean lose = false; // Status, ob das Spiel verloren wurde
    private boolean multiplayer; // Status, ob das Spiel im Multiplayer-Modus ist

    private boolean[] keys = new boolean[256]; // Array zur Verwaltung der Tastendrücke

    private long lastSpellTime; // Zeit des letzten Schusses
    private static final long SPELL_COOLDOWN = 300; // Abklingzeit zwischen den Schüssen in Millisekunden

    public Controller(MainModel model, GUI view, Redirector redirector, String name, boolean multiplayer) { // Konstruktor
        this.model = model;
        this.view = view;
        this.level = model.getLevels();
        this.redirector = redirector;
        this.name = name;
        this.multiplayer = multiplayer;
    }

    public void startGame() { // Methode zum Starten des Spiels
        this.timer = new Timer(16, e -> gameLoop()); // Timer, der alle 16 Millisekunden das Spiel-Loop aufruft
        timer.start();
    }

    public void winOrLose() { // Methode zum Starten des Gewinn-/Verlier-Loop
        this.timer = new Timer(16, e -> winOrLoseLoop()); // Timer, der alle 16 Millisekunden das Gewinn-/Verlier-Loop aufruft
        timer.start();
    }

    private void gameLoop() { // Haupt-Spiel-Loop
        if (keys[KeyEvent.VK_UP]) model.movePlayerUp();
        if (keys[KeyEvent.VK_DOWN]) model.movePlayerDown();
        if (keys[KeyEvent.VK_SPACE] && canShoot()) {
            model.shootSpell();
            lastSpellTime = System.currentTimeMillis(); // Zeit des letzten Schusses aktualisieren
        }
        if (keys[KeyEvent.VK_ESCAPE]) {
            timer.stop();
            if (name.equals("Username")) {
                redirector.menu(); // Zum Menü zurückkehren
            } else {
                model.saveLevel(name); // Level speichern
                redirector.menu(); // Zum Menü zurückkehren
            }
        }

        if (level[model.getCurrentLevel()].isCompleted()) { // Überprüfen, ob das Level abgeschlossen ist
            if (model.getCurrentLevel() < level.length - 1 && !multiplayer) { // Nächstes Level laden, wenn es kein Multiplayer-Spiel ist und noch Levels übrig sind
                model.increaseCurrentLevel(); // Aktuelles Level erhöhen
                model.nextLevel();
                timer.start();
            } else { // Spiel gewonnen
                timer.stop();
                model.resetLevel(name); // Level zurücksetzen
                this.win = true;
                redirector.win(); // Gewinnbildschirm anzeigen
                return;
            }
        }

        ArrayList<Enemies> enemies = model.getEnemies();
        for (Enemies enemy : enemies) { // Überprüfen, ob ein Gegner den Bildschirm verlassen hat
            if (enemy.isOffScreen()) {
                timer.stop();
                if (name.equals("Username")) {
                    redirector.lose(); // Verliererbildschirm anzeigen
                } else {
                    model.saveLevel(name); // Level speichern
                    this.lose = true;
                    redirector.lose(); // Verliererbildschirm anzeigen
                }
                return;
            }
        }

        model.moveEnemies();
        model.moveSpells();
        model.isEnemieHit();
        view.repaint();
    }

    private void winOrLoseLoop() { // Loop für Gewinn-/Verlier-Status
        if (keys[KeyEvent.VK_ESCAPE]) { // Zum Menü zurückkehren, wenn die Escape-Taste gedrückt wird
            timer.stop();
            redirector.menu(); // Zum Menü zurückkehren
        }
    }

    private boolean canShoot() { // Überprüfen, ob der Spieler schießen kann
        return (System.currentTimeMillis() - lastSpellTime) >= SPELL_COOLDOWN; // Spieler kann schießen, wenn die Abklingzeit abgelaufen ist
    }

    @Override
    public void keyPressed(KeyEvent e) { // Wird aufgerufen, wenn eine Taste gedrückt wird
        keys[e.getKeyCode()] = true; // Taste als gedrückt markieren
    }

    @Override
    public void keyReleased(KeyEvent e) { // Wird aufgerufen, wenn eine Taste losgelassen wird
        keys[e.getKeyCode()] = false; // Taste als losgelassen markieren
    }

    public boolean isEnd() { // Überprüfen, ob das Spiel beendet ist (Gewinn und Verlust gleichzeitig)
        return win && lose;
    }
}