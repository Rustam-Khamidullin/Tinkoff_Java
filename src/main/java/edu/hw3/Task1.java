package edu.hw3;

public class Task1 {
    private Task1() {
    }

    public static String atbash(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder result = new StringBuilder(input);

        for (int i = 0; i < result.length(); i++) {
            char symbol = result.charAt(i);
            if (!Character.isLetter(symbol)) {
                continue;
            }
            if (Character.isLowerCase(symbol)) {
                result.setCharAt(i, (char) ('z' + 'a' - symbol));
            } else {
                result.setCharAt(i, (char) ('Z' + 'A' - symbol));
            }
        }

        return result.toString();
    }
}

