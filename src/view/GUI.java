package view;

import model.MainModel;
import model.Spells;
import model.Enemies;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class GUI extends JPanel {
    private Image playerImage;
    private Image spellImage;
    private Image[] enemyImages = new Image[7];
    private ArrayList<Image> imageForEnemy;
    private Image[] backgroundImages = new Image[4];

    private final MainModel model;


    public GUI(MainModel model) {
        this.model = model;

        setPreferredSize(new Dimension(1280, 760));
        setFocusable(true);
        requestFocusInWindow();

        loadImages();
        this.imageForEnemy = getEnemyImage();
    }

    private void loadImages() {
        playerImage = new ImageIcon("src/resources/game/player.png").getImage();
        spellImage = new ImageIcon("src/resources/game/spell.png").getImage();

        for (int i = 0; i <= 6; i++) {
            enemyImages[i] = new ImageIcon("src/resources/game/enemies/enemy" + i + ".png").getImage();
        }

        for (int i = 0; i <= 3; i++) {
            backgroundImages[i] = new ImageIcon("src/resources/game/backgrounds/level" + i + ".jpg").getImage();
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

    private ArrayList<Image> getEnemyImage() {
        ArrayList<Image> imageForEnemy = new ArrayList<>();
        for (int i = 0; i <= enemyImages.length; i++) {
            imageForEnemy.add(enemyImages[(int) (Math.random() * enemyImages.length)]);
        }
        return  imageForEnemy;
    }

    private void drawEnemies(Graphics g) {
        int index = 0;

        if (!(model.getEnemies().isEmpty())) {
            for (Enemies enemy : model.getEnemies()) {
                g.drawImage(imageForEnemy.get(index), enemy.getX(), enemy.getY(), null); //funktioniert so halb, wenn gegner stirb, muss bild aus liste entfernt werden
                index++;
            }
        }

    }
}
