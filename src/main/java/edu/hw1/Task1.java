package edu.hw1;

public class Task1 {
    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
    public static long minutesToSeconds(String str) {
        String[] parts = str.split(":");

        String numberRE = "\\d+";

        if ((parts.length == 2) && parts[0].matches(numberRE) && parts[1].matches(numberRE)) {
            long minutes = Long.parseLong(parts[0]);
            long seconds = Long.parseLong(parts[1]);

            if (seconds >= 60) {
                return -1;
            }
            return minutes * 60 + seconds;
        } else {
            return -1;
        }
    }
}
