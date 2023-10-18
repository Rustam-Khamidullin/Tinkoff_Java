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
        @Override
        public void close() {
        }

        @Override
        public void execute(String command) {

        }
    }
}
