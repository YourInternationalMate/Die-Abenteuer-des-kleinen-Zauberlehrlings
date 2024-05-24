package interfaces;

public interface Redirector {
    void startGame(String name);
    void menu();
    void win();
    void lose();
    void watch();
    void connectToStream(String IP);
}
