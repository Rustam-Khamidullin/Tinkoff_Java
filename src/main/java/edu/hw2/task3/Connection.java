package edu.hw2.task3;

public sealed interface Connection extends AutoCloseable {
    void execute(String command);

    final class StableConnection implements Connection {
        @Override
        public void close() {
        }

        @Override
        public void execute(String command) {

        }
    }

    final class FaultyConnection implements Connection {
        private static final double CONNECTION_EXCEPTION_PROBABILITY = 1;

        @Override
        public void close() {
        }

        @Override
        public void execute(String command) {
            if (Math.random() < CONNECTION_EXCEPTION_PROBABILITY) {
                throw new ConnectionException("Some trouble.");
            }
        }
    }
}
