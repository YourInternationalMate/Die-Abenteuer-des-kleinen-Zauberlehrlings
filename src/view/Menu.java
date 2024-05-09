package view;

import interfaces.Redirector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    private Image backgroundImage;
    private JButton playButton, exitButton;
    private Redirector redirector;
    private JFrame frame;

    public Menu(JFrame frame, Redirector redirector) {
        this.redirector = redirector;
        this.frame = frame;
        setPreferredSize(new Dimension(1280, 760));
        loadImages();
        initButtons();
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
                redirector.startGame();
//                frame.dispose();
            }
        });
        add(playButton);

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