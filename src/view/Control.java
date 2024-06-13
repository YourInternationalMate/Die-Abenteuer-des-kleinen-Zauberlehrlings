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

    private Image backgroundImage;
    private JButton[] buttons;
    private JButton placeButton;
    private JTextField ipField;
    private static final int BUTTON_COUNT = 16;
    private static final int MAX_BUTTON_CLICKS = 5; // Anzahl der zu platzierenden Gegner
    private ArrayList<SerializablePoint> clickedButtonValues;
    private ArrayList<Point> possiblePositions;
    private Redirector redirector;

    private static final String DEFAULT_TEXT = "IP-Adress";

    public Control(ArrayList<Point> possiblePositions, Redirector redirector) {
        this.possiblePositions = possiblePositions;
        this.redirector = redirector;
        setPreferredSize(new Dimension(1280, 760));
        loadImages();
        initButtons();

        initTextFields();
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
                        clickedButtonValues.add(new SerializablePoint(possiblePositions.get(index).x, possiblePositions.get(index).y));
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
                new Thread(() -> {
                    GameClient client = new GameClient(clickedButtonValues);
                    client.sendPoints(ipField.getText());
                }).start();
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

    private void initTextFields() {
        ipField = new JTextField(DEFAULT_TEXT);
        ipField.setBounds(560, 650, 220, 50);
        ipField.setHorizontalAlignment(JTextField.CENTER);
        ipField.setForeground(Color.WHITE);
        ipField.setFont(new Font("SansSerif", Font.BOLD, 24));
        ipField.setOpaque(false);
        ipField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        ipField.setCaretColor(Color.WHITE);
        ipField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ipField.getText().equals(DEFAULT_TEXT)) {
                    ipField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ipField.getText().isEmpty()) {
                    ipField.setText(DEFAULT_TEXT);
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