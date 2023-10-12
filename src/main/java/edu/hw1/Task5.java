package edu.hw1;

public class Task5 {
    private static final long RADIX = 10L;

    private Task5() {
    }

    private static boolean isPalindrome(long value) {
        long x = value;
        if (x < 0) {
            return false;
        }
        long reversed = 0;
        long copy = x;

        while (x > 0) {
            reversed = RADIX * reversed + x % RADIX;
            x /= RADIX;
        }
        return reversed == copy;
    }

    @SuppressWarnings("ReturnCount")
    public static boolean isPalindromeDescendant(long value) {
        final long twoLastDigits = 100L;
        long x = value;

        if (x < 0) {
            return false;
        }

        if (isPalindrome(x)) {
            return true;
        }

        int digitCount = Task2.countDigits(x);

        while (true) {
            if (digitCount == 1) {
                return false;
            }

            if (isPalindrome(x)) {
                return true;
            }

            if ((digitCount & 1) == 1) {
                return false;
            }

            digitCount >>= 1;

            int newDigitCount = digitCount;
            long newX = 0;
            long power = 1;

            for (int i = 0; i < digitCount; i++) {
                long r = x % twoLastDigits;
                long add = (r % RADIX) + (r / RADIX);
                newX += add * power;

                x /= twoLastDigits;
                power *= RADIX;
                if (add >= RADIX) {
                    power *= RADIX;
                    newDigitCount++;
                }
            }

            x = newX;
            digitCount = newDigitCount;
        }
    }
}

