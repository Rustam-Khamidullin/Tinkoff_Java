package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task1 {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
    public static String calculateAverageDuration(String[] periods) {
        if (periods == null) {
            throw new NullPointerException();
        }

        int number = periods.length;

        long totalMinutes = 0;

        for (var duration : periods) {
            LocalDateTime first;
            LocalDateTime second;
            long betweenMinutes;

            try {
                first = LocalDateTime.parse(duration.substring(0, 17), DATE_TIME_FORMATTER);
                second = LocalDateTime.parse(duration.substring(20), DATE_TIME_FORMATTER);
                betweenMinutes = Duration.between(first, second).toMinutes();
                if (betweenMinutes < 0) {
                    throw new IllegalArgumentException();
                }
            } catch (RuntimeException e) {
                throw new IllegalArgumentException();
            }

            totalMinutes += betweenMinutes;
        }

        Duration averageDuration = Duration.ofMinutes(totalMinutes / number);

        return averageDuration.toHours() + "ч " + (averageDuration.toMinutes() % 60) + "м";
    }
}
