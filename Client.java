
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        try {
            // Connect to server
            Socket socket = new Socket("localhost", 5000);

            System.out.println("Connected to Server!");

            // Input and output streams
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true
            );

            // Send message to server
            output.println("Hello Server! I am Client.");

            // Receive response from server
            String response = input.readLine();

            System.out.println("Server says: " + response);

            // Close connection
            socket.close();

        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
