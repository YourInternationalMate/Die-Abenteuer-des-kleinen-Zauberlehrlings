package controller;

import interfaces.GameStarter;
import model.Enemies;
import model.MainModel;
import model.Level;
import ui.GUI;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Controller extends KeyAdapter {
    private MainModel model;
    private GUI view;
    private GameStarter gameStarter;
    private Timer timer;
    private Level[] level;

    private boolean[] keys = new boolean[256];

    private long lastSpellTime; // letzter Schuss
    private static final long SPELL_COOLDOWN = 500; // Cooldown Zeit

    public Controller(MainModel model, GUI view, GameStarter gameStarter) {
        this.model = model;
        this.view = view;
        this.level = model.getLevel();
        this.gameStarter = gameStarter;
    }

    public void startGame(){
        this.timer = new Timer(16, e -> gameLoop()); // Timer starten mit 60 FPS (16 Millisekunden(laut Google))
        timer.start();
    }

    private void initializeLevel() {
        model.nextLevel();
        timer.start();
    }

    private void gameLoop() {
        if (keys[KeyEvent.VK_UP]) { // Nach oben
            model.movePlayerUp();
        }
        if (keys[KeyEvent.VK_DOWN]) { // Nach unten
            model.movePlayerDown();
        }
        if (keys[KeyEvent.VK_SPACE] && canShoot()) { // Schießen
            model.shootSpell();
            lastSpellTime = System.currentTimeMillis();
        }
        if (keys[KeyEvent.VK_ESCAPE]) { // Pause
            timer.stop();
            model.saveLevel("Test"); // Namen einbauen -> Eingabe fehlt noch
            gameStarter.menu();
        }


        if (level[model.getCurrentLevel()].isCompleted()) {
            if (model.getCurrentLevel() < level.length -1) {
                model.increaseCurrentLevel();
                initializeLevel();
            } else {
                timer.stop();
                model.resetLevel("Test"); // Namen einbauen -> Eingabe fehlt noch
                return;
            }
        }

        ArrayList<Enemies> enemies = model.getEnemies();

        for (Enemies enemy : enemies) {
            if (enemy.isOffScreen()) {
                timer.stop(); // Spiel stoppen
                return; // lose Screen einbauen
            }
        }

        model.moveEnemies();
        model.moveSpells();
        model.isEnemieHit();

        view.repaint();
    }

    private boolean canShoot() { // Cooldown für Schüsse, gegen Spam
        return (System.currentTimeMillis() - lastSpellTime) >= SPELL_COOLDOWN;
    }

    @Override
    public void keyPressed(KeyEvent e) { // in Array speichern, welche Taste gedrückt ist
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}

