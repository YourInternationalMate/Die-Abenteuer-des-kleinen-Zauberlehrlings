package view;

import javax.swing.*;
import java.awt.*;

public class Win extends JPanel{

    private Image winScreen;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        loadImages();
        drawBackground(g);
    }

    private void loadImages() {
        winScreen = new ImageIcon("src/resources/game/ending/win.jpg").getImage();
    }

    private void drawBackground(Graphics g) {
        g.drawImage(winScreen, 0, 0, null);
    }


}
