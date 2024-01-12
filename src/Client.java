import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Inserisci il nome del giocatore
            System.out.print("Inserisci il tuo nome: ");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String playerName = userInput.readLine();
            out.println(playerName);

            // Leggi e visualizza la classifica iniziale
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
            }

            // Invia il punteggio al server
            while (true) {
                System.out.print("Inserisci il tuo punteggio (o 'exit' per uscire): ");
                String scoreInput = userInput.readLine();

                if (scoreInput.equalsIgnoreCase("exit")) {
                    break;
                }

                out.println(scoreInput);
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
