package Server;

import model.MainModel;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameClient {
    private Socket socket;
    private ObjectInputStream in;
    private MainModel model;

    public GameClient(String address, int port, MainModel model) {
        this.model = model;

        try {
            socket = new Socket(address, port);
            in = new ObjectInputStream(socket.getInputStream());

            ArrayList<Point> clickedButtonValues = (ArrayList<Point>) in.readObject();
            System.out.println("Clicked button values received from server: " + clickedButtonValues);

        } catch (UnknownHostException ex) {
            throw new RuntimeException(ex);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (socket != null) socket.close();
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}