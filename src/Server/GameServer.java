package Server;

import model.SerializablePoint;
import model.MainModel;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class GameServer {

    public GameServer(MainModel model) {
        int port = 8080;

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server gestartet!");

            while (true) {
                try (Socket socket = server.accept()) {

                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);

                    Object obj = objectIn.readObject();
                    if (obj instanceof ArrayList) {
                        ArrayList<SerializablePoint> points = (ArrayList<SerializablePoint>) obj;
                        System.out.println("Received ArrayList from client: " + points);
                        // Do something with points

                        model.placeEnemies(points);
                    }

                    socketOut.println("ArrayList received");

                } catch (IOException | ClassNotFoundException e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }

                System.out.println("Warte auf nächste Anfrage!");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}