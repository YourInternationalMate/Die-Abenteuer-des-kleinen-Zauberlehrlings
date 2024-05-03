package model;

public class Enemies {
    private int health;
    private int speed;
    private int x;
    private int y;

    public Enemies(int health, int speed, int x, int y) {
        this.health = health;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public int getHealth() {
        return health;
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

    public void setHealth(int health) {
        this.health = health;
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
        this.x = x - speed; // Bewegt sich von Rechts nach Links
    }

    public boolean isOffScreen() {
        return x < 0; //Überprüfen, ob Gegner am Ende ist, dann lose, Y-Offset muss noch an Gegnergröße angepasst werden
    }

}
