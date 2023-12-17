package edu.hw10.task1;

public record MyRecord(@Min(0) int first, @Min(-100) @Max(0) int second, int third, @NotNull String string) {
}
