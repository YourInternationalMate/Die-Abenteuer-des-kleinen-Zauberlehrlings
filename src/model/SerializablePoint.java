package model;

import java.io.Serializable;
import java.awt.Point;

public class SerializablePoint extends Point implements Serializable {
    private static final long serialVersionUID = 1L;

    public SerializablePoint(int x, int y) {
        super(x, y);
    }
}