package Server;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class GameClient {
    public static void main(String[] args) {

        int matrikelnummer;

        System.out.println("Client gestartet!");

        String hostName = "10.25.2.67"; // Rechner-Name bzw. -Adresse
        int port = 8080; // Port-Nummer


        try (Socket socket = new Socket(hostName, port)){

            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Inputstream vom Server
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true); // Outputstream zum Server

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Matrikelnummer:");
                matrikelnummer = scanner.nextInt();
            }


            socketOut.println(matrikelnummer);

//            String text = socketIn.readLine(); // Zeile vom Server empfangen // Antwort empfangen
//
//            System.out.println(text); // Zeile auf die Konsole schreiben


        } catch (UnknownHostException ue) {
            System.out.println("Kein DNS-Eintrag fuer " + hostName);
        } catch (NoRouteToHostException e) {
            System.err.println("Nicht erreichbar " + hostName);
        } catch (IOException e) {
            System.out.println("IO-Error");
        }


    }
}
