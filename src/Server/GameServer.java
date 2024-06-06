package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class GameServer {

    public static void main(String[] args) {



        int port = 8080; // Port-Nummer

        try (ServerSocket server = new ServerSocket(port)){

            System.out.println("Server gestartet!");

            while (true) {

                try (Socket socket = server.accept()) { // try-with-resources, Auf Verbindung warten, Methode blockiert
                    //socket.setSoTimeout(5000);

                    BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Inputstream vom Client
                    PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true); // Outputstream zum Client mit autoflush

                    String line = socketIn.readLine();
                    System.out.println("Received from client: " + line);

//                    int matrikelnummer = 0; // Antwort
//                    try {
//                        matrikelnummer = Integer.parseInt(line);
//                        System.out.println("Matrikelnummer:" + matrikelnummer);
//                        socketOut.println(studenten.get(matrikelnummer));
//
//                    } catch (NumberFormatException e) {
//                        socketOut.println(e.getMessage());
//                    }

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }

                System.out.println("Warte auf n�chste Anfrage!");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
