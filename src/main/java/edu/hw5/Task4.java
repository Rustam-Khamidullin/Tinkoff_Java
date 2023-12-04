package edu.hw5;

public class Task4 {
    private static final String PASSWD_RE = "^[^~!@#$%^&*|]*[~!@#$%^&*|][^~!@#$%^&*|]*$";

    private Task4() {
    }

    public static boolean isCorrectPassword(String password) {
        if (password == null) {
            throw new NullPointerException();
        }
        return password.matches(PASSWD_RE);
    }
}
