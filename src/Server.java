import java.io.*;
import java.net.*;

public class Server {
    // Initialize the nextClientNumber variable to 0
    private static int nextClientNumber = 0;

    public static void main(String[] args) throws IOException {
        int port = 6868; // Arbitrary port number

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected");

            // Assign a unique name to the client
            String clientName = "Client " + nextClientNumber++;

            // Create a new thread for handling the client's connection
            new Thread(new ClientHandler(clientSocket, clientName)).start();
        }
    }
}