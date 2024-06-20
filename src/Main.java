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

public class Main implements Redirector {
    private static JFrame mainFrame;
    private Menu menu;
    private Lose lose;
    private Win win;
    private Story story;
    private GUI gui;
    private Multiplayer multiplayer;
    private Control control;
    private Controller controller;
    private MainModel model;
    private GameServer server;
    private boolean serverStarted = false;

    public Main() {
        mainFrame = new JFrame("Die Abenteuer des kleinen Zauberlehrlings");
        menu = new Menu(this);
        lose = new Lose();
        win = new Win();
        story = new Story();
        multiplayer = new Multiplayer(this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setWindow());
    }

    public void setWindow() { // Methode zur Konfiguration des Hauptfensters
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1280, 760);
        mainFrame.setResizable(false);
        mainFrame.setIconImage(new ImageIcon("src/resources/game/icon.jpg").getImage());
        initMenu();
    }

    public void initMenu() {
        mainFrame.add(menu);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
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
    public void control() {
        model = new MainModel("Username", true); // Initialisiert das Modell mit Standard-Username
        control = new Control(model.calculatePossiblePositions(), this);

        mainFrame.remove(multiplayer); // Entfernt die Multiplayer-Ansicht

        mainFrame.add(control); // Fügt die Steuerungsansicht hinzu
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void multiplayer() { // Methode zur Anzeige der Multiplayer-Ansicht
        if (control != null) {
            mainFrame.remove(control); // Entfernt die Steuerungsansicht, falls vorhanden
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
        gui = new GUI(model);
        controller = new Controller(model, gui, this, "Username", true);

        if (!serverStarted) {
            startStream(model); // Startet den GameServer
            serverStarted = true;
        }

        mainFrame.add(gui); // Fügt die Spielansicht hinzu
        mainFrame.revalidate();
        mainFrame.repaint();

        mainFrame.requestFocus();
        mainFrame.addKeyListener(controller); // Fügt den KeyListener hinzu

        controller.startGame();
    }

    @Override
    public void startGame(String name) {
        story(); // Zeigt die Story-Ansicht

        Timer timer = new Timer(3000, e -> startGameNow(name)); // Timer für Verzögerung
        timer.setRepeats(false); // Einmaliger Timer
        timer.start();
    }

    private void startGameNow(String name) {
        model = new MainModel(name, false);
        gui = new GUI(model);
        controller = new Controller(model, gui, this, name, false);

        mainFrame.remove(story); // Entfernt die Story-Ansicht

        mainFrame.add(gui); // Fügt die Spielansicht hinzu
        mainFrame.revalidate();
        mainFrame.repaint();

        mainFrame.requestFocus();
        mainFrame.addKeyListener(controller); // Fügt den KeyListener hinzu

        controller.startGame();
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

    public void startStream(MainModel model) {
        new Thread(() -> {
            server = new GameServer(model); // Initialisiert den GameServer
        }).start(); // Startet den Server in einem neuen Thread
    }
}