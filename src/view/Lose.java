package view;

import javax.swing.*;
import java.awt.*;

public class Lose extends JPanel{

    private Image backgroundImage;
    private JFrame frame;

    public Lose(JFrame frame) {
        this.frame = frame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
    }

    private void loadImages() {
        backgroundImage = new ImageIcon("src/resources/menu/background.jpg").getImage();
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }


}
