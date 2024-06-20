package view;

import javax.swing.*;
import java.awt.*;

public class Lose extends JPanel {

    private Image loseScreen;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        loadImages();
        drawBackground(g);
    }

    private void loadImages() {
        loseScreen = new ImageIcon("src/resources/game/ending/lose.jpg").getImage();
    }

    private void drawBackground(Graphics g) {
        g.drawImage(loseScreen, 0, 0, null);
    }
}