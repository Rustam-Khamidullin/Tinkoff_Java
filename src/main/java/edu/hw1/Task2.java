package edu.hw1;

public class Task2 {

    private Task2() {
    }

    public static int countDigits(long value) {
        final long radix = 10L;
        long x = value;
        int res = 0;

        do {
            x /= radix;
            res++;
        } while (x != 0);

        return res;
    }
}
