package model;

public class Player {

    private int speed;
    private int x;
    private int y;

    public Player( int speed, int x, int y) {
        this.speed = speed;
        this.x = x;
        this.y = y;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void reset() { // Start Position muss noch angepasst werden
        x = 0;
        y = 0;
    }

    public void moveUp() {
        this.y = y + speed;
    }

    public void moveDown() {
        this.y = y - speed;
    }
}
