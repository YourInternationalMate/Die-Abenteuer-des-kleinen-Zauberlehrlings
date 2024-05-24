import model.MainModel;

import java.io.*;
import java.net.*;

public class GameClient {
    private Socket socket;
    private ObjectInputStream in;

    public GameClient(String address, int port) {
        try {
            socket = new Socket(address, port);
            in = new ObjectInputStream(socket.getInputStream());

            // Empfange die Daten vom Server
            while (true) {
                MainModel model = (MainModel) in.readObject();

                System.out.println(model.getPlayerX());

                // TODO: Hier die empfangenen Spiel-Daten an View weiterleiten
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
