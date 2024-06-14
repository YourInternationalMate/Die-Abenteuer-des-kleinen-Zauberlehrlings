package view;

import javax.swing.*;
import java.awt.*;

public class Win extends JPanel {

    private Image winScreen; // Bild für den Gewinn-Bildschirm

    @Override
    protected void paintComponent(Graphics g) { // Überschreibt die paintComponent-Methode von JPanel
        super.paintComponent(g);
        loadImages(); // Lädt das Gewinn-Bild
        drawBackground(g); // Zeichnet den Hintergrund
    }

    private void loadImages() { // Methode zum Laden des Gewinn-Bildes
        winScreen = new ImageIcon("src/resources/game/ending/win.jpg").getImage(); // Lädt das Bild aus dem angegebenen Pfad
    }

    private void drawBackground(Graphics g) { // Methode zum Zeichnen des Hintergrundbildes
        g.drawImage(winScreen, 0, 0, null); // Zeichnet das Bild an den Koordinaten (0, 0)
    }
}