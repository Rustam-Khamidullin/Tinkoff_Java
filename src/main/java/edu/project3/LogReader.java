package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class LogReader {
    private LogReader() {
    }

    public static List<NginxLog> readLogs(String path) {
        try {
            return readLogsFromURL(path);
        } catch (MalformedURLException ignored) {
        } catch (IOException e) {
            System.err.println("Incorrect URL.");
        }

        try {
            return readLogsFromLocalFiles(path);
        } catch (IOException e) {
            return null;
        }
    }

    private static List<NginxLog> readLogsFromURL(String url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
            new URL(url).openStream()
        ))) {
            return reader.lines()
                .map(LogParser::parseLogRecord)
                .toList();
        }
    }

    private static List<NginxLog> readLogsFromLocalFiles(String localPath) throws IOException {
        try (Stream<String> logs = Files.lines(Paths.get(localPath))) {
            return logs.map(LogParser::parseLogRecord)
                .toList();
        } catch (IOException e) {
            System.err.println("Incorrect file.");
        }
        return List.of();
    }
}
