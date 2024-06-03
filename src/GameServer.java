import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameServer {
    private ServerSocket serverSocket;
    private ArrayList<Point> clickedButtonValues;

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private DataOutputStream out;


        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new DataOutputStream(clientSocket.getOutputStream());

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
//                oos.writeObject(clickedButtonValues);
                oos.close();

                String clickedButtonValuesString = baos.toString("ISO-8859-1");

                out.writeUTF(clickedButtonValuesString);
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
