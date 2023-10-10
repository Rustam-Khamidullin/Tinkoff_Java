package edu.hw1;

public class Task4 {
    public static String fixString(String string) {
        var str = new StringBuilder(string);

        for (int i = 0; i + 1 < str.length(); i += 2) {
            char tmp = str.charAt(i);
            str.setCharAt(i, str.charAt(i + 1));
            str.setCharAt(i + 1, tmp);
        }

        return str.toString();
    }
}
