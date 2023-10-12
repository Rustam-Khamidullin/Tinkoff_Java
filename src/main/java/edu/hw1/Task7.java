package edu.hw1;

public class Task7 {
    private Task7() {
    }

    private static int maxBinDigit(long value) {
        long x = value;
        int cntDigit = 0;
        do {
            x >>= 1;
            cntDigit++;
        } while (x != 0);

        return cntDigit;
    }

    public static long rotateRight(long value, int shift) {
        int lenX = maxBinDigit(value);
        long x = value;

        long onlyLastDigit = 1L << (lenX - 1);

        for (int n = shift % lenX; n > 0; n--) {
            if ((x & 1) == 1) {
                x >>= 1;
                x |= onlyLastDigit;
            } else {
                x >>= 1;
            }
        }

        return x;
    }

    public static long rotateLeft(long value, int shift) {
        int lenX = maxBinDigit(value);
        long x = value;

        long onlyLastDigit = 1L << (lenX - 1);

        for (int n = shift % lenX; n > 0; n--) {
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
