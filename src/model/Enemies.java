package model;

import java.awt.*;

public class Enemies {
    private Image image; // Bild des Gegners
    private int health; // Lebenspunkte des Gegners
    private int speed; // Geschwindigkeit des Gegners
    private int x; // X-Position des Gegners
    private int y; // Y-Position des Gegners
    private static final int HEIGHT = 90; // Höhe des Gegners
    private static final int WIDTH = 128; // Breite des Gegners

    public Enemies(Image image, int health, int speed, int x, int y) { // Konstruktor
        this.image = image; // Initialisiert das Bild
        this.health = health; // Initialisiert die Lebenspunkte
        this.speed = speed; // Initialisiert die Geschwindigkeit
        this.x = x; // Initialisiert die X-Position
        this.y = y; // Initialisiert die Y-Position
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public boolean isAlive() { // Überprüft, ob der Gegner noch lebt
        return health > 0;
    }

    public void takeDamage(int damage) { // Reduziert die Lebenspunkte des Gegners
        health -= damage;
    }

    public void move() {
        this.x -= speed; // Bewegt sich von rechts nach links
    }

    public boolean isOffScreen() {
        return x < 0; // Der Gegner ist außerhalb des Bildschirms, wenn die X-Position kleiner als 0 ist
    }
}