package view;

import javax.swing.*;
import java.awt.*;

public class Lose extends JPanel {

    private Image loseScreen; // Bild für den Verlierer-Bildschirm

    @Override
    protected void paintComponent(Graphics g) { // Überschreibt die paintComponent-Methode von JPanel
        super.paintComponent(g);
        loadImages(); // Lädt das Verlierer-Bild
        drawBackground(g); // Zeichnet den Hintergrund
    }

    private void loadImages() { // Methode zum Laden des Verlierer-Bildes
        loseScreen = new ImageIcon("src/resources/game/ending/lose.jpg").getImage(); // Lädt das Bild aus dem angegebenen Pfad
    }

    private void drawBackground(Graphics g) { // Methode zum Zeichnen des Hintergrundbildes
        g.drawImage(loseScreen, 0, 0, null); // Zeichnet das Bild an den Koordinaten (0, 0)
    }
}