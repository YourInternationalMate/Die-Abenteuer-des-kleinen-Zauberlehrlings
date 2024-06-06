package Server;

import model.SerializablePoint;

import java.io.*;
import java.net.*;
import java.util.*;

public class GameClient {
    private ArrayList<SerializablePoint> points;

    public GameClient(ArrayList<SerializablePoint> points) {
        this.points = points;
    }

    public void sendPoints(String hostName) {
        System.out.println("Client gestartet!");

        int port = 8080; // Port-Nummer

        try (Socket socket = new Socket(hostName, port)) {
            ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream()); // ObjectOutputstream zum Server
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Inputstream vom Server

            objectOut.writeObject(points); // Sende ArrayList an den Server

            String response = socketIn.readLine(); // Zeile vom Server empfangen
            System.out.println(response); // Zeile auf die Konsole schreiben

        } catch (UnknownHostException ue) {
            System.out.println("Kein DNS-Eintrag für " + hostName);
        } catch (NoRouteToHostException e) {
            System.err.println("Nicht erreichbar " + hostName);
        } catch (IOException e) {
            System.out.println("IO-Error");
        }
    }
}