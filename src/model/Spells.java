package model;

public class Spells {

    public String name;
    public String type;
    public int damage;
    public int speed;
    public int x;
    public int y;

//    public Spells(String name, String type, int damage, int speed, int x, int y) { // für mehrere Typen
//        this.name = name;
//        this.type = type;
//        this.damage = damage;
//        this.speed = speed;
//        this.x = x;
//        this.y = y;
//    }

    public Spells(String name, int damage, int speed, int x, int y) { // solange wir nur einen Typ haben
        this.name = name;
        this.type = "normal";
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public void move() {
        this.x = x + speed; // Bewegt sich von Links nach Rechts
    }
}
