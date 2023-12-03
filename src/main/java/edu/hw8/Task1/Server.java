package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 8081;
    private static final int MAX_CONNECTIONS = 1;

    private Server() {
    }

    @SuppressWarnings({"UncommentedMain", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS)) {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server is running");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(new ClientHandler(clientSocket));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
