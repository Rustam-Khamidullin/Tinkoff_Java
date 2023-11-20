package edu.project3;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final DateTimeFormatter
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Main() {
    }

    public static void main(String[] args) {
        String path = null;
        OffsetDateTime fromDate = OffsetDateTime.MIN;
        OffsetDateTime toDate = OffsetDateTime.MAX;
        Format format = Format.MARKDOWN;

        for (int i = 0; i < args.length; i += 2) {
            String arg = args[i];
            String value = args[i + 1];

            switch (arg) {
                case "--path":
                    path = value;
                    break;
                case "--format":
                    if (value.equalsIgnoreCase("markdown")) {
                        format = Format.MARKDOWN;
                    } else if (value.equalsIgnoreCase("adoc")) {
                        format = Format.ADOC;
                    } else {
                        throw new IllegalArgumentException("Unsupported format.");
                    }
                    break;
                case "--from":
                    try {
                        fromDate = LocalDate.parse(value, DATE_TIME_FORMATTER)
                            .atStartOfDay().atOffset(ZoneOffset.UTC);
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Unsupported date from.");
                    }
                    break;
                case "--to":
                    try {
                        toDate = LocalDate.parse(value, DATE_TIME_FORMATTER)
                            .atStartOfDay().atOffset(ZoneOffset.UTC);
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Unsupported date to.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        if (path == null) {
            throw new IllegalArgumentException();
        }

        var logs = LogReader.readLogs(path, fromDate, toDate);
        var report = LogAnalyzer.getLogReport(logs);

        if (report == null) {
            LOGGER.log(Level.SEVERE, "Failed to get report");
            return;
        }

        if (format == Format.MARKDOWN) {
            LogAnalyzer.writeToMarkdownFile(report);
        } else {
            LogAnalyzer.writeToAdocFile(report);
        }
    }

    enum Format {
        MARKDOWN, ADOC
    }
}
