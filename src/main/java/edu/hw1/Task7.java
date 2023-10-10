package edu.hw1;

public class Task7 {
    private Task7() {
    }

    @SuppressWarnings("ParameterAssignment")
    private static int maxBinDigit(long x) {
        int cntDigit = 0;
        do {
            x >>= 1;
            cntDigit++;
        } while (x > 0);

        return cntDigit;
    }

    @SuppressWarnings("ParameterAssignment")
    public static long rotateRight(long x, int n) {
        int lenX = maxBinDigit(x);

        n %= lenX;

        long onlyLastDigit = 1L << (lenX - 1);

        for (; n > 0; n--) {
            if ((x & 1) == 1) {
                x >>= 1;
                x |= onlyLastDigit;
            } else {
                x >>= 1;
            }
        }

        return x;
    }

    @SuppressWarnings("ParameterAssignment")
    public static long rotateLeft(long x, int n) {
        int lenX = maxBinDigit(x);

        n %= lenX;

        long onlyLastDigit = 1L << (lenX - 1);

        for (; n > 0; n--) {
            if ((x & onlyLastDigit) == onlyLastDigit) {
                x &= ~onlyLastDigit;
                x <<= 1;
                x |= 1L;
            } else {
                x <<= 1;
            }
        }

        return x;
    }
}
