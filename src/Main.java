import Server.GameServer;
import view.Menu;
import view.GUI;
import view.Lose;
import view.Win;
import view.Story;
import view.Multiplayer;
import view.Control;
import controller.Controller;
import model.MainModel;
import interfaces.Redirector;

import javax.swing.*;

public class Main implements Redirector { // Hauptklasse, die das Redirector-Interface implementiert
    private static JFrame mainFrame; // Hauptfenster des Spiels
    private Menu menu; // Menü-Ansicht
    private Lose lose; // Verlierer-Ansicht
    private Win win; // Gewinner-Ansicht
    private Story story; // Story-Ansicht
    private GUI gui; // Spielansicht
    private Multiplayer multiplayer; // Multiplayer-Ansicht
    private Control controll; // Steuerungsansicht
    private Controller controller; // Controller für die Spiel-Logik
    private MainModel model; // Modell für die Spiel-Daten
    private GameServer server; // GameServer-Instanz für Multiplayer-Spiele
    private boolean serverStarted = false; // Flag, ob der Server gestartet wurde

    public Main() { // Konstruktor der Hauptklasse
        mainFrame = new JFrame("Die Abenteuer des kleinen Zauberlehrlings"); // Initialisiert das Hauptfenster
        menu = new Menu(this); // Initialisiert das Menü
        lose = new Lose(); // Initialisiert die Verlierer-Ansicht
        win = new Win(); // Initialisiert die Gewinner-Ansicht
        story = new Story(); // Initialisiert die Story-Ansicht
        multiplayer = new Multiplayer(this); // Initialisiert die Multiplayer-Ansicht
    }

    public static void main(String[] args) { // Hauptmethode des Programms
        SwingUtilities.invokeLater(() -> new Main().setWindow()); // Setzt das Fenster im Event-Dispatch-Thread
    }

    public void setWindow() { // Methode zur Konfiguration des Hauptfensters
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beendet das Programm bei Fenster-Schließung
        mainFrame.setSize(1280, 760); // Setzt die Fenstergröße
        mainFrame.setResizable(false); // Verhindert die Größenänderung des Fensters
        mainFrame.setIconImage(new ImageIcon("src/resources/game/icon.jpg").getImage()); // Setzt das Fenster-Icon
        initMenu(); // Initialisiert das Menü
    }

    public void initMenu() { // Initialisiert und zeigt das Hauptmenü
        mainFrame.add(menu); // Fügt das Menü zum Hauptfenster hinzu
        mainFrame.revalidate(); // Validiert die Komponenten-Hierarchie
        mainFrame.repaint(); // Repaint das Fenster
        mainFrame.setVisible(true); // Macht das Fenster sichtbar
    }

    @Override
    public void menu() { // Methode zur Rückkehr ins Menü
        if (multiplayer != null) {
            mainFrame.remove(multiplayer); // Entfernt die Multiplayer-Ansicht, falls vorhanden
        }
        if (gui != null) {
            mainFrame.remove(gui); // Entfernt die Spielansicht, falls vorhanden
        }
        if (lose != null) {
            mainFrame.remove(lose); // Entfernt die Verlierer-Ansicht, falls vorhanden
        }
        if (win != null) {
            mainFrame.remove(win); // Entfernt die Gewinner-Ansicht, falls vorhanden
        }

        mainFrame.add(menu); // Fügt das Menü wieder hinzu
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void controll() { // Methode zur Anzeige der Steuerungsansicht
        model = new MainModel("Username", true); // Initialisiert das Modell mit Standard-Username
        controll = new Control(model.calculatePossiblePositions(), this); // Initialisiert die Steuerungsansicht

        mainFrame.remove(multiplayer); // Entfernt die Multiplayer-Ansicht

        mainFrame.add(controll); // Fügt die Steuerungsansicht hinzu
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void multiplayer() { // Methode zur Anzeige der Multiplayer-Ansicht
        if (controll != null) {
            mainFrame.remove(controll); // Entfernt die Steuerungsansicht, falls vorhanden
        }
        if (menu != null) {
            mainFrame.remove(menu); // Entfernt das Menü, falls vorhanden
        }

        mainFrame.add(multiplayer); // Fügt die Multiplayer-Ansicht hinzu
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    public void story() { // Methode zur Anzeige der Story-Ansicht
        mainFrame.remove(menu); // Entfernt das Menü

        mainFrame.add(story); // Fügt die Story-Ansicht hinzu
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void startMultiplayerGame() { // Methode zum Starten eines Multiplayer-Spiels
        mainFrame.remove(multiplayer); // Entfernt die Multiplayer-Ansicht

        model = new MainModel("Username", true); // Initialisiert das Modell mit Standard-Username
        gui = new GUI(model); // Initialisiert die Spielansicht
        controller = new Controller(model, gui, this, "Username", true); // Initialisiert den Controller

        if (!serverStarted) {
            startStream(model); // Startet den GameServer
            serverStarted = true;
        }

        mainFrame.add(gui); // Fügt die Spielansicht hinzu
        mainFrame.revalidate();
        mainFrame.repaint();

        mainFrame.requestFocus(); // Setzt den Fokus auf das Fenster
        mainFrame.addKeyListener(controller); // Fügt den KeyListener hinzu

        controller.startGame(); // Startet das Spiel
    }

    @Override
    public void startGame(String name) { // Methode zum Starten eines Spiels mit Story-Delay
        story(); // Zeigt die Story-Ansicht

        Timer timer = new Timer(3000, e -> startGameNow(name)); // Timer für Verzögerung
        timer.setRepeats(false); // Einmaliger Timer
        timer.start(); // Startet den Timer
    }

    private void startGameNow(String name) { // Methode zum tatsächlichen Starten des Spiels
        model = new MainModel(name, false); // Initialisiert das Modell mit Spielername
        gui = new GUI(model); // Initialisiert die Spielansicht
        controller = new Controller(model, gui, this, name, false); // Initialisiert den Controller

        mainFrame.remove(story); // Entfernt die Story-Ansicht

        mainFrame.add(gui); // Fügt die Spielansicht hinzu
        mainFrame.revalidate();
        mainFrame.repaint();

        mainFrame.requestFocus(); // Setzt den Fokus auf das Fenster
        mainFrame.addKeyListener(controller); // Fügt den KeyListener hinzu

        controller.startGame(); // Startet das Spiel
    }

    @Override
    public void lose() { // Methode zur Anzeige der Verlierer-Ansicht
        mainFrame.remove(gui); // Entfernt die Spielansicht

        mainFrame.add(lose); // Fügt die Verlierer-Ansicht hinzu
        mainFrame.revalidate();

        controller.winOrLose(); // Beendet das Spiel

        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void win() { // Methode zur Anzeige der Gewinner-Ansicht
        mainFrame.remove(gui); // Entfernt die Spielansicht

        mainFrame.add(win); // Fügt die Gewinner-Ansicht hinzu
        mainFrame.revalidate();

        controller.winOrLose(); // Beendet das Spiel

        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    // Methode zum Starten des GameServers für den Multiplayer-Stream
    public void startStream(MainModel model) {
        new Thread(() -> {
            server = new GameServer(model); // Initialisiert den GameServer
        }).start(); // Startet den Server in einem neuen Thread
    }
}