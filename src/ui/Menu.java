package ui;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    private Image backgroundImage;

    public Menu() {
        setPreferredSize(new Dimension(1280, 760));
        loadImages();
    }

    @Override
    protected void paintComponent(Graphics g) { // Spieler zeichnen
        super.paintComponent(g);
        drawBackground(g); // Bild muss noch getauscht werden
    }

    private void loadImages() {
        backgroundImage = new ImageIcon("src/resources/menu/menu.png").getImage();
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
