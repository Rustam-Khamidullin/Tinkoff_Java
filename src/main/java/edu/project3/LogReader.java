package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class LogReader {
    private static final Logger LOGGER = Logger.getLogger(LogReader.class.getName());

    private LogReader() {
    }

    public static List<NginxLog> readLogs(
        String path,
        OffsetDateTime fromDate, OffsetDateTime toDate
    ) {

        List<NginxLog> logs;

        if (path.startsWith("http")) {
            logs = readLogsFromURL(path);
        } else {
            logs = readLogsFromLocalFiles(path);
        }

        return logs.stream()
            .filter(log -> log != null
                && log.timestamp().isBefore(toDate) && log.timestamp().isAfter(fromDate))
            .toList();
    }

    private static List<NginxLog> readLogsFromURL(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        try {
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            try (InputStream responseBody = response.body();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody))) {
                return reader.lines()
                    .map(LogParser::parseLogRecord)
                    .toList();
            }

        } catch (IOException | InterruptedException e) {
            return List.of();
        }
    }

    private static List<NginxLog> readLogsFromLocalFiles(String localPath) {
        List<String> logs = new ArrayList<>();

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + localPath);

        try (Stream<Path> filePathStream = Files.find(
            Paths.get("."),
            Integer.MAX_VALUE,
            (path, basicFileAttributes) -> pathMatcher.matches(path)
        )) {
            filePathStream
                .filter(Files::isRegularFile)
                .forEach(filePath -> {
                    try {
                        logs.addAll(Files.readAllLines(filePath, StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "readLogsFromLocalFiles", e);
                    }
                });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return logs.stream()
            .map(LogParser::parseLogRecord)
            .toList();
    }
}
