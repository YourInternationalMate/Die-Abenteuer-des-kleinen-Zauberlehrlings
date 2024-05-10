package model;

import java.awt.*;

public class Spells {
    private Image image;
    private String type;
    private int damage;
    private int speed;
    private int x;
    private int y;

    private static final int WIDTH = 20;
    private static final int HEIGHT = 10;


    public Spells(Image image, int damage, int speed, int x, int y) {// solange wir nur einen Typ haben
        this.image = image;
        this.type = "normal";
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
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

    public int getDamage() {
        return damage;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public void move() {
        this.x = x + speed; // Bewegt sich von Links nach Rechts
    }
}
