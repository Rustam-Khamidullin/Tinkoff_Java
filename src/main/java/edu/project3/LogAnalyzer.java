package edu.project3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LogAnalyzer {
    private LogAnalyzer() {
    }

    public static LogReport getLogReport(List<NginxLog> logs) {
        int totalRequests = 0;
        Map<String, Integer> requestResources = new TreeMap<>();
        Map<Integer, Integer> responseCode = new TreeMap<>();
        long sumBodyBytesSent = 0;

        for (var log : logs) {
            if (log != null) {
                totalRequests++;

                requestResources.merge(log.requestResource(), 1, Integer::sum);

                responseCode.merge(log.status(), 1, Integer::sum);

                sumBodyBytesSent += log.bodyBytesSent();
            }
        }
        if (totalRequests != 0) {
            return new LogReport(totalRequests, requestResources, responseCode, sumBodyBytesSent / totalRequests);
        } else {
            return null;
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static void writeToMarkdownFile(LogReport logReport) {
        var totalRequests = logReport.totalRequests;
        var requestResources = logReport.requestResources;
        var responseCode = logReport.responseCode;
        var averageBodyBytesSent = logReport.averageBodyBytesSent;

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of("report.md"))) {
            writer.write("#### Общая информация\n\n");
            writer.write("|        Метрика        |     Значение |\n");
            writer.write("|:---------------------:|-------------:|\n");
            writer.write(String.format("|  Количество запросов  |   %,d      |\n", totalRequests));
            writer.write(String.format("| Средний размер ответа |   %,d bytes |\n\n", averageBodyBytesSent));

            writer.write("#### Запрашиваемые ресурсы\n\n");
            writer.write("|     Ресурс      | Количество |\n");
            writer.write("|:---------------:|-----------:|\n");
            for (Map.Entry<String, Integer> entry : requestResources.entrySet()) {
                writer.write(String.format("|  `%s`  |   %,d     |\n", entry.getKey(), entry.getValue()));
            }
            writer.write("\n");

            writer.write("#### Коды ответа\n\n");
            writer.write("| Код |          Имя          | Количество |\n");
            writer.write("|:---:|:---------------------:|-----------:|\n");
            for (Map.Entry<Integer, Integer> entry : responseCode.entrySet()) {
                writer.write(String.format(
                    "|  %d  |    %s   |   %,d     |\n",
                    entry.getKey(),
                    getResponseCodeName(entry.getKey()),
                    entry.getValue()
                ));
            }
            writer.write("\n");
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static void writeToAdocFile(LogReport logReport) {
        var totalRequests = logReport.totalRequests;
        var requestResources = logReport.requestResources;
        var responseCode = logReport.responseCode;
        var averageBodyBytesSent = logReport.averageBodyBytesSent;

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of("report.adoc"))) {

            writer.write("== Общая информация\n\n");
            writer.write("[options=\"header\"]\n");
            writer.write("|====\n");
            writer.write("| Метрика               | Значение\n");
            writer.write(String.format("| Количество запросов   | %,d\n", totalRequests));
            writer.write(String.format("| Средний размер ответа  | %,d bytes\n", averageBodyBytesSent));
            writer.write("|====\n\n");

            writer.write("== Запрашиваемые ресурсы\n\n");
            writer.write("[options=\"header\"]\n");
            writer.write("|====\n");
            writer.write("| Ресурс               | Количество\n");
            for (Map.Entry<String, Integer> entry : requestResources.entrySet()) {
                writer.write(String.format("| %s | %,d\n", entry.getKey(), entry.getValue()));
            }
            writer.write("|====\n\n");

            writer.write("== Коды ответа\n\n");
            writer.write("[options=\"header\"]\n");
            writer.write("|====\n");
            writer.write("| Код | Имя                    | Количество\n");
            for (Map.Entry<Integer, Integer> entry : responseCode.entrySet()) {
                writer.write(String.format(
                    "| %d | %s | %,d\n",
                    entry.getKey(),
                    getResponseCodeName(entry.getKey()),
                    entry.getValue()
                ));
            }
            writer.write("|====\n\n");
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    @SuppressWarnings("MagicNumber")
    private static String getResponseCodeName(int code) {
        return switch (code) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "Unknown";
        };
    }

    public record LogReport(int totalRequests, Map<String, Integer> requestResources,
                            Map<Integer, Integer> responseCode, long averageBodyBytesSent) {
    }
}
