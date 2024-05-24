package view;

import interfaces.Redirector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Watch extends JPanel {

    private Image backgroundImage;
    private JButton watchButton, backButton;
    private JTextField adressField;
    private Redirector redirector;

    private static final String DEFAULT_TEXT = "IP-Adress";

    public Watch(Redirector redirector) {
        this.redirector = redirector;
        setPreferredSize(new Dimension(1280, 760));
        loadImages();
        initButtons();
        initTextFields();
        setLayout(null); // Layout deaktivieren -> manuelle Positionierung
    }

    private void initButtons() {
        backButton = new JButton(new ImageIcon("src/resources/menu/button_back.png"));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);

        backButton.setBounds(100, 560, 180, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirector.menu();
            }
        });
        add(backButton);

        watchButton = new JButton(new ImageIcon("src/resources/menu/button_watch.png"));
        watchButton.setContentAreaFilled(false);
        watchButton.setBorderPainted(false);
        watchButton.setFocusPainted(false);

        watchButton.setBounds(100, 500, 180, 50);
        watchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(adressField.getText());
                redirector.connectToStream(adressField.getText());
            }
        });
        add(watchButton);
    }

    private void initTextFields() {
        adressField = new JTextField(DEFAULT_TEXT);
        adressField.setBounds(520, 600, 220, 50);
        adressField.setHorizontalAlignment(JTextField.CENTER);
        adressField.setForeground(Color.WHITE);
        adressField.setFont(new Font("SansSerif", Font.BOLD, 24));
        adressField.setOpaque(false);
        adressField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        adressField.setCaretColor(Color.WHITE);
        adressField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (adressField.getText().equals(DEFAULT_TEXT)) {
                    adressField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (adressField.getText().isEmpty()) {
                    adressField.setText(DEFAULT_TEXT);
                }
            }
        });
        add(adressField);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
    }

    private void loadImages() {
        backgroundImage = new ImageIcon("src/resources/menu/background_watch.jpg").getImage();
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
