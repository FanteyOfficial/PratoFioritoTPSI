import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Connected to server");

            Thread inputThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Received message: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            inputThread.start();

            while (true) {
                String message = in.readLine();
                System.out.println("Received matrix: ");
                System.out.println(message);

                if (scanner.hasNextInt()) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    if (clientHandler.checkCoordinates(x, y)) {
                        System.out.println("You have hit a mine!");
                        break;
                    } else if (clientHandler.isWinner(x, y)) {
                        System.out.println("Congratulations! You have won!");
                        break;
                    }
                }
            }
        } catch (UnknownHostException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } } }