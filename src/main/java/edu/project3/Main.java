package edu.project3;

public class Main {
    private Main() {
    }

    @SuppressWarnings("InnerAssignment")
    public static void main(String[] args) {
        String path = null;
        String fromDate = null;
        String toDate = null;
        String format = "markdown";

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "--path" -> path = args[i + 1];
                case "--from" -> fromDate = args[i + 1];
                case "--to" -> toDate = args[i + 1];
                case "--format" -> format = args[i + 1];
                default -> throw new IllegalArgumentException();
            }
        }

        if (path == null) {
            throw new IllegalArgumentException();
        }

        var lr = LogAnalyzer.getLogReport(LogReader.readLogs(path));

        LogAnalyzer.writeToMarkdownFile(lr);
    }
}
