import GameView.Game;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Server server;
    private BufferedReader in;
    private PrintWriter out;

    private Game game;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        this.game = new Game();
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received message: " + message);
                server.broadcast(message, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public Game getGame() {
        return game;
    }

    public boolean checkCoordinates(int x, int y) {
        return game.checkMine(x, y);
    }

    public boolean isWinner(int x, int y) {
        return game.checkMine(x, y);
    }
}