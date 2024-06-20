package view;

import javax.swing.*;
import java.awt.*;

public class Story extends JPanel {

    private Image storyImage; // Bild für die Story-Anzeige

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        loadImages();
        drawStory(g);
    }

    private void loadImages() {
        storyImage = new ImageIcon("src/resources/game/story.jpg").getImage();
    }

    private void drawStory(Graphics g) {
        g.drawImage(storyImage, 0, 0, null);
    }
}