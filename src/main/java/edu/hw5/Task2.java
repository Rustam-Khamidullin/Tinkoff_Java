package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class Task2 {
    private static final int THIRTEEN = 13;
    private static final int MAX_MONTH = 12;

    private Task2() {
    }

    public static List<LocalDate> findFriday13(int year) {
        List<LocalDate> listFriday13 = new ArrayList<>();

        for (int month = 1; month <= MAX_MONTH; month++) {
            LocalDate date = LocalDate.of(year, month, THIRTEEN);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                listFriday13.add(date);
            }
        }

        return listFriday13;
    }

    public static LocalDate findNextFriday13(LocalDate date) {
        var currentDate = date;
        do {
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        } while (currentDate.getDayOfMonth() != THIRTEEN);

        return currentDate;
    }
}
