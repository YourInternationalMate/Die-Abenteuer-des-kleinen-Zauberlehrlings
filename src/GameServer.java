import controller.Controller;
import model.MainModel;

import java.io.*;
import java.net.*;

public class GameServer {
    private ServerSocket serverSocket;
    private MainModel model;
    private Controller controller;

    public GameServer(int port, MainModel model, Controller controller) throws IOException {
        serverSocket = new ServerSocket(port);
        this.model = model;
        this.controller = controller;
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, model, controller).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private DataOutputStream out;

        private MainModel model;
        private Controller controller;

        public ClientHandler(Socket socket, MainModel model, Controller controller) {
            this.clientSocket = socket;
            this.model = model;
            this.controller = controller;
        }

        public void run() {
            try {
                out = new DataOutputStream(clientSocket.getOutputStream());

                 while (!controller.isEnd()) { // solange das Spiel nicht beendet ist
                     out.write(model.getPlayerX());
                     out.flush();
                 }

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
