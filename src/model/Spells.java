package model;

public class Spells {
    public String type;
    public int damage;
    public int speed;
    public int x;
    public int y;

//    public Spells(String type, int damage, int speed, int x, int y) { // für mehrere Typen
//        this.type = type;
//        this.damage = damage;
//        this.speed = speed;
//        this.x = x;
//        this.y = y;
//    }

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

    public void move() {
        this.x = x + speed; // Bewegt sich von Links nach Rechts
    }
}
