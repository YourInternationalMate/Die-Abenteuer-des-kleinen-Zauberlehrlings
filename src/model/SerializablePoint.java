package model;

import java.io.Serializable;
import java.awt.Point;

public class SerializablePoint extends Point implements Serializable { // Erbt von Point und implementiert Serializable
    private static final long serialVersionUID = 1L;

    public SerializablePoint(int x, int y) { // Konstruktor
        super(x, y); // Ruft den Konstruktor der Basisklasse Point auf
    }
}