package ClientServer;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server on port 12345
            Socket socket = new Socket("localhost", 12345);

            // Send a request to the server
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("Hello, server!");

            // Receive the response from the server
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String response = reader.readLine();
            System.out.println("Server response: " + response);

            // Close the connection
            socket.close();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
