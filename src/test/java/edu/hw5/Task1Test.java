package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task1.calculateAverageDuration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task1Test {

    @Test
    void testCalculateAverageDuration1() {
        String[] periods = {"2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20"};

        assertEquals("3ч 40м", calculateAverageDuration(periods));
    }

    @Test
    void testCalculateAverageDuration2() {
        String[] periods = {"2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-03-13, 00:00 - 2022-03-13, 02:30"};

        assertEquals("3ч 0м", calculateAverageDuration(periods));
    }

    @Test
    void testCalculateAverageDuration3() {
        String[] periods = {"2022-03-12, 20:20 - 2022-03-12, 20:20"};

        assertEquals("0ч 0м", calculateAverageDuration(periods));
    }

    @Test
    void testInvalidInput() {
        String[] invalidPeriods = {"invalid input"};

        assertThrows(IllegalArgumentException.class, () -> calculateAverageDuration(invalidPeriods));
    }

    @Test
    void testInvalidMinutes() {
        String[] invalidPeriod = {"2022-04-01, 21:30 - 2022-04-02, 22:60"};

        assertThrows(IllegalArgumentException.class, () -> calculateAverageDuration(invalidPeriod));
    }

    @Test
    void testInvalidHours() {
        String[] invalidPeriod = {"2022-04-01, 21:30 - 2022-05-02, 24:59"};

        assertThrows(IllegalArgumentException.class, () -> calculateAverageDuration(invalidPeriod));
    }

    @Test
    void testInvalidDay() {
        String[] invalidPeriods = {"2022-02-02, 21:30 - 2022-04-40, 22:59"};

        assertThrows(IllegalArgumentException.class, () -> calculateAverageDuration(invalidPeriods));
    }

    @Test
    void testInvalidMonth() {
        String[] invalidPeriods = {"2022-02-02, 21:30 - 2022-13-04, 22:59"};

        assertThrows(IllegalArgumentException.class, () -> calculateAverageDuration(invalidPeriods));
    }
}
