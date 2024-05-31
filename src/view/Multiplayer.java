package view;

import interfaces.Redirector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Multiplayer extends JPanel {

    private Image backgroundImage;
    private JButton playButton, controllButton;
    private JTextField ipField;
    private Redirector redirector;

    private static final String DEFAULT_TEXT = "IP-Adress";

    public Multiplayer(Redirector redirector) {
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

        playButton.setBounds(100, 500, 180, 50);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.connectToStream(ipField.getText());
                redirector.startMultiplayerGame();
            }
        });
        add(playButton);

        controllButton = new JButton(new ImageIcon("src/resources/menu/button_controll.png"));
        controllButton.setContentAreaFilled(false);
        controllButton.setBorderPainted(false);
        controllButton.setFocusPainted(false);

        controllButton.setBounds(100, 560, 300, 50);
        controllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.controll();
            }
        });
        add(controllButton);
    }

    private void initTextFields() {
        ipField = new JTextField(DEFAULT_TEXT);
        ipField.setBounds(520, 600, 220, 50);
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
