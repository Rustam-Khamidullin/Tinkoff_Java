package edu.hw5;

public class Task7 {
    private static final String AT_LEAST_TREE_AND_ZERO_THIRD = "^[01]{2}0[01]*$";
    private static final String START_EQUALS_END = "^([01])([01]*\\1)?$";
    private static final String LENGTH_MORE_ONE_LESS_TREE = "^[01]{1,3}$";

    private Task7() {
    }

    public static boolean isAtLeastTreeAndZeroThird(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string.matches(AT_LEAST_TREE_AND_ZERO_THIRD);
    }

    public static boolean isStartEqualsEnd(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string.matches(START_EQUALS_END);
    }

    public static boolean isLengthMoreOneLessTree(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string.matches(LENGTH_MORE_ONE_LESS_TREE);
    }
}
