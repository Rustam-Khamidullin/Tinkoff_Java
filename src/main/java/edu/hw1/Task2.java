package edu.hw1;

public class Task2 {
    public static int countDigits(long x) {
        x = Math.abs(x);
        int res = 1;
        while (x >= 10) {
            x /= 10;
            res++;
        }
        return res;
    }
}
