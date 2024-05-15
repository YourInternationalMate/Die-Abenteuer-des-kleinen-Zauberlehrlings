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
    private String name;
    private MainModel model;
    private GUI view;
    private Redirector redirector;
    private Timer timer;
    private Level[] level;

    private boolean[] keys = new boolean[256];

    private long lastSpellTime; // letzter Schuss
    private static final long SPELL_COOLDOWN = 300; // Cooldown Zeit

    public Controller(MainModel model, GUI view, Redirector redirector, String name) {
        this.model = model;
        this.view = view;
        this.level = model.getLevels();
        this.redirector = redirector;
        this.name = name;
    }

    public void startGame() {
        this.timer = new Timer(16, e -> gameLoop());
        timer.start();
    }

    public void winOrLose() {
        this.timer = new Timer(16, e -> winOrLoseLoop());
        timer.start();
    }

    private void gameLoop() {
        if (keys[KeyEvent.VK_UP]) model.movePlayerUp();
        if (keys[KeyEvent.VK_DOWN]) model.movePlayerDown();
        if (keys[KeyEvent.VK_SPACE] && canShoot()) {
            model.shootSpell();
            lastSpellTime = System.currentTimeMillis();
        }
        if (keys[KeyEvent.VK_ESCAPE]) {
            timer.stop();
            model.saveLevel(name);
            redirector.menu();
        }

        if (level[model.getCurrentLevel()].isCompleted()) {
            if (model.getCurrentLevel() < level.length - 1) {
                model.increaseCurrentLevel();
                model.nextLevel();
                timer.start();
            } else {
                timer.stop();
                model.resetLevel(name);
                redirector.win();
                return;
            }
        }

        ArrayList<Enemies> enemies = model.getEnemies();
        for (Enemies enemy : enemies) {
            if (enemy.isOffScreen()) {
                timer.stop();
                model.saveLevel(name);
                redirector.lose();
                return;
            }
        }

        model.moveEnemies();
        model.moveSpells();
        model.isEnemieHit();
        view.repaint();
    }

    private void winOrLoseLoop() {
        if (keys[KeyEvent.VK_ESCAPE]) {
            timer.stop();
            model.saveLevel(name);
            redirector.menu();
        }
    }

    private boolean canShoot() {
        return (System.currentTimeMillis() - lastSpellTime) >= SPELL_COOLDOWN;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}