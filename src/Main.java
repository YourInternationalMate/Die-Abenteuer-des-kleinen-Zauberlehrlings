import view.Menu;
import view.GUI;
import view.Lose;
import view.Win;
import controller.Controller;
import model.MainModel;
import interfaces.Redirector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main implements Redirector {
    private static JFrame mainFrame;
    private Menu menu;
    private Lose lose;
    private Win win;
    private GUI gui;
    private Controller controller;
    private MainModel model;

    public Main() {
        mainFrame = new JFrame("Die Abenteuer des kleinen Zauberlehrlings");
        menu = new Menu(this);
        lose = new Lose();
//        win = new Win(mainFrame, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setWindow();
            }
        });
    }

    public void setWindow() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1280, 760);
        mainFrame.setResizable(false);
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
        mainFrame.remove(gui);

        mainFrame.add(menu);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void startGame() {
        model = new MainModel();
        gui = new GUI(model);
        controller = new Controller(model, gui, this);

        mainFrame.remove(menu);

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

        mainFrame.addKeyListener(controller); // ESC für Back to Menu

        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    @Override
    public void win() {
        mainFrame.remove(gui);

        mainFrame.add(win);
        mainFrame.revalidate();

        mainFrame.repaint();
        mainFrame.setVisible(true);
    }
}
