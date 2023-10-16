package edu.project1;

sealed interface GuessResult {
    boolean toFinish();

    int attempt();

    int maxAttempts();

    void massage();

    record Defeat(int attempt, int maxAttempts, String hiddenWord) implements GuessResult {
        @Override public boolean toFinish() {
            return true;
        }

        @Override public void massage() {
            System.out.print(
                "> Missed, mistake " + attempt + " out of " + maxAttempts + ".\n"
                    + ">\n"
                    + "> The word: " + hiddenWord + "\n"
                    + ">\n"
                    + "> You lost!\n");
        }
    }

    record Win(int attempt, int maxAttempts, String hiddenWord) implements GuessResult {
        @Override public boolean toFinish() {
            return true;
        }

        @Override public void massage() {
            System.out.print(
                "> Hit!"
                    + ">\n"
                    + "> The word: " + hiddenWord + "\n"
                    + ">\n"
                    + "> You won!\n");
        }
    }

    record SuccessfulGuess(int attempt, int maxAttempts, String hiddenWord) implements GuessResult {
        @Override public boolean toFinish() {
            return false;
        }

        @Override public void massage() {
            System.out.print(
                "> Hit!\n"
                    + ">\n"
                    + "> The word: " + hiddenWord + "\n"
                    + ">\n");
        }
    }

    record FailedGuess(int attempt, int maxAttempts, String hiddenWord) implements GuessResult {
        @Override public boolean toFinish() {
            return false;
        }

        @Override public void massage() {
            System.out.print(
                "> Missed, mistake " + attempt + " out of " + maxAttempts + ".\n"
                    + ">\n"
                    + "> The word: " + hiddenWord + "\n"
                    + ">\n");
        }
    }

    record IncorrectInput(int attempt, int maxAttempts, String hiddenWord) implements GuessResult {
        @Override public boolean toFinish() {
            return false;
        }

        @Override public void massage() {
            System.out.print(
                "> Incorrect input, please try again.\n"
                    + "> Mistake " + attempt + " out of " + maxAttempts + ".\n"
                    + ">\n"
                    + "> The word: " + hiddenWord + "\n"
                    + ">\n");
        }
    }

    record UsedLetter(int attempt, int maxAttempts, String hiddenWord) implements GuessResult {
        @Override public boolean toFinish() {
            return false;
        }

        @Override public void massage() {
            System.out.print(
                "> This letter has already been used, please try again.\n"
                    + "> Mistake " + attempt + " out of " + maxAttempts + ".\n"
                    + ">\n"
                    + "> The word: " + hiddenWord + "\n"
                    + ">\n");
        }
    }
}
