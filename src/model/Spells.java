package model;

import java.awt.*;

public class Spells {
    private Image image; // Bild für den Zauber
    private String type; // Typ des Zaubers
    private int damage; // Schaden des Zaubers
    private int speed; // Geschwindigkeit des Zaubers
    private int x; // X-Position des Zaubers
    private int y; // Y-Position des Zaubers

    private static final int WIDTH = 20; // Breite des Zaubers
    private static final int HEIGHT = 10; // Höhe des Zaubers

    public Spells(Image image, int damage, int speed, int x, int y) { // Konstruktor
        this.image = image; // Initialisiert das Bild
        this.type = "normal"; // Setzt den Typ des Zaubers auf "normal"
        this.damage = damage; // Initialisiert den Schaden
        this.speed = speed; // Initialisiert die Geschwindigkeit
        this.x = x; // Initialisiert die X-Position
        this.y = y; // Initialisiert die Y-Position
    }

    public Image getImage() { // Getter für das Bild
        return image;
    }

    public int getX() { // Getter für die X-Position
        return x;
    }

    public int getY() { // Getter für die Y-Position
        return y;
    }

    public int getDamage() { // Getter für den Schaden
        return damage;
    }

    public static int getWIDTH() { // Getter für die Breite
        return WIDTH;
    }

    public static int getHEIGHT() { // Getter für die Höhe
        return HEIGHT;
    }

    public void move() { // Methode zum Bewegen des Zaubers
        this.x = x + speed; // Bewegt den Zauber nach rechts um die Geschwindigkeit
    }
}