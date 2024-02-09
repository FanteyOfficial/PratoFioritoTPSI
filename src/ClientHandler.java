import java.io.*;
import java.net.Socket;

// ClientHandler class to handle the client's connection in a separate thread
public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private String clientName;

    public ClientHandler(Socket clientSocket, String clientName) {
        this.clientSocket = clientSocket;
        this.clientName = clientName;
    }

    @Override
    public void run() {
        try {
            InputStream input = clientSocket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(reader);

            OutputStream output = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            // Send a welcome message to the client with their assigned name
            writer.println("Welcome, " + clientName + "! Type messages to send to the server.");

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(clientName + " sent: " + line);
                writer.println("[" + clientName + "]: " + line);
            }

            clientSocket.close();
            System.out.println(clientName + " disconnected");
        } catch (IOException e) {
            System.out.println("Error handling client connection for " + clientName + ": " + e.getMessage());
        }
    }
}