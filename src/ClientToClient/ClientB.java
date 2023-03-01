package ClientToClient;
import java.io.*;
import java.net.*;

public class ClientB {
    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        System.out.println("Connected to server on " + HOST + ":" + PORT);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Read messages from user and send to server
        String message;
        while ((message = in.readLine()) != null) {
            out.println(message);
            System.out.println("Sent message to server: " + message);

            // Read messages from server and display to user
            System.out.println("Waiting for response from ClientA...");
            String response = serverIn.readLine();
            System.out.println("Received response from ClientA: " + response);
        }
    }
}
