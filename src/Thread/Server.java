package Thread;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 12345;
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            executorService.execute(() -> {
                try {
                    handleClient(clientSocket);
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

        String clientName = "Client " + Thread.currentThread().getId();
        System.out.println(clientName + " connected");

        String clientMessage;
        while ((clientMessage = input.readLine()) != null) {

            String ClientMainMessage;
            String[] tokens = clientMessage.split(" ");
            ClientMainMessage = clientMessage.substring(1);

            System.out.println(tokens[0] + " sent message: " + ClientMainMessage);


            // Respond to the client
            String response = "Server to " + clientName + ": " +ClientMainMessage.toUpperCase();
            output.println(response);
           // System.out.println(clientName + " received response: " + response);
        }

        System.out.println(clientName + " disconnected");
        clientSocket.close();
    }
}
