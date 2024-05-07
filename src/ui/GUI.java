package ui;

import model.MainModel;
import model.Spells;
import model.Enemies;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.ImageIcon;

public class GUI extends JPanel {
    private Image playerImage;
    private Image spellImage;
    private Image enemyImage;

    private final MainModel model;



    public GUI(MainModel model) {
        this.model = model;

        setPreferredSize(new Dimension(1280, 720));

        loadImages();
    }

    private void loadImages() {
        playerImage = new ImageIcon("src/resources/player.png").getImage();
        spellImage = new ImageIcon("src/resources/spell.png").getImage();
        enemyImage = new ImageIcon("src/resources/enemy.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) { // Spieler zeichnen
        super.paintComponent(g);
        drawPlayer(g);
        drawSpells(g);
        drawEnemies(g);
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(playerImage, model.getPlayerX(), model.getPlayerY(), null);
    }

    private void drawSpells(Graphics g) {
        for (Spells spell : model.getSpells()) {
            g.drawImage(spellImage, spell.getX(), spell.getY(), null);
        }
    }

    private void drawEnemies(Graphics g) {
        if (!(model.getEnemies().isEmpty())) {
            for (Enemies enemy : model.getEnemies()) {
                g.drawImage(enemyImage, enemy.getX(), enemy.getY(), null);
            }
        }

    }
}
