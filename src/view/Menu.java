package view;

import interfaces.Redirector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Menu extends JPanel {

    private Image backgroundImage;
    private JButton playButton, exitButton, multiplayerButton;
    private JTextField nameField;
    private Redirector redirector;

    private static final String DEFAULT_TEXT = "Username";

    public Menu(Redirector redirector) {
        this.redirector = redirector;
        setPreferredSize(new Dimension(1280, 760));
        loadImages();
        initButtons();
        initTextFields();
        setLayout(null); // Layout deaktivieren -> manuelle Positionierung
    }

    private void initButtons() {
        playButton = new JButton(new ImageIcon("src/resources/menu/button_play.png"));
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);

        playButton.setBounds(100, 440, 180, 50);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.startGame(nameField.getText());
            }
        });
        add(playButton);

        multiplayerButton = new JButton(new ImageIcon("src/resources/menu/button_multiplayer.png"));
        multiplayerButton.setContentAreaFilled(false);
        multiplayerButton.setBorderPainted(false);
        multiplayerButton.setFocusPainted(false);

        multiplayerButton.setBounds(100, 500, 300, 50);
        multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.multiplayer();
            }
        });
        add(multiplayerButton);

        exitButton = new JButton(new ImageIcon("src/resources/menu/button_exit.png"));
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);

        exitButton.setBounds(100, 560, 180, 50);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Programm beenden
            }
        });
        add(exitButton);
    }

    private void initTextFields() {
        nameField = new JTextField(DEFAULT_TEXT);
        nameField.setBounds(520, 600, 220, 50);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setForeground(Color.WHITE);
        nameField.setFont(new Font("SansSerif", Font.BOLD, 24));
        nameField.setOpaque(false);
        nameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        nameField.setCaretColor(Color.WHITE);
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals(DEFAULT_TEXT)) {
                    nameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText(DEFAULT_TEXT);
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
