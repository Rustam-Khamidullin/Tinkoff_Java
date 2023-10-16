package edu.project1;

import java.util.HashSet;
import java.util.Set;

public class Session {
    static final int MAX_ATTEMPTS = 5;
    Set<Character> usedLetter;
    int attempts;
    int lettersToGuess;
    String word;
    StringBuilder hiddenWord;

    Session() {
        this.word = Dictionary.getRandomWord();
        this.lettersToGuess = word.length();
        this.hiddenWord = new StringBuilder("*".repeat(word.length()));
        this.attempts = 0;
        usedLetter = new HashSet<>();
    }

    public GuessResult guess(String input) {
        if ((input.length() != 1) || !Character.isLetter(input.charAt(0))) {
            return new GuessResult.IncorrectInput(attempts, MAX_ATTEMPTS, hiddenWord.toString());
        }

        char letter = input.charAt(0);

        if (usedLetter.contains(letter)) {
            return new GuessResult.UsedLetter(attempts, MAX_ATTEMPTS, hiddenWord.toString());
        }

        usedLetter.add(letter);

        if (word.contains(String.valueOf(letter))) {
            openLetter(letter);

            if (lettersToGuess > 0) {
                return new GuessResult.SuccessfulGuess(attempts, MAX_ATTEMPTS, hiddenWord.toString());
            }

            return new GuessResult.Win(attempts, MAX_ATTEMPTS, hiddenWord.toString());
        }

        attempts++;

        if (attempts < MAX_ATTEMPTS) {
            return new GuessResult.FailedGuess(attempts, MAX_ATTEMPTS, hiddenWord.toString());
        }

        return new GuessResult.Defeat(attempts, MAX_ATTEMPTS, hiddenWord.toString());
    }

    private void openLetter(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                hiddenWord.setCharAt(i, letter);
                lettersToGuess--;
            }
        }
    }

}
