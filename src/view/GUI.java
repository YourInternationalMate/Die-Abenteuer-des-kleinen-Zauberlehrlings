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
    protected void paintComponent(Graphics g) { // Spieler zeichnen
        super.paintComponent(g);
        drawBackground(g);
        drawPlayer(g);
        drawSpells(g);
        drawEnemies(g);
    }

    private void drawBackground(Graphics g) {
        g.drawImage(model.getLevel().getImage(), 0, 0, null);
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(model.getPlayer().getImage(), model.getPlayerX(), model.getPlayerY(), null);
    }

    private void drawSpells(Graphics g) {
        for (Spells spell : model.getSpells()) {
            g.drawImage(spell.getImage(), spell.getX(), spell.getY(), null);
        }
    }

    private void drawEnemies(Graphics g) {
        for (Enemies enemy : model.getEnemies()) {
            g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null); //funktioniert so halb, wenn gegner stirb, muss bild aus liste entfernt werden
        }
    }
}
