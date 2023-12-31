package edu.hw2.task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        try (Connection connection = manager.getConnection()) {
            for (int i = 0; i < maxAttempts; i++) {
                try {
                    connection.execute(command);
                    return;
                } catch (ConnectionException e) {
                    if (i == maxAttempts - 1) {
                        throw new ConnectionException(
                            "Failed to execute command after " + maxAttempts + " attempts", e);
                    }
                }
            }
        } catch (ConnectionException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to close connection");
        }
    }
}


