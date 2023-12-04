package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public abstract class DateParserHandler {
    protected DateParserHandler nextHandler;

    public void setNextHandler(DateParserHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract Optional<LocalDate> parse(String input);

    public Optional<LocalDate> tryParse(String input) {
        var result = parse(input);
        if (result.isPresent()) {
            return result;
        }
        if (nextHandler != null) {
            return nextHandler.tryParse(input);
        }
        return Optional.empty();
    }

    static class DateFormatHandler extends DateParserHandler {
        private final String pattern;

        DateFormatHandler(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public Optional<LocalDate> parse(String input) {
            try {
                LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern(pattern));
                return Optional.of(date);
            } catch (Exception ignored) {
                return Optional.empty();
            }
        }
    }

    static class TomorrowHandler extends DateParserHandler {
        @Override
        public Optional<LocalDate> parse(String input) {
            if ("tomorrow".equalsIgnoreCase(input)) {
                return Optional.of(LocalDate.now().plusDays(1));
            }
            return Optional.empty();
        }
    }

    static class TodayHandler extends DateParserHandler {
        @Override
        public Optional<LocalDate> parse(String input) {
            if ("today".equalsIgnoreCase(input)) {
                return Optional.of(LocalDate.now());
            }
            return Optional.empty();
        }
    }

    static class YesterdayHandler extends DateParserHandler {
        @Override
        public Optional<LocalDate> parse(String input) {
            if ("yesterday".equalsIgnoreCase(input)) {
                return Optional.of(LocalDate.now().minusDays(1));
            }
            return Optional.empty();
        }
    }

    static class DaysAgoHandler extends DateParserHandler {
        @Override
        public Optional<LocalDate> parse(String input) {
            if (input.matches("\\d+\\s+day(s)?\\s+ago")) {
                int daysAgo = Integer.parseInt(input.split("\\s+")[0]);
                return Optional.of(LocalDate.now().minusDays(daysAgo));
            }
            return Optional.empty();
        }
    }
}

