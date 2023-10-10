package edu.hw1;

public class Task1 {
    public static long minutesToSeconds(String str) {
        String[] parts = str.split(":");

        if ((parts.length == 2) && parts[0].matches("\\d+") && parts[1].matches("\\d+")) {
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
