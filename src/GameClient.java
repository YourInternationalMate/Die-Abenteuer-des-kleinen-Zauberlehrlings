import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameClient {
    private Socket socket;
    private DataInputStream in;

    public GameClient(String address, int port) {
        try {
            socket = new Socket(address, port);
            in = new DataInputStream(socket.getInputStream());

            String clickedButtonValuesString = in.readUTF();

            ByteArrayInputStream bais = new ByteArrayInputStream(clickedButtonValuesString.getBytes("ISO-8859-1"));
            ObjectInputStream ois = new ObjectInputStream(bais);

            ArrayList<Point> clickedButtonValues = (ArrayList<Point>) ois.readObject();


            } catch (UnknownHostException ex) {
            throw new RuntimeException(ex);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }


    }
}
