package model;

public class Spells {
    private String type;
    private int damage;
    private int speed;
    private int x;
    private int y;

    private static final int WIDTH = 30; // muss noch angepasst werden, richtige Werte
    private static final int HEIGHT = 20;


    public Spells(int damage, int speed, int x, int y) { // solange wir nur einen Typ haben
        this.type = "normal";
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
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
