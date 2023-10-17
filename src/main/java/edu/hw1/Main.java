package edu.hw1;

public final class Main {

    private Main() {
    }

    @SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        Task0.printHelloWorld();

        System.out.println(Task1.minutesToSeconds(Long.MIN_VALUE / 60 + ":00"));

        System.out.println(Task2.countDigits(Long.MIN_VALUE));

        System.out.println(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3}));

        System.out.println(Task4.fixString("badce"));

        System.out.println(Task5.isPalindromeDescendant(1224));

        System.out.println(Task6.countK(1234));

        System.out.println(Task7.rotateRight(5, 2));

        int[][] mas =
            {
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
            };
        System.out.println(Task8.knightBoardCapture(mas));
    }

}
