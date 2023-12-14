package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task2.cloneFile;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {
    private static final String TEST_FILE = "src/test/java/edu/hw6/testFile.txt";

    @AfterEach
    void cleanup() throws IOException {
        try (Stream<Path> paths = Files.list(Path.of("src/test/java/edu/hw6/"))) {
            paths.filter(Files::isRegularFile)
                .filter(path -> path.getFileName().toString().startsWith("test"))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        }
    }

    @Test
    void testCloneFile() throws IOException {
        Path testDirectory = Path.of(TEST_FILE);

        Path tempFile = Files.createFile(testDirectory);

        cloneFile(tempFile);
        cloneFile(tempFile);
        cloneFile(tempFile);

        assertTrue(Files.exists(tempFile), "Temp file should exist");
        assertTrue(Files.exists(Path.of("src/test/java/edu/hw6/testFile - копия.txt")));
        assertTrue(Files.exists(Path.of("src/test/java/edu/hw6/testFile - копия (2).txt")));
        assertTrue(Files.exists(Path.of("src/test/java/edu/hw6/testFile - копия (3).txt")));
    }
}
