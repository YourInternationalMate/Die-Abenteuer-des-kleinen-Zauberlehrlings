package view;

import Server.GameClient;
import interfaces.Redirector;
import model.SerializablePoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class Control extends JPanel {

    private Image backgroundImage; // Hintergrundbild
    private JButton[] buttons; // Schaltflächen für die Platzierung der Gegner
    private JButton placeButton; // Schaltfläche zum Bestätigen der Platzierungen
    private JTextField ipField; // Textfeld für die Eingabe der IP-Adresse
    private static final int BUTTON_COUNT = 16; // Anzahl der Schaltflächen
    private static final int MAX_BUTTON_CLICKS = 4; // Maximale Anzahl der Platzierungen
    private ArrayList<SerializablePoint> clickedButtonValues; // Liste der angeklickten Schaltflächen
    private ArrayList<Point> possiblePositions; // Mögliche Positionen für die Schaltflächen
    private Redirector redirector; // Referenz auf den Redirector zur Navigation

    private static final String DEFAULT_TEXT = "IP-Adress"; // Standardtext für das IP-Feld

    public Control(ArrayList<Point> possiblePositions, Redirector redirector) { // Konstruktor
        this.possiblePositions = possiblePositions;
        this.redirector = redirector;
        setPreferredSize(new Dimension(1280, 760)); // Setzt die bevorzugte Größe des Panels
        loadImages();
        initButtons();
        initTextFields();
        setLayout(null); // Deaktiviert das Layout-Manager für manuelle Positionierung
    }

    private void initButtons() {
        JButton backButton;
        buttons = new JButton[BUTTON_COUNT]; // Initialisiert das Array der Schaltflächen
        clickedButtonValues = new ArrayList<>();

        for (int i = 0; i < BUTTON_COUNT; i++) {
            int index = i;
            buttons[index] = new JButton("Button " + possiblePositions.get(index), new ImageIcon("src/resources/controll/button_enemy.png"));
            buttons[index].setContentAreaFilled(false);
            buttons[index].setBorderPainted(false);
            buttons[index].setFocusPainted(false);
            buttons[index].setBounds(100 + (index % 4) * 150, 100 + (index / 4) * 100, 130, 35); // Setzt die Position und Größe der Schaltfläche
            buttons[index].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (clickedButtonValues.size() >= MAX_BUTTON_CLICKS) {
                    } else {
                        JButton source = (JButton) e.getSource();
                        source.setEnabled(false); // Deaktiviert die Schaltfläche nach dem Klick
                        clickedButtonValues.add(new SerializablePoint(possiblePositions.get(index).x, possiblePositions.get(index).y)); // Fügt die Position zur Liste hinzu
                    }
                }
            });
            add(buttons[index]);
        }

        // Initialisierung der Place-Schaltfläche
        placeButton = new JButton(new ImageIcon("src/resources/controll/button_place.png"));
        placeButton.setContentAreaFilled(false);
        placeButton.setBorderPainted(false);
        placeButton.setFocusPainted(false);
        placeButton.setBounds(600, 600, 180, 50);
        placeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    GameClient client = new GameClient(clickedButtonValues); // Erstellt einen neuen GameClient
                    client.sendPoints(ipField.getText()); // Sendet die Punkte an die angegebene IP-Adresse
                }).start();
            }
        });
        add(placeButton); // Fügt die Schaltfläche dem Panel hinzu


        backButton = new JButton(new ImageIcon("src/resources/menu/button_back.png"));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setBounds(100, 560, 180, 50); // Setzt die Position und Größe der Schaltfläche
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.multiplayer(); // Kehrt zur Multiplayer-Ansicht zurück
            }
        });
        add(backButton); // Fügt die Schaltfläche dem Panel hinzu
    }

    private void initTextFields() {
        ipField = new JTextField(DEFAULT_TEXT); // Initialisiert das Textfeld mit dem Standardtext
        ipField.setBounds(560, 650, 220, 50);
        ipField.setHorizontalAlignment(JTextField.CENTER);
        ipField.setForeground(Color.WHITE);
        ipField.setFont(new Font("SansSerif", Font.BOLD, 24));
        ipField.setOpaque(false); // Macht das Textfeld transparent
        ipField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // Setzt einen weißen Rand
        ipField.setCaretColor(Color.WHITE);
        ipField.addFocusListener(new FocusAdapter() { // Fügt einen Fokus-Listener hinzu
            @Override
            public void focusGained(FocusEvent e) {
                if (ipField.getText().equals(DEFAULT_TEXT)) {
                    ipField.setText(""); // Leert das Textfeld bei Fokus, wenn der Standardtext vorhanden ist
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ipField.getText().isEmpty()) {
                    ipField.setText(DEFAULT_TEXT); // Setzt den Standardtext, wenn das Textfeld leer ist
                }
            }
        });
        add(ipField);
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