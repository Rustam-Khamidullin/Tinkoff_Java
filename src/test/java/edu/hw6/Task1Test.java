package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task1Test {
    private static final String TEST_FILE = "src/test/java/edu/hw6/testFile.txt";
    private DiskMap diskMap;

    @AfterEach
    void cleanup() throws IOException {
        diskMap.clear();
        diskMap.save();

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

    @BeforeEach
    void setUp() {
        diskMap = new DiskMap(TEST_FILE);
    }

    @Test
    void testPutAndGet() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        assertEquals("value1", diskMap.get("key1"));
        assertEquals("value2", diskMap.get("key2"));
    }

    @Test
    void testRemove() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        assertEquals("value1", diskMap.remove("key1"));
        assertNull(diskMap.get("key1"));
        assertEquals(1, diskMap.size());
    }

    @Test
    void testLoad() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.save();

        DiskMap loadedMap = new DiskMap(TEST_FILE, false);
        loadedMap.load();

        assertEquals("value1", loadedMap.get("key1"));
        assertEquals("value2", loadedMap.get("key2"));
    }

    @Test
    void testImmediateSave() {
        DiskMap immediateSaveMap = new DiskMap(TEST_FILE, true);

        immediateSaveMap.put("key1", "value1");
        immediateSaveMap.put("key2", "value2");

        DiskMap loadedMap = new DiskMap(TEST_FILE, false);
        loadedMap.load();

        assertEquals("value1", loadedMap.get("key1"));
        assertEquals("value2", loadedMap.get("key2"));
    }
}
