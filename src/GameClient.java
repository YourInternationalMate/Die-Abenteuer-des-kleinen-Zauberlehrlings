import java.io.*;
import java.net.*;

public class GameClient {
    private Socket socket;
    private DataInputStream in;

    public GameClient(String address, int port) {
        try {
            socket = new Socket(address, port);
            in = new DataInputStream(socket.getInputStream());

            // Empfange die Daten vom Server
            while (true) {
                String message = in.readUTF();
                System.out.println("Server says: " + message);

                // TODO: Hier die empfangenen Spiel-Daten verarbeiten
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GameClient client = new GameClient("127.0.0.1", 12345);
    }
}
