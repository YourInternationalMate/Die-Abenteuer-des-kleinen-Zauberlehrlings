package Server;

import model.SerializablePoint;
import model.MainModel;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class GameServer {

    public GameServer(MainModel model) { // Konstruktor
        int port = 8080; // Portnummer für den Server

        try (ServerSocket server = new ServerSocket(port)) { // Erstellen eines ServerSocket, der auf dem angegebenen Port lauscht
            System.out.println("Server gestartet!");

            while (true) { // Endlosschleife, um kontinuierlich auf Anfragen zu warten
                try (Socket socket = server.accept()) { // Warten auf eingehende Verbindungen
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream()); // Eingabestream für Objekte
                    PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true); // Ausgabestream für Textnachrichten

                    Object obj = objectIn.readObject(); // Lesen des empfangenen Objekts
                    if (obj instanceof ArrayList) { // Überprüfen, ob das Objekt eine ArrayList ist
                        ArrayList<SerializablePoint> points = (ArrayList<SerializablePoint>) obj; // Casten des Objekts zu ArrayList
                        System.out.println("Received ArrayList from client: " + points);
                        // Verarbeiten der empfangenen Punkte
                        model.placeEnemies(points); // Platzieren der Gegner im Modell
                    }

                    socketOut.println("ArrayList received"); // Bestätigungsnachricht an den Client

                } catch (IOException | ClassNotFoundException e) { // Fehlerbehandlung für Ein-/Ausgabe und Klassenkonvertierung
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }

                System.out.println("Warte auf nächste Anfrage!");
            }
        } catch (IOException e) { // Fehlerbehandlung für ServerSocket
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}