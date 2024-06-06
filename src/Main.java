import Server.GameClient;
import Server.GameServer;
import view.Menu;
import view.GUI;
import view.Lose;
import view.Win;
import view.Story;
import view.Multiplayer;
import view.Controll;
import controller.Controller;
import model.MainModel;
import interfaces.Redirector;

import javax.swing.*;
import java.io.IOException;

public class Main implements Redirector {
    private static JFrame mainFrame;
    private Menu menu;
    private Lose lose;
    private Win win;
    private Story story;
    private GUI gui;
    private Multiplayer multiplayer;
    private Controll controll;
    private Controller controller;
    private MainModel model;
    private GameServer server;

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

    public void setWindow() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1280, 760);
        mainFrame.setResizable(false);
        mainFrame.setIconImage(new ImageIcon("src/resources/game/icon.jpg").getImage());
        initMenu();
    }

    public void initMenu() { // anfangs Menü
        mainFrame.add(menu);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void menu() { // um vom Spiel zurück ins Menü zu kommen
        if (multiplayer != null) {
            mainFrame.remove(multiplayer);
        }
        if (gui != null) {
            mainFrame.remove(gui);
        }
        if (lose != null) {
            mainFrame.remove(lose);
        }
        if (win != null) {
            mainFrame.remove(win);
        }

        mainFrame.add(menu);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void controll() {
        model = new MainModel("Username", true); //Username = Standard, wird nicht in DB gespeichert

        controll = new Controll(model.calculatePossiblePositions(), this);

        mainFrame.remove(multiplayer);

        mainFrame.add(controll);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void multiplayer() {
        if (controll != null) {
            mainFrame.remove(controll);
        }
        if (menu != null) {
            mainFrame.remove(menu);
        }

        mainFrame.add(multiplayer);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    public void story() {
        mainFrame.remove(menu);

        mainFrame.add(story);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void startMultiplayerGame() {
        mainFrame.remove(multiplayer);

        model = new MainModel("Username", true); //Username = Standard, wird nicht in DB gespeichert
        gui = new GUI(model);
        controller = new Controller(model, gui, this, "Username", true);

        startStream(model);

        mainFrame.add(gui);
        mainFrame.revalidate();
        mainFrame.repaint();

        mainFrame.requestFocus();
        mainFrame.addKeyListener(controller);

        controller.startGame();
    }

    @Override
    public void startGame(String name) {
        story(); // Delay für Story

        Timer timer = new Timer(3000, e -> startGameNow(name));
        timer.setRepeats(false);
        timer.start();
    }

    private void startGameNow(String name) {
        model = new MainModel(name, false);
        gui = new GUI(model);
        controller = new Controller(model, gui, this, name, false);

        mainFrame.remove(story);

        mainFrame.add(gui);
        mainFrame.revalidate();
        mainFrame.repaint();

        mainFrame.requestFocus();
        mainFrame.addKeyListener(controller);

        controller.startGame();
    }

    @Override
    public void lose() {
        mainFrame.remove(gui);

        mainFrame.add(lose);
        mainFrame.revalidate();

        controller.winOrLose();

        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void win() {
        mainFrame.remove(gui);

        mainFrame.add(win);
        mainFrame.revalidate();

        controller.winOrLose();

        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    //Stream
    public void startStream(MainModel model) {
        new Thread(() -> {
            server = new GameServer(model);
        }).start();
    }


}
