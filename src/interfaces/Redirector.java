package interfaces;

public interface Redirector {
    void startGame(String name); // Methode zum Starten des Spiels mit einem angegebenen Namen
    void menu(); // Methode zum Zurückkehren ins Hauptmenü
    void win(); // Methode zum Anzeigen des Gewinn-Bildschirms
    void lose(); // Methode zum Anzeigen des Verlierer-Bildschirms
    void startMultiplayerGame(); // Methode zum Starten eines Multiplayer-Spiels
    void multiplayer(); // Methode zum Anzeigen des Multiplayer-Menüs
    void controll(); // Methode zum Anzeigen der Steuerungsansicht
}