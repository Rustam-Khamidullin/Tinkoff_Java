package edu.hw1;

public class Task2 {
    private static final long RADIX = 10L;

    private Task2() {
    }

    public static int countDigits(long value) {
        long x = value;
        int res = 0;

        do {
            x /= RADIX;
            res++;
        } while (x != 0);

        return res;
    }
}
