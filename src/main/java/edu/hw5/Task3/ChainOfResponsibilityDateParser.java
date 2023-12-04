package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class ChainOfResponsibilityDateParser {
    private final DateParserHandler handlerChain;

    public ChainOfResponsibilityDateParser() {
        DateParserHandler[] chain = {
            new DateParserHandler.DateFormatHandler("yyyy-MM-dd"),
            new DateParserHandler.DateFormatHandler("yyyy-M-d"),
            new DateParserHandler.DateFormatHandler("d/M/yyyy"),
            new DateParserHandler.DateFormatHandler("d/M/yy"),
            new DateParserHandler.TomorrowHandler(),
            new DateParserHandler.TodayHandler(),
            new DateParserHandler.YesterdayHandler(),
            new DateParserHandler.DaysAgoHandler()
        };
        handlerChain = buildChain(chain);

    }

    public Optional<LocalDate> parseDate(String input) {
        return handlerChain.tryParse(input);
    }

    private DateParserHandler buildChain(DateParserHandler[] chain) {
        for (int i = 1; i < chain.length; i++) {
            chain[i - 1].setNextHandler(chain[i]);
        }
        if (chain.length > 0) {
            return chain[0];
        }
        return null;
    }
}
