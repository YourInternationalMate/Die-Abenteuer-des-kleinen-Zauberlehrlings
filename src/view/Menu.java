package view;

import interfaces.Redirector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Menu extends JPanel {

    private Image backgroundImage; // Hintergrundbild für das Menü
    private JButton playButton, exitButton, multiplayerButton; // Schaltflächen im Menü
    private JTextField nameField; // Textfeld für den Spielernamen
    private Redirector redirector; // Referenz auf den Redirector zur Navigation

    private static final String DEFAULT_TEXT = "Username"; // Standardtext für das Textfeld

    public Menu(Redirector redirector) {
        this.redirector = redirector;
        setPreferredSize(new Dimension(1280, 760));
        loadImages();
        initButtons();
        initTextFields();
        setLayout(null);
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
                redirector.startGame(nameField.getText()); // Startet das Spiel mit dem eingegebenen Namen bei Klick
            }
        });
        add(playButton);

        // Initialisierung der Multiplayer-Schaltfläche
        multiplayerButton = new JButton(new ImageIcon("src/resources/menu/button_multiplayer.png"));
        multiplayerButton.setContentAreaFilled(false);
        multiplayerButton.setBorderPainted(false);
        multiplayerButton.setFocusPainted(false);
        multiplayerButton.setBounds(100, 500, 300, 50);
        multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.multiplayer(); // Zeigt die Multiplayer-Ansicht bei Klick
            }
        });
        add(multiplayerButton);

        // Initialisierung der Exit-Schaltfläche
        exitButton = new JButton(new ImageIcon("src/resources/menu/button_exit.png"));
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(100, 560, 180, 50);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Beendet das Programm bei Klick
            }
        });
        add(exitButton);
    }

    private void initTextFields() {
        nameField = new JTextField(DEFAULT_TEXT); // Initialisiert das Textfeld mit dem Standardtext
        nameField.setBounds(520, 600, 220, 50);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setForeground(Color.WHITE);
        nameField.setFont(new Font("SansSerif", Font.BOLD, 24));
        nameField.setOpaque(false); // Macht das Textfeld transparent
        nameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        nameField.setCaretColor(Color.WHITE);
        nameField.addFocusListener(new FocusAdapter() { // Fügt einen Fokus-Listener hinzu
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals(DEFAULT_TEXT)) {
                    nameField.setText(""); // Leert das Textfeld bei Fokus, wenn der Standardtext vorhanden ist
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText(DEFAULT_TEXT); // Setzt den Standardtext, wenn das Textfeld leer ist
                }
            }
        });
        add(nameField);
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