import java.io.*;
import java.net.Socket;
import GameView.Game;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private String clientName;
    private Game game;

    public ClientHandler(Socket clientSocket, String clientName, Game game) {
        this.clientSocket = clientSocket;
        this.clientName = clientName;
        this.game = game;
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
            //writer.println("Welcome, " + clientName + "! Type messages to send coordinates to the server.");

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(clientName + " sent: " + line);

                // Parse the input string as coordinates
                String[] parts = line.split(" ");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);

                // Reveal the cell at the given coordinates
                game.revealTable(x, y);

                // Send the updated table to the client
                String tableHidden = game.getTableHiddenString();
                System.out.println(tableHidden);
                writer.println(tableHidden);
                /*for (int i = 0; i < tableHidden.length; i++) {
                    for (int j = 0; j < tableHidden[i].length; j++) {
                        writer.println(tableHidden[i][j]);
                    }
                }*/

                // Check if the client has won
                if (game.isWinner()) {
                    writer.println("Congratulations, " + clientName + "! You have won!");
                    break;
                }
            }

            clientSocket.close();
            System.out.println(clientName + " disconnected");
        } catch (IOException e) {
            System.out.println("Error handling client connection for " + clientName + ": " + e.getMessage());
        }
    }
}