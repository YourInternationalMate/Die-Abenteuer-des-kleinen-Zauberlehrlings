package view;

import model.MainModel;
import model.Spells;
import model.Enemies;

import javax.swing.JPanel;
import java.awt.*;

public class GUI extends JPanel {
    private final MainModel model; // Referenz auf das MainModel, das die Spiel-Daten enthält

    public GUI(MainModel model) { // Konstruktor
        this.model = model;

        setPreferredSize(new Dimension(1280, 760)); // Setzt die bevorzugte Größe des Panels
        setFocusable(true); // Macht das Panel fokussierbar
        requestFocusInWindow(); // Fordert den Fokus für das Panel an
    }

    @Override
    protected void paintComponent(Graphics g) { // Überschreibt die paintComponent-Methode von JPanel
        super.paintComponent(g);
        drawBackground(g); // Zeichnet den Hintergrund
        drawPlayer(g); // Zeichnet den Spieler
        drawSpells(g); // Zeichnet die Zaubersprüche
        drawEnemies(g); // Zeichnet die Gegner
    }

    private void drawBackground(Graphics g) { // Methode zum Zeichnen des Hintergrundes
        g.drawImage(model.getLevel().getImage(), 0, 0, null); // Zeichnet das Hintergrundbild des aktuellen Levels
    }

    private void drawPlayer(Graphics g) { // Methode zum Zeichnen des Spielers
        g.drawImage(model.getPlayer().getImage(), model.getPlayerX(), model.getPlayerY(), null); // Zeichnet das Spielerbild an den aktuellen Koordinaten
    }

    private void drawSpells(Graphics g) { // Methode zum Zeichnen der Zaubersprüche
        for (Spells spell : model.getSpells()) { // Iteriert durch alle Zaubersprüche im Modell
            g.drawImage(spell.getImage(), spell.getX(), spell.getY(), null); // Zeichnet jedes Zauberspruch-Bild an den aktuellen Koordinaten
        }
    }

    private void drawEnemies(Graphics g) { // Methode zum Zeichnen der Gegner
        for (Enemies enemy : model.getEnemies()) { // Iteriert durch alle Gegner im Modell
            g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null); // Zeichnet jedes Gegner-Bild an den aktuellen Koordinaten
        }
    }
}