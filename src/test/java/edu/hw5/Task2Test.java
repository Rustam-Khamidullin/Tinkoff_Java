package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static edu.hw5.Task2.findFriday13;
import static edu.hw5.Task2.findNextFriday13;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    void testFindFriday13first() {
        int year = 2024;

        List<LocalDate> fridayThe13ths = findFriday13(year);

        assertEquals(2, fridayThe13ths.size());
        assertEquals(LocalDate.of(2024, 9, 13), fridayThe13ths.get(0));
        assertEquals(LocalDate.of(2024, 12, 13), fridayThe13ths.get(1));
    }

    @Test
    void testFindFriday13second() {
        int year = 1925;

        List<LocalDate> fridayThe13ths = findFriday13(year);

        assertEquals(3, fridayThe13ths.size());
        assertEquals(LocalDate.of(1925, 2, 13), fridayThe13ths.get(0));
        assertEquals(LocalDate.of(1925, 3, 13), fridayThe13ths.get(1));
        assertEquals(LocalDate.of(1925, 11, 13), fridayThe13ths.get(2));
    }

    @Test
    void testFindNextFriday13first() {
        LocalDate currentDate = LocalDate.of(1925, 3, 13);

        LocalDate nextFridayThe13th = findNextFriday13(currentDate);

        assertEquals(LocalDate.of(1925, 11, 13), nextFridayThe13th);
    }

    @Test
    void testFindNextFriday13second() {
        LocalDate currentDate = LocalDate.of(1924, 12, 13);

        LocalDate nextFridayThe13th = findNextFriday13(currentDate);

        assertEquals(LocalDate.of(1925, 2, 13), nextFridayThe13th);
    }
}
