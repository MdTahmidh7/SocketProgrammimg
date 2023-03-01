package ClientServer2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientB {
    public static void main(String[] args) {
        try {
            // Connect to the server on port 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server");

            // Send an integer to the server
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an Integer number : ");
            int number = scanner.nextInt();
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("B " + number);

            // Read the server's response
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String response = reader.readLine();
            System.out.println("Server response: " + response);

            // Close the connection
            socket.close();
        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
