package ClientServer2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientA {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        System.out.println("Connected to server: " + SERVER_ADDRESS + ":" + SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

        String message;
        while ((message = input.readLine()) != null) {
            output.println("A "+message);
            String response = serverInput.readLine();
            System.out.println("Response from server: " + response);
        }

        clientSocket.close();
    }
}

