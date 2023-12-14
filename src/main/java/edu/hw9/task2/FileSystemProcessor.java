package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FileSystemProcessor {
    private FileSystemProcessor() {
    }

    static class FindLargeDirectoryTask extends RecursiveTask<List<File>> {
        private final static int LARGE_FROM = 1000;
        private final File directory;

        FindLargeDirectoryTask(File directory) {
            this.directory = directory;
        }

        @Override
        protected List<File> compute() {
            List<File> result = new ArrayList<>();
            if (!directory.isDirectory()) {
                return result;
            }

            File[] files = directory.listFiles();

            if (files == null) {
                return result;
            }

            if (files.length > LARGE_FROM) {
                result.add(directory);
            }

            List<FindLargeDirectoryTask> subTasks = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    FindLargeDirectoryTask subTask = new FindLargeDirectoryTask(file);
                    subTask.fork();
                    subTasks.add(subTask);
                }
            }

            for (FindLargeDirectoryTask subTask : subTasks) {
                result.addAll(subTask.join());
            }

            return result;
        }
    }

    static class FileSearchTask extends RecursiveTask<List<File>> {
        private final File directory;
        private final long sizeThreshold;
        private final String extension;

        FileSearchTask(File directory, long sizeThreshold, String extension) {
            this.directory = directory;
            this.sizeThreshold = sizeThreshold;
            this.extension = extension;
        }

        @Override
        protected List<File> compute() {
            List<File> result = new ArrayList<>();

            if (directory.isFile()) {
                if (directory.length() > sizeThreshold && directory.getName().endsWith(extension)) {
                    result.add(directory);
                    return result;
                }
            }

            List<FileSearchTask> subTasks = new ArrayList<>();
            File[] files = directory.listFiles();

            if (files == null) {
                return result;
            }

            for (File file : files) {
                if (file.isDirectory()) {
                    FileSearchTask subTask = new FileSearchTask(file, sizeThreshold, extension);
                    subTask.fork();
                    subTasks.add(subTask);
                } else if (file.length() > sizeThreshold && file.getName().endsWith(extension)) {
                    result.add(file);
                }
            }

            for (FileSearchTask subTask : subTasks) {
                result.addAll(subTask.join());
            }

            return result;
        }
    }
}
