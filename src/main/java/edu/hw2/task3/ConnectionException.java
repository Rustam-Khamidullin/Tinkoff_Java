package edu.hw2.task3;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String massage, Throwable cause) {
        super(massage, cause);
    }

    public ConnectionException(String massage) {
        super(massage);
    }
}
