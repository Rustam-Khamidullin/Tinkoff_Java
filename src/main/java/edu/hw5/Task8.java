package edu.hw5;

public class Task8 {
    private Task8() {
    }

    public static boolean isOddLength(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string.matches("^[01]([01]{2})*$");
    }

    public static boolean isStartZeroOddLengthOrStartOneEvenLength(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string.matches("^(0([01]{2})*|1[01]([01]{2})*)$");
    }

    public static boolean isZeroMultipleThree(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string.matches("^1*((01*){3})*$");
    }

    public static boolean isAllExceptSome(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string.matches("^(?!11$|111$)[01]*$");
    }

    public static boolean isOddEqualOne(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string.matches("^(1[01])*1?$");
    }

    public static boolean isAtLeastTwo0AndNoMoreOne1(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string.matches("^0*(001|010|100|00)0*$");
    }

    public static boolean thereIsNoConsecutiveOne(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string.matches("^0*(10+)*1?$");
    }
}
