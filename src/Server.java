import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    private List<ClientHandler> clientHandlers;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            clientHandlers = new ArrayList<>();

            System.out.println("Server listening on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(socket, this);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcast(String message, ClientHandler sender) {
        sender.sendMessage(getMatrixAsString(sender.getGame().getTable()));
        for (ClientHandler client : clientHandlers) {
            if (client != sender) {
                client.sendMessage(getMatrixAsString(client.getGame().getTable()));
            }
        }
    }

    private String getMatrixAsString(char[][] table) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                sb.append(table[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new Server();
    }
}