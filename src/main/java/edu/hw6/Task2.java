package edu.hw6;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 {
    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        int cntCopy = 1;

        while (true) {
            String originalFileName = path.getFileName().toString();

            int dotIndex = originalFileName.lastIndexOf('.');
            String baseName = originalFileName.substring(0, dotIndex);
            String extension = originalFileName.substring(dotIndex);

            String copyFileName;
            if (cntCopy == 1) {
                copyFileName = baseName + " - копия" + extension;
            } else {
                copyFileName = baseName + " - копия (" + cntCopy + ")" + extension;
            }

            Path copyPath = path.resolveSibling(copyFileName);

            try {
                Files.copy(path, copyPath);
                break;
            } catch (FileAlreadyExistsException e) {
                cntCopy++;
            }
        }
    }
}
