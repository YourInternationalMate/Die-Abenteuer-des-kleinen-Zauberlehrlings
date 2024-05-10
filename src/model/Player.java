package model;

import java.awt.*;

public class Player {
    private Image image;
    private int speed;
    private int x;
    private int y;

    public Player(Image image, int speed, int x, int y) {
        this.image = image;
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
        y = 270;
    }

    public void moveUp() {
        if (y > 0) {
            this.y = y - speed;
        }
    }

    public void moveDown() {
        if (y + 90 < 720) {
            this.y = y + speed;
        }
    }
}
