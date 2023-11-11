package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static edu.hw5.Task3.ParseDate.parseDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task3Test {

    @Test
    void testParseDateWithHyphenFormat() {
        assertEquals(Optional.of(LocalDate.of(2020, 10, 10)), parseDate("2020-10-10"));
        assertEquals(Optional.of(LocalDate.of(2020, 12, 2)), parseDate("2020-12-2"));
    }

    @Test
    void testParseDateWithSlashFormat() {
        assertEquals(Optional.of(LocalDate.of(1976, 1, 3)), parseDate("3/1/1976"));
        assertEquals(Optional.of(LocalDate.of(2020, 1, 3)), parseDate("3/1/20"));
    }

    @Test
    void testParseDateTomorrowTodayYesterday() {
        assertEquals(Optional.of(LocalDate.now().plusDays(1)), parseDate("tomorrow"));
        assertEquals(Optional.of(LocalDate.now()), parseDate("today"));
        assertEquals(Optional.of(LocalDate.now().minusDays(1)), parseDate("yesterday"));
    }

    @Test
    void testParseDateDaysAgo() {
        assertEquals(Optional.of(LocalDate.now().minusDays(5)), parseDate("5 days ago"));
        assertEquals(Optional.of(LocalDate.now().minusDays(2234)), parseDate("2234 days ago"));
        assertEquals(Optional.of(LocalDate.now().minusDays(1)), parseDate("1 day ago"));
        assertEquals(Optional.of(LocalDate.now().minusDays(1243)), parseDate("1243 day ago"));
    }

    @Test
    void testParseDateInvalidFormat() {
        assertFalse(parseDate("invalidFormat").isPresent());
    }
}
