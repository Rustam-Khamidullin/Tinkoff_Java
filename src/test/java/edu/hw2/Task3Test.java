package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.ConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    @Test
    public void testUpdatePackagesFaultyConnection() {
        // always ConnectionException
        var connectionManager = new ConnectionManager.FaultyConnectionManager();
        var executor = new PopularCommandExecutor(connectionManager, 3);

        assertThrows(ConnectionException.class, executor::updatePackages);
    }

    @Test
    public void testUpdatePackagesStableConnection() {
        // always StableConnection
        var connectionManager = new ConnectionManager.DefaultConnectionManager();
        var executor = new PopularCommandExecutor(connectionManager, 3);

        assertDoesNotThrow(executor::updatePackages);
    }
}
