package ClientServer2;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket on port 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started on port 12345");

            while (true) {
                // Wait for a client to connect
                System.out.println("Wait for a client to connect");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
                System.out.println("Client Port = "+socket.getPort());


                // Read the request from the client
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String[] tokens = reader.readLine().split(" ");
                String client = tokens[0];
                System.out.println("Client "+client+" Connected successfully.");
                int number = Integer.parseInt(tokens[1]);

                // Process the request and send a response to the client
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                if (client.equals("A")) {
                    System.out.println("Client A send = "+tokens[1]);
                    if (number % 2 == 0) {
                        writer.println("ClientA message: even");

                    } else {
                        writer.println("ClientA message: odd");
                    }
                } else if (client.equals("B")) {
                    System.out.println("Client B send = "+tokens[1]);
                    int result = number * 2;
                    writer.println(Integer.toString(result));
                }

                // Close the connection
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
