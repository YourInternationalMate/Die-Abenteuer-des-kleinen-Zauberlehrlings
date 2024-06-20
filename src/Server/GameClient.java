package Server;

import model.SerializablePoint;

import java.io.*;
import java.net.*;
import java.util.*;

public class GameClient {
    private ArrayList<SerializablePoint> points; // Liste der zu sendenden Punkte

    public GameClient(ArrayList<SerializablePoint> points) {
        this.points = points;
    }

    public void sendPoints(String hostName) { // Methode zum Senden der Punkte an den Server
        System.out.println("Client gestartet!");

        int port = 8080; // Port-Nummer

        try (Socket socket = new Socket(hostName, port)) {
            ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            objectOut.writeObject(points); // Sendet die ArrayList an den Server

            String response = socketIn.readLine();
            System.out.println(response);

        } catch (UnknownHostException ue) { // Fehlerbehandlung für unbekannte Hosts
            System.out.println("Kein DNS-Eintrag für " + hostName);
        } catch (NoRouteToHostException e) { // Fehlerbehandlung für nicht erreichbare Hosts
            System.err.println("Nicht erreichbar " + hostName);
        } catch (IOException e) { // Fehlerbehandlung für allgemeine E/A-Fehler
            System.out.println("IO-Error");
        }
    }
}