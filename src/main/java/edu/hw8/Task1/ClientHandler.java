package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ClientHandler implements Runnable {
    private static final int BUFSIZ = 1024;
    private final Socket clientSocket;

    ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    @Override
    public void run() {
        try (
            clientSocket;
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()
        ) {
            System.out.println("Connected");

            byte[] buffer = new byte[BUFSIZ];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer, 0, BUFSIZ)) != -1) {

                String request = new String(buffer, 0, bytesRead);
                String response = getResponse(request);
                outputStream.write(response.getBytes());
                buffer = new byte[BUFSIZ];
            }

            System.out.println("Disconnected");
        } catch (IOException e) {
            System.err.println("IO error");
        }
    }

    @SuppressWarnings("ReturnCount")
    private static String getResponse(String request) {
        if (request.contains("личности")) {
            return "Не переходи на личности там, где их нет";
        } else if (request.contains("оскорбления")) {
            return "Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами";
        } else if (request.contains("глупый")) {
            return "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
        } else if (request.contains("интеллект")) {
            return "Чем ниже интеллект, тем громче оскорбления";
        } else {
            return "Unknown key word";
        }
    }
}
