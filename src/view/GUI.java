package view;

import model.MainModel;
import model.Spells;
import model.Enemies;

import javax.swing.JPanel;
import java.awt.*;

public class GUI extends JPanel {
    private final MainModel model;

    public GUI(MainModel model) {
        this.model = model;

        setPreferredSize(new Dimension(1280, 760));
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawPlayer(g);
        drawSpells(g);
        drawEnemies(g);
    }

    private void drawBackground(Graphics g) {
        g.drawImage(model.getLevel().getImage(), 0, 0, null); // Zeichnet das Hintergrundbild des aktuellen Levels
    }

    private void drawPlayer(Graphics g) { // Methode zum Zeichnen des Spielers
        g.drawImage(model.getPlayer().getImage(), model.getPlayerX(), model.getPlayerY(), null);
    }

    private void drawSpells(Graphics g) {
        for (Spells spell : model.getSpells()) { // Iteriert durch alle Zaubersprüche im Modell
            g.drawImage(spell.getImage(), spell.getX(), spell.getY(), null);
        }
    }

    private void drawEnemies(Graphics g) {
        for (Enemies enemy : model.getEnemies()) { // Iteriert durch alle Gegner im Modell
            g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
        }
    }
}