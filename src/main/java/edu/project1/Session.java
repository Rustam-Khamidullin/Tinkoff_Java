package edu.project1;

import java.util.HashSet;
import java.util.Set;

public class Session {
    static final int MAX_ATTEMPTS = 5;
    static final String COMMAND_TO_QUIT = "quit";
    private final Set<Character> usedLetter;
    private final String word;
    private final StringBuilder hiddenWord;
    private int attempts;

    private int lettersToGuess;

    public Session() {
        this(Dictionary.getRandomWord().toLowerCase());
    }

    public Session(String myWord) {
        if (!myWord.matches("[a-zA-Z]+")) {
            throw new RuntimeException("Invalid word.");
        }
        this.word = myWord.toLowerCase();
        this.lettersToGuess = word.length();
        this.hiddenWord = new StringBuilder("*".repeat(word.length()));
        this.attempts = 0;
        this.usedLetter = new HashSet<>();
    }

    @SuppressWarnings("ReturnCount")
    public GuessResult guess(String input) {
        String inputLowerCase = input.toLowerCase();

        if (inputLowerCase.equals(COMMAND_TO_QUIT)) {
            return new GuessResult.Quit();
        }

        if ((inputLowerCase.length() != 1) || !Character.isLetter(inputLowerCase.charAt(0))) {
            return new GuessResult.IncorrectInput();
        }

        char letter = inputLowerCase.charAt(0);

        if (usedLetter.contains(letter)) {
            return new GuessResult.UsedLetter();
        }

        usedLetter.add(letter);

        if (word.contains(String.valueOf(letter))) {
            openLetter(letter);

            if (lettersToGuess > 0) {
                return new GuessResult.SuccessfulGuess();
            }

            return new GuessResult.Win();
        }

        attempts++;

        if (attempts < MAX_ATTEMPTS) {
            return new GuessResult.FailedGuess();
        }

        return new GuessResult.Defeat();
    }

    private void openLetter(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                hiddenWord.setCharAt(i, letter);
                lettersToGuess--;
            }
        }
    }

    public String getWord() {
        return word;
    }

    public Set<Character> getUsedLetter() {
        return usedLetter;
    }

    public StringBuilder getHiddenWord() {
        return hiddenWord;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getLettersToGuess() {
        return lettersToGuess;
    }

}
