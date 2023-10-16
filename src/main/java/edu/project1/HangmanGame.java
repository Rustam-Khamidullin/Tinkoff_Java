package edu.project1;

import java.util.Scanner;

public class HangmanGame {
    static Scanner scanner = new Scanner(System.in);

    public static void run() {
        var session = new Session();
        System.out.println(session.word);
        while (true) {
            String input = askLetter();

            GuessResult guessResult = session.guess(input);

            guessResult.massage();

            if (guessResult.toFinish()) {
                break;
            }
        }
    }

    public static String askLetter() {
        System.out.print(
            "> Guess a letter:\n" +
                "< ");
        return scanner.nextLine();
    }

}
