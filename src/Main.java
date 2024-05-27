import view.Menu;
import view.GUI;
import view.Lose;
import view.Win;
import view.Story;
import view.Watch;
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
    private Watch watch;
    private GUI gui;
    private Controller controller;
    private MainModel model;

    public Main() {
        mainFrame = new JFrame("Die Abenteuer des kleinen Zauberlehrlings");
        menu = new Menu(this);
        lose = new Lose();
        win = new Win();
        story = new Story();
        watch = new Watch(this);
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
        if (watch != null) {
            mainFrame.remove(watch);
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
    public void watch() {
        mainFrame.remove(menu);
        mainFrame.add(watch);
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
    public void startGame(String name) {
        story(); // Delay für Story

        Timer timer = new Timer(3000, e -> startGameNow(name));
        timer.setRepeats(false);
        timer.start();
    }

    private void startGameNow(String name) {
        model = new MainModel(name);
        gui = new GUI(model);
        controller = new Controller(model, gui, this, name);
        startStream();

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
    public void startStream() {
        new Thread(() -> {
            try {
                GameServer server = new GameServer(8080, model, controller);
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    @Override
    public void connectToStream(String IP) {
        new Thread(() -> {
            GameClient client = new GameClient(IP, 8080);
        }).start();
    }

}
