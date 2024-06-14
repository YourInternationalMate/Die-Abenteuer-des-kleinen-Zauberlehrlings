package view;

import javax.swing.*;
import java.awt.*;

public class Story extends JPanel {

    private Image storyImage; // Bild für die Story-Anzeige

    @Override
    protected void paintComponent(Graphics g) { // Überschreibt die paintComponent-Methode von JPanel
        super.paintComponent(g);
        loadImages(); // Lädt das Story-Bild
        drawStory(g); // Zeichnet die Story
    }

    private void loadImages() { // Methode zum Laden des Story-Bildes
        storyImage = new ImageIcon("src/resources/game/story.jpg").getImage(); // Lädt das Bild aus dem angegebenen Pfad
    }

    private void drawStory(Graphics g) { // Methode zum Zeichnen der Story
        g.drawImage(storyImage, 0, 0, null); // Zeichnet das Bild an den Koordinaten (0, 0)
    }
}