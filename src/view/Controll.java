package view;

import interfaces.Redirector;
import Server.GameServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controll extends JPanel {

    private Image backgroundImage;
    private JButton[] buttons;
    private JButton placeButton;
    private static final int BUTTON_COUNT = 16;
    private static final int MAX_BUTTON_CLICKS = 5; // Anzahl der zu platzierenden Gegner
    private ArrayList<Point> clickedButtonValues;
    private ArrayList<Point> possiblePositions;
    private Redirector redirector;
    private GameServer server;

    public Controll(ArrayList<Point> possiblePositions, Redirector redirector, GameServer server) {
        this.possiblePositions = possiblePositions;
        this.redirector = redirector;
        this.server = server;
        setPreferredSize(new Dimension(1280, 760));
        loadImages();
        initButtons();
        setLayout(null); // Layout deaktivieren -> manuelle Positionierung
    }

    private void initButtons() {
        JButton backButton;
        buttons = new JButton[BUTTON_COUNT];
        clickedButtonValues = new ArrayList<>();

        for (int i = 0; i < BUTTON_COUNT; i++) {
            int index = i;
            buttons[index] = new JButton("Button " + possiblePositions.get(index), new ImageIcon("src/resources/controll/button_enemy.png"));
            buttons[index].setContentAreaFilled(false);
            buttons[index].setBorderPainted(false);
            buttons[index].setFocusPainted(false);
            buttons[index].setBounds(100 + (index % 4) * 150, 100 + (index / 4) * 100, 130, 35);
            buttons[index].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (clickedButtonValues.size() >= MAX_BUTTON_CLICKS) {
                        System.out.println("Das waren alle");
                    } else {
                        JButton source = (JButton) e.getSource();
                        source.setEnabled(false);
                        clickedButtonValues.add(possiblePositions.get(index));
                    }
                }
            });
            add(buttons[index]);
        }

        placeButton = new JButton(new ImageIcon("src/resources/controll/button_place.png"));
        placeButton.setContentAreaFilled(false);
        placeButton.setBorderPainted(false);
        placeButton.setFocusPainted(false);
        placeButton.setBounds(600, 600, 180, 50);
        placeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.setClickedButtonValues(clickedButtonValues);
            }
        });
        add(placeButton);

        backButton = new JButton(new ImageIcon("src/resources/menu/button_back.png"));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);

        backButton.setBounds(100, 560, 180, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.multiplayer();
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