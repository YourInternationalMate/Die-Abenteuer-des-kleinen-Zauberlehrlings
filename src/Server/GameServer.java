package Server;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameServer {
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    synchronized (this) {
                        this.clientHandler = new ClientHandler(clientSocket);
                    }
                    this.clientHandler.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setClickedButtonValues(ArrayList<Point> clickedButtonValues) {
        ClientHandler handler;
        synchronized (this) {
            handler = this.clientHandler;
        }
        if (handler != null) {
            handler.sendClickedButtonValues(clickedButtonValues);
            System.out.println("Clicked button values sent to client");
        } else {
            System.out.println("ClientHandler is null");
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream out;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            System.out.println("ClientHandler created for: " + socket.getInetAddress());
        }

        @Override
        public void run() {
            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.flush();
                System.out.println("Output stream initialized for: " + clientSocket.getInetAddress());

                while (!clientSocket.isClosed()) {
                    // Placeholder for handling incoming messages
                    Thread.sleep(1000); // Sleep to prevent busy waiting
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendClickedButtonValues(ArrayList<Point> clickedButtonValues) {
            try {
                if (out != null) {
                    out.writeObject(clickedButtonValues);
                    out.flush();
                    System.out.println("Clicked button values sent to client: " + clientSocket.getInetAddress());
                } else {
                    System.out.println("Output stream is null for: " + clientSocket.getInetAddress());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}