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

    public Multiplayer(Redirector redirector) {
        this.redirector = redirector;
        setPreferredSize(new Dimension(1280, 760));
        loadImages();
        initButtons();
        setLayout(null); // Deaktiviert das Layout-Manager für manuelle Positionierung
    }

    private void initButtons() {
        // Initialisierung der Play-Schaltfläche
        playButton = new JButton(new ImageIcon("src/resources/menu/button_play.png"));
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);
        playButton.setBounds(100, 440, 180, 50);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.startMultiplayerGame(); // Startet das Multiplayer-Spiel bei Klick
            }
        });
        add(playButton);

        // Initialisierung der Steuerungs-Schaltfläche
        controllButton = new JButton(new ImageIcon("src/resources/menu/button_controll.png"));
        controllButton.setContentAreaFilled(false);
        controllButton.setBorderPainted(false);
        controllButton.setFocusPainted(false);
        controllButton.setBounds(100, 500, 300, 50);
        controllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.control(); // Zeigt die Steuerungsansicht bei Klick
            }
        });
        add(controllButton);

        // Initialisierung der Zurück-Schaltfläche
        backButton = new JButton(new ImageIcon("src/resources/menu/button_back.png"));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setBounds(100, 560, 180, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.menu(); // Geht zurück ins Menü bei Klick
            }
        });
        add(backButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
    }

    private void loadImages() {
        backgroundImage = new ImageIcon("src/resources/menu/background_clean.jpg").getImage();
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }
}