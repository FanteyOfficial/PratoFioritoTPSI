import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        int port = 6868; // Server's port number
        String serverAddress = "localhost"; // Server's IP address

        Socket socket = new Socket(serverAddress, port);
        System.out.println("Connected to the server");

        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);

        InputStream input = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("Sending message to server: " + line);
            writer.println(line);

            String response = bufferedReader.readLine();
            System.out.println("Received message from server:\n" + response);
        }

        socket.close();
        System.out.println("Closed connection to the server");
    }
}