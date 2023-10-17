package edu.hw1;

public class Task1 {
    private static final long SECONDS_IN_MINUTE = 60L;

    private Task1() {
    }

    public static long minutesToSeconds(String str) {

        String[] parts = str.split(":");

        if (parts.length != 2) {
            return -1;
        }

        try {
            long minutes = Long.parseLong(parts[0]);
            long seconds = Long.parseLong(parts[1]);

            if ((seconds >= SECONDS_IN_MINUTE) || (seconds < 0) || (minutes < 0)) {
                return -1;
            }

            long res = Math.multiplyExact(minutes, SECONDS_IN_MINUTE);
            res = Math.addExact(res, seconds);

            return res;
        } catch (NumberFormatException | ArithmeticException e) {
            return -1;
        }
    }
}
