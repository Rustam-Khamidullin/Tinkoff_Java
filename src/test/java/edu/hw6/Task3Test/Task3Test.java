package edu.hw6.Task3Test;

import edu.hw6.Task3.AbstractFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task3Test {

    private static final Path TEST_DIRECTORY = Paths.get("src/test/java/edu/hw6/Task3Test");

    @Test
    void testLargerThan() throws IOException {
        AbstractFilter filter = AbstractFilter.largerThan(10);

        assertTrue(filter.accept(TEST_DIRECTORY.resolve("large.txt")));
        assertFalse(filter.accept(TEST_DIRECTORY.resolve("small.txt")));
    }

    @Test
    void testReadable() throws IOException {
        AbstractFilter filter = AbstractFilter.readable();

        assertTrue(filter.accept(TEST_DIRECTORY.resolve("small.txt")));
        assertTrue(filter.accept(TEST_DIRECTORY.resolve("large.txt")));
    }

    @Test
    void testWritable() throws IOException {
        AbstractFilter filter = AbstractFilter.writable();

        assertTrue(filter.accept(TEST_DIRECTORY.resolve("large.txt")));
        assertTrue(filter.accept(TEST_DIRECTORY.resolve("small.txt")));
    }

    @Test
    void testExtension() throws IOException {
        AbstractFilter filterTxt = AbstractFilter.extension("txt");

        assertTrue(filterTxt.accept(TEST_DIRECTORY.resolve("small.txt")));
        assertFalse(filterTxt.accept(TEST_DIRECTORY.resolve("binary.bin")));

        AbstractFilter filterBin = AbstractFilter.extension("bin");

        assertFalse(filterBin.accept(TEST_DIRECTORY.resolve("small.txt")));
        assertTrue(filterBin.accept(TEST_DIRECTORY.resolve("binary.bin")));
    }

    @Test
    void testRegexName() throws IOException {
        AbstractFilter filter = AbstractFilter.regexName(".*\\.txt");

        assertTrue(filter.accept(TEST_DIRECTORY.resolve("text_file.txt")));
        assertFalse(filter.accept(TEST_DIRECTORY.resolve("binary_file.bin")));
    }

    @Test
    void testMagicNumber() throws IOException {
        AbstractFilter filter = AbstractFilter.magicNumber((byte) '0', (byte) '1');

        assertTrue(filter.accept(TEST_DIRECTORY.resolve("binary.bin")));
        assertFalse(filter.accept(TEST_DIRECTORY.resolve("small.txt")));
    }

    @Test
    void testAnd() throws IOException {
        AbstractFilter combinedFilter = AbstractFilter.extension("txt")
            .and(AbstractFilter.writable())
            .and(AbstractFilter.writable())
            .and(AbstractFilter.largerThan(10));

        assertTrue(combinedFilter.accept(TEST_DIRECTORY.resolve("large.txt")));
        assertFalse(combinedFilter.accept(TEST_DIRECTORY.resolve("small.txt")));
        assertFalse(combinedFilter.accept(TEST_DIRECTORY.resolve("binary.bin")));
    }
}
