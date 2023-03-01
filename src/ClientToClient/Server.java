package ClientToClient;

import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 1234;
    private Socket socketA, socketB;
    private BufferedReader inA, inB;
    private PrintWriter outA, outB;

    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started on port " + PORT);

        // Wait for connections from clients
        socketA = serverSocket.accept();
        System.out.println("ClientA connected");
        socketB = serverSocket.accept();
        System.out.println("ClientB connected");

        // Set up input and output streams for clients
        inA = new BufferedReader(new InputStreamReader(socketA.getInputStream()));
        outA = new PrintWriter(socketA.getOutputStream(), true);
        inB = new BufferedReader(new InputStreamReader(socketB.getInputStream()));
        outB = new PrintWriter(socketB.getOutputStream(), true);

        // Start client handlers to handle communication between clients
        ClientHandler clientA = new ClientHandler(socketA, socketB, "ClientA");
        new Thread(clientA).start();
        ClientHandler clientB = new ClientHandler(socketB, socketA, "ClientB");
        new Thread(clientB).start();
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private Socket otherSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String clientName;

        public ClientHandler(Socket clientSocket, Socket otherSocket, String clientName) throws IOException {
            this.clientSocket = clientSocket;
            this.otherSocket = otherSocket;
            this.clientName = clientName;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(otherSocket.getOutputStream(), true);
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = in.readLine()) != null) {
                    System.out.println(clientName + " sent: " + message);
                    out.println(clientName + ": " + message);
                }
            } catch (IOException e) {
                System.out.println("Error handling client: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Error closing client socket: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
