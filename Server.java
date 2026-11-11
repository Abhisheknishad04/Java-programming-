
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {
            // Create ServerSocket
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server is waiting for client...");

            // Accept client connection
            Socket socket = serverSocket.accept();

            System.out.println("Client connected successfully!");

            // Input and output streams
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true
            );

            // Receive message from client
            String message = input.readLine();

            System.out.println("Client says: " + message);

            // Send response to client
            output.println("Hello Client! Message received by Server.");

            // Close connections
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}

