package edu.hw1;

public class Task5 {
    private Task5() {
    }

    @SuppressWarnings({"MagicNumber", "ParameterAssignment"})
    private static boolean isPalindrome(long x) {
        if (x < 0) {
            return false;
        }
        long reversed = 0;
        long copy = x;

        while (x > 0) {
            reversed = 10 * reversed + x % 10;
            x /= 10;
        }
        return reversed == copy;
    }

    @SuppressWarnings({"MagicNumber", "ParameterAssignment", "ReturnCount"})
    public static boolean isPalindromeDescendant(long x) {
        if (x < 0) {
            return false;
        }

        if (isPalindrome(x)) {
            return true;
        }

        while (true) {
            int cd = Task2.countDigits(x);

            if (cd == 1) {
                return false;
            }

            if (isPalindrome(x)) {
                return true;
            }

            if (cd % 2 == 1) {
                return false;
            }

            long newX = 0;
            long power = 1;

            for (int i = 0; i < cd / 2; i++) {
                long r = x % 100;
                long add = (r % 10) + (r / 10);
                newX += add * power;

                x /= 100;
                power *= 10;
                if (add > 9) {
                    power *= 10;
                }
            }

            x = newX;
        }
    }
}

