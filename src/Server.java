import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 9999;
    private static Map<String, Integer> scoreboard = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server avviato sulla porta " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata da " + clientSocket.getInetAddress());

                // Creazione di un gestore client in un thread separato
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Classe per gestire le connessioni dei client in modo separato
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                // Legge il nome del giocatore dal client
                String playerName = in.readLine();
                System.out.println("Nuovo giocatore: " + playerName);

                // Invia la classifica attuale al client
                sendScoreboard();

                // Legge e aggiorna la classifica quando il client invia il punteggio
                String scoreStr;
                while ((scoreStr = in.readLine()) != null) {
                    try {
                        int score = Integer.parseInt(scoreStr);
                        scoreboard.put(playerName, score);
                        System.out.println("Aggiornamento punteggio per " + playerName + ": " + score);

                        // Invia la classifica aggiornata a tutti i client
                        sendScoreboard();
                    } catch (NumberFormatException e) {
                        System.err.println("Formato del punteggio non valido per " + playerName);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Invia la classifica a tutti i client
        private void sendScoreboard() {
            out.println("Classifica attuale:");
            for (Map.Entry<String, Integer> entry : scoreboard.entrySet()) {
                out.println(entry.getKey() + ": " + entry.getValue());
            }
            out.println(); // Fine della classifica
        }
    }
}
