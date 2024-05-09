package view;

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
    private Image[] backgroundImages = new Image[4];

    private final MainModel model;



    public GUI(MainModel model) {
        this.model = model;

        setPreferredSize(new Dimension(1280, 760));
        setFocusable(true);
        requestFocusInWindow();

        loadImages();
    }

    private void loadImages() {
        playerImage = new ImageIcon("src/resources/game/player.png").getImage();
        spellImage = new ImageIcon("src/resources/game/spell.jpg").getImage();
        enemyImage = new ImageIcon("src/resources/game/enemy.png").getImage();
        for (int i = 0; i <= 3; i++) {
            backgroundImages[i] = new ImageIcon("src/resources/game/level" + i + ".jpg").getImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) { // Spieler zeichnen
        super.paintComponent(g);
        drawBackground(g);
        drawPlayer(g);
        drawSpells(g);
        drawEnemies(g); // evtl Liste mit verschiedenen Gegner Bildern, dann random choice
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImages[model.getCurrentLevel()+1], 0, 0, null);
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
