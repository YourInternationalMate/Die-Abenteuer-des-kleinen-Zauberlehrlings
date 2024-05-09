package controller;

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
    private Timer timer;
    private Level[] level;

    public Controller(MainModel model, GUI view) {
        this.model = model;
        this.view = view;
        this.level = model.getLevel();
        startGame();
    }

    public void startGame(){
        this.timer = new Timer(16, e -> gameLoop()); // Timer starten mit 60 FPS (16 Millisekunden(laut Google))
        timer.start();
    }

    private void gameLoop() {
        if (level[model.getCurrentLevel()].isCompleted()) {
            if (model.getCurrentLevel() < level.length -1) {
                model.increaseCurrentLevel();
                initializeLevel();
            } else {
                timer.stop(); // lose Screen einbauen
                return;
            }
        }

        ArrayList<Enemies> enemies = model.getEnemies();

        for (Enemies enemy : enemies) {
            if (enemy.isOffScreen()) {
                timer.stop(); // Spiel stoppen
                return;
            }
        }

        model.moveEnemies();
        model.moveSpells();
        model.isEnemieHit();

        view.repaint();
    }

    private void initializeLevel() {
        model.nextLevel();
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e) { // Steuerung des Spielers
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: // Pfeiltaste nach oben
                model.movePlayerUp();
                break;
            case KeyEvent.VK_DOWN: // Pfeiltaste nach unten
                model.movePlayerDown();
                break;
            case KeyEvent.VK_SPACE: // Leertaste
                model.shootSpell();
                break;
        }
        view.repaint();
    }
}

