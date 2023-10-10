package edu.hw1;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Task0.printHelloWorld();

        System.out.println(Task1.minutesToSeconds("999:59"));

        System.out.println(Task2.countDigits(12345));

        System.out.println(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3}));

        System.out.println(Task4.fixString("badce"));

        System.out.println(Task5.isPalindromeDescendant(11211230));

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
