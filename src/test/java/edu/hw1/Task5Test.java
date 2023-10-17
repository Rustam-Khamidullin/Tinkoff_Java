package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @Test
    @DisplayName("Обычные палиндромы")
    void testIsPalindromeDescendantPositive() {
        assertThat(Task5.isPalindromeDescendant(121L)).isTrue();
        assertThat(Task5.isPalindromeDescendant(2332L)).isTrue();
        assertThat(Task5.isPalindromeDescendant(1331331L)).isTrue();
        assertThat(Task5.isPalindromeDescendant(1L)).isTrue();
        assertThat(Task5.isPalindromeDescendant(0L)).isTrue();
    }

    @Test
    @DisplayName("Отрицательные числа не палиндромы")
    void testIsPalindromeDescendantNegative() {
        assertThat(Task5.isPalindromeDescendant(-1L)).isFalse();
        assertThat(Task5.isPalindromeDescendant(-121L)).isFalse();
        assertThat(Task5.isPalindromeDescendant(-2332L)).isFalse();
    }

    @Test
    @DisplayName("Потомок - палидром длины один")
    void testIsPalindromeDescendantSingleLength() {
        assertThat(Task5.isPalindromeDescendant(36L)).isFalse();
        assertThat(Task5.isPalindromeDescendant(1224L)).isFalse();
    }

    @Test
    @DisplayName("Потомок - палиндром неединчной длины")
    void testIsPalindromeDescendantNotSingleLength() {
        assertThat(Task5.isPalindromeDescendant(11211230L)).isTrue();
        assertThat(Task5.isPalindromeDescendant(13001120L)).isTrue();
        assertThat(Task5.isPalindromeDescendant(23336014L)).isTrue();
        assertThat(Task5.isPalindromeDescendant(994410L)).isTrue();
    }
}
