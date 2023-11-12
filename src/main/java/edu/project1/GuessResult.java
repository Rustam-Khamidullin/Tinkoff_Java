package edu.project1;

sealed interface GuessResult {
    boolean toFinish();

    String massage();

    record Quit() implements GuessResult {
        @Override public boolean toFinish() {
            return true;
        }

        @Override public String massage() {
            return "Bye-bye!";
        }
    }

    record Defeat() implements GuessResult {
        @Override public boolean toFinish() {
            return true;
        }

        @Override public String massage() {
            return "Missed. That was your last attempt. You lost!";
        }
    }

    record Win() implements GuessResult {
        @Override public boolean toFinish() {
            return true;
        }

        @Override public String massage() {
            return "Hit! You won!";
        }
    }

    record SuccessfulGuess() implements GuessResult {
        @Override public boolean toFinish() {
            return false;
        }

        @Override public String massage() {
            return "Hit!";
        }
    }

    record FailedGuess() implements GuessResult {
        @Override public boolean toFinish() {
            return false;
        }

        @Override public String massage() {
            return "Missed.";
        }
    }

    record IncorrectInput() implements GuessResult {
        @Override public boolean toFinish() {
            return false;
        }

        @Override public String massage() {
            return "Incorrect input, please try again.";
        }
    }

    record UsedLetter() implements GuessResult {
        @Override public boolean toFinish() {
            return false;
        }

        @Override public String massage() {
            return "This letter has already been used, please try again.";
        }
    }
}
