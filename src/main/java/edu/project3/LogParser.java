package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "(\\S+) - (\\S+) \\[(.*?)] \"(\\S+) (\\S+) (\\S+)\" (\\d+) (\\d+) \"(.*?)\" \"(.*?)\""
    );
    private static final DateTimeFormatter
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

    private LogParser() {
    }

    @SuppressWarnings("MagicNumber")
    public static NginxLog parseLogRecord(String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);

        if (!matcher.matches()) {
            return null;
        }

        try {
            String remoteAddr = matcher.group(1);
            String remoteUser = matcher.group(2);
            OffsetDateTime timestamp = OffsetDateTime.parse(matcher.group(3), DATE_TIME_FORMATTER);
            String requestType = matcher.group(4);
            String requestResource = matcher.group(5);
            String requestProtocol = matcher.group(6);
            int status = Integer.parseInt(matcher.group(7));
            long bodyBytesSent = Long.parseLong(matcher.group(8));
            String httpReferer = matcher.group(9);
            String httpUserAgent = matcher.group(10);

            return new NginxLog(
                remoteAddr,
                remoteUser,
                timestamp,
                requestType,
                requestResource,
                requestProtocol,
                status,
                bodyBytesSent,
                httpReferer,
                httpUserAgent
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
