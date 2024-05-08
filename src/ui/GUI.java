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
    private Image backgroundImage;

    private final MainModel model;



    public GUI(MainModel model) {
        this.model = model;

        setPreferredSize(new Dimension(1280, 720));

        loadImages();
    }

    private void loadImages() {
        playerImage = new ImageIcon("src/resources/game/player.png").getImage();
        spellImage = new ImageIcon("src/resources/game/spell.png").getImage();
        enemyImage = new ImageIcon("src/resources/game/enemy.png").getImage();
//        backgroundImage = new ImageIcon("src/resources/game/background.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) { // Spieler zeichnen
        super.paintComponent(g);
//        drawBackground(g); // Bild fehlt noch
        drawPlayer(g);
        drawSpells(g);
        drawEnemies(g); // evtl Liste mit verschiedenen Gegner Bildern, dann random choice
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
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
