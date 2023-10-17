package edu.project1;

import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class HangmanGame {
    static Scanner scanner = new Scanner(System.in);

    private HangmanGame() {
    }

    public static void run() {
        var session = new Session();

        while (true) {
            printHiddenWord(session);

            printAttemptLeft(session);

            String input = askLetter();

            GuessResult guessResult = session.guess(input);

            printGuessResult(guessResult);

            if (guessResult.toFinish()) {
                break;
            }

            printSpase();
        }
    }

    private static void printGuessResult(GuessResult guessResult) {
        System.out.println("> " + guessResult.massage());
    }

    private static void printHiddenWord(Session session) {
        System.out.println("> " + session.getHiddenWord());
    }

    private static void printAttemptLeft(Session session) {
        System.out.println("> Mistake " + session.getAttempts() + " out of " + Session.MAX_ATTEMPTS + ".");
    }

    private static void printSpase() {
        System.out.println(">");
    }

    private static String askLetter() {
        System.out.print(
            "> Guess a letter:\n"
                + "< ");
        return scanner.nextLine();
    }
}
