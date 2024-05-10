package model;

import java.awt.*;

public class Enemies {
    private Image image;
    private int health;
    private int speed;
    private int x;
    private int y;
    private static final int HEIGHT = 90;
    private static final int WIDTH = 128;

    public Enemies(Image image, int health, int speed, int x, int y) {
        this.image = image;
        this.health = health;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public int getSpeed() {
        return speed;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void move() {
        this.x -= speed;// Bewegt sich von Rechts nach Links
    }

    public boolean isOffScreen() {
        return x < 0; //Überprüfen, ob Gegner am Ende ist
    }

}
