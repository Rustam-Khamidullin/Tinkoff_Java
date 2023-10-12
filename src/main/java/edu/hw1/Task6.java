package edu.hw1;

import java.util.Arrays;

public class Task6 {
    public static final long KAPRECAR_CONSTANT = 6174;

    private Task6() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countK(int x) {
        if ((x < 999) || (x > 9999) || (x % 1111 == 0)) {
            return -1;
        }

        if (x == KAPRECAR_CONSTANT) {
            return 0;
        }

        String tmp = Integer.toString(x);
        if (tmp.length() < 4) {
            tmp = tmp + '0';
        }

        char[] digits = tmp.toCharArray();

        Arrays.sort(digits);

        int x1 = 0;
        int x2 = 0;
        int pow1 = 1000;
        int pow2 = 1;

        for (int i = 0; i < 4; i++) {
            x1 += (digits[i] - '0') * pow1;
            x2 += (digits[i] - '0') * pow2;

            pow1 /= 10;
            pow2 *= 10;
        }

        return 1 + countK(x2 - x1);
    }

}
