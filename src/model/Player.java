package model;

import java.awt.*;

public class Player {
    private Image image; // Bild des Spielers
    private int speed; // Geschwindigkeit des Spielers
    private int x; // X-Position des Spielers
    private int y; // Y-Position des Spielers

    public Player(Image image, int speed, int x, int y) { // Konstruktor
        this.image = image; // Initialisiert das Bild
        this.speed = speed; // Initialisiert die Geschwindigkeit
        this.x = x; // Initialisiert die X-Position
        this.y = y; // Initialisiert die Y-Position
    }

    public Image getImage() { // Getter für das Bild
        return image;
    }

    public int getSpeed() { // Getter für die Geschwindigkeit
        return speed;
    }

    public int getX() { // Getter für die X-Position
        return x;
    }

    public int getY() { // Getter für die Y-Position
        return y;
    }

    public void setSpeed(int speed) { // Setter für die Geschwindigkeit
        this.speed = speed;
    }

    public void setX(int x) { // Setter für die X-Position
        this.x = x;
    }

    public void setY(int y) { // Setter für die Y-Position
        this.y = y;
    }

    public void reset() { // Methode zum Zurücksetzen der Position des Spielers
        x = 0;
        y = 270; // Setzt die Y-Position auf 270 (Startposition)
    }

    public void moveUp() { // Methode zum Bewegen des Spielers nach oben
        if (y > 0) { // Überprüft, ob der Spieler sich innerhalb des oberen Randes des Spielfelds befindet
            this.y = y - speed; // Bewegt den Spieler nach oben um die Geschwindigkeit
        }
    }

    public void moveDown() { // Methode zum Bewegen des Spielers nach unten
        if (y + 90 < 720) { // Überprüft, ob der Spieler sich innerhalb des unteren Randes des Spielfelds befindet (unter Berücksichtigung der Spielerhöhe)
            this.y = y + speed; // Bewegt den Spieler nach unten um die Geschwindigkeit
        }
    }
}