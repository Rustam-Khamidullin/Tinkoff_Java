package edu.hw2.task3;

public sealed interface ConnectionManager {
    Connection getConnection();

    final class DefaultConnectionManager implements ConnectionManager {
        private static final double FAULTY_CONNECTION_PROBABILITY = 0;

        @Override
        public Connection getConnection() {
            if (Math.random() < FAULTY_CONNECTION_PROBABILITY) {
                return new Connection.FaultyConnection();
            } else {
                return new Connection.StableConnection();
            }
        }
    }

    final class FaultyConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            return new Connection.FaultyConnection();
        }
    }
}
