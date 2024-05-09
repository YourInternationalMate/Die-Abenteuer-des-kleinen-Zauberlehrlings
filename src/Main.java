import ui.GUI;
import controller.Controller;
import model.MainModel;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::start);
    }

    private static void start() {
        JFrame frame = new JFrame("Die Abenteuer des kleinen Zauberlehrlings"); //Stimmt das?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 760);
        frame.setResizable(false);
        
//        frame.setIconImage(); // Bild fehlt noch

        MainModel model = new MainModel();
        GUI view = new GUI(model);
        Controller controller = new Controller(model, view);

        frame.add(view);

        frame.setLocationRelativeTo(null); // Zentrieren und anzeigen
        frame.setVisible(true);

        frame.addKeyListener(controller); // Steuerung einbinden

        controller.startGame();
    }
}
