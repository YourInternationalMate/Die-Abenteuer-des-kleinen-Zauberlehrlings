package interfaces;

import java.awt.*;
import java.util.ArrayList;

public interface Redirector {
    void startGame(String name);
    void menu();
    void win();
    void lose();
    void startMultiplayerGame();
    void multiplayer();
    void controll();
    void connectToStream(String IP);
    void startStream(ArrayList<Point> clickedButtonValues);
}
