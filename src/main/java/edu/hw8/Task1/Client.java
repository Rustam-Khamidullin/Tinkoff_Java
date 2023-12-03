package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int BUFSIZ = 1024;
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8081;
    private static final String NAME = "Ваня";

    private Client() {
    }

    @SuppressWarnings({"RegexpSinglelineJava", "UncommentedMain"})
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            try (
                Scanner scanner = new Scanner(System.in);
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream()
            ) {
                System.out.println("Connected to server on " + SERVER_ADDRESS + ":" + SERVER_PORT);
                System.out.println("Enter 0 to break the connection");

                while (true) {
                    System.out.print(NAME + ": ");
                    String input = scanner.nextLine();

                    if (input.equals("0")) {
                        System.out.println("Connection is broken");
                        break;
                    }

                    outputStream.write(input.getBytes());

                    byte[] buffer = new byte[BUFSIZ];
                    int bytesRead = inputStream.read(buffer);

                    if (bytesRead != -1) {
                        String response = new String(buffer, 0, bytesRead);
                        System.out.println("Response: " + response);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Connection could not be established");
        }
    }
}
