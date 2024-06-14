package view;

import interfaces.Redirector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Multiplayer extends JPanel {

    private Image backgroundImage; // Hintergrundbild
    private JButton playButton, controllButton, backButton; // Schaltflächen für die Benutzeroberfläche
    private Redirector redirector; // Referenz auf den Redirector zur Navigation

    // Konstruktor
    public Multiplayer(Redirector redirector) {
        this.redirector = redirector;
        setPreferredSize(new Dimension(1280, 760)); // Setzt die bevorzugte Größe des Panels
        loadImages(); // Lädt die Bilder
        initButtons(); // Initialisiert die Schaltflächen
        setLayout(null); // Deaktiviert das Layout-Manager für manuelle Positionierung
    }

    private void initButtons() { // Methode zur Initialisierung der Schaltflächen
        // Initialisierung der Play-Schaltfläche
        playButton = new JButton(new ImageIcon("src/resources/menu/button_play.png"));
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);
        playButton.setBounds(100, 440, 180, 50); // Setzt die Position und Größe der Schaltfläche
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.startMultiplayerGame(); // Startet das Multiplayer-Spiel bei Klick
            }
        });
        add(playButton); // Fügt die Schaltfläche dem Panel hinzu

        // Initialisierung der Steuerungs-Schaltfläche
        controllButton = new JButton(new ImageIcon("src/resources/menu/button_controll.png"));
        controllButton.setContentAreaFilled(false);
        controllButton.setBorderPainted(false);
        controllButton.setFocusPainted(false);
        controllButton.setBounds(100, 500, 300, 50); // Setzt die Position und Größe der Schaltfläche
        controllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.controll(); // Zeigt die Steuerungsansicht bei Klick
            }
        });
        add(controllButton); // Fügt die Schaltfläche dem Panel hinzu

        // Initialisierung der Zurück-Schaltfläche
        backButton = new JButton(new ImageIcon("src/resources/menu/button_back.png"));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setBounds(100, 560, 180, 50); // Setzt die Position und Größe der Schaltfläche
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.menu(); // Geht zurück ins Menü bei Klick
            }
        });
        add(backButton); // Fügt die Schaltfläche dem Panel hinzu
    }

    @Override
    protected void paintComponent(Graphics g) { // Überschreibt die paintComponent-Methode von JPanel
        super.paintComponent(g);
        drawBackground(g); // Zeichnet den Hintergrund
    }

    private void loadImages() { // Methode zum Laden des Hintergrundbildes
        backgroundImage = new ImageIcon("src/resources/menu/background_clean.jpg").getImage(); // Lädt das Bild aus dem angegebenen Pfad
    }

    private void drawBackground(Graphics g) { // Methode zum Zeichnen des Hintergrundbildes
        g.drawImage(backgroundImage, 0, 0, null); // Zeichnet das Bild an den Koordinaten (0, 0)
    }
}