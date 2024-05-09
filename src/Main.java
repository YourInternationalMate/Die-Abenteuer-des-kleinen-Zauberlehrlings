import ui.Menu;
import ui.GUI;
import controller.Controller;
import model.MainModel;
import interfaces.GameStarter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main implements GameStarter{
    private static JFrame mainFrame;
    private Menu menu;
    private GUI gui;
    private Controller controller;
    private MainModel model;

    public Main() {
        mainFrame = new JFrame("Die Abenteuer des kleinen Zauberlehrlings");
        menu = new Menu(mainFrame, this);
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
        mainFrame.setSize(1280, 780);
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
}
