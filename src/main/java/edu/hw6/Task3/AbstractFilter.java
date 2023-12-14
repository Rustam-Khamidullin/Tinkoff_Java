package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    Logger LOGGER = Logger.getLogger(AbstractFilter.class.getName());

    static AbstractFilter largerThan(long size) {
        return (path) -> Files.readAttributes(path, BasicFileAttributes.class).size() > size;
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static AbstractFilter extension(String extension) {
        return (path) -> path.toString().endsWith("." + extension);
    }

    static AbstractFilter regexName(String regex) {
        return (path) -> path.getFileName().toString().matches(regex);
    }

    static AbstractFilter magicNumber(byte... magicBytes) {
        return (path) -> {
            try (var inputStream = Files.newInputStream(path)) {
                byte[] fileBytes = new byte[magicBytes.length];
                if (inputStream.readNBytes(fileBytes, 0, magicBytes.length) != magicBytes.length) {
                    return false;
                }
                return Arrays.equals(magicBytes, fileBytes);
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Exception while processing file: " + path, e);
            }
            return false;
        };
    }

    default AbstractFilter and(AbstractFilter other) {
        return (path) -> this.accept(path) && other.accept(path);
    }
}


