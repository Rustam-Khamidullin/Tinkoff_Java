package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class ParseDate {
    private ParseDate() {
    }

    public static Optional<LocalDate> parseDate(String input) {
        var parser = new ChainOfResponsibilityDateParser();

        return parser.parseDate(input);
    }
}

