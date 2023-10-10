package edu.hw1;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {

    @Test
    @DisplayName("Обычные палиндромы")
    void testIsPalindromeDescendantPositive() {
        assertThat(Task5.isPalindromeDescendant(121)).isTrue();
        assertThat(Task5.isPalindromeDescendant(2332)).isTrue();
        assertThat(Task5.isPalindromeDescendant(1331331)).isTrue();
        assertThat(Task5.isPalindromeDescendant(1)).isTrue();
        assertThat(Task5.isPalindromeDescendant(0)).isTrue();
    }

    @Test
    @DisplayName("Отрицательные числа не палиндромы")
    void testIsPalindromeDescendantNegative() {
        assertThat(Task5.isPalindromeDescendant(-121)).isFalse();
        assertThat(Task5.isPalindromeDescendant(-2332)).isFalse();
    }

    @Test
    @DisplayName("Потомок - палидром длины один")
    void testIsPalindromeDescendantSingleLength() {
        assertThat(Task5.isPalindromeDescendant(36)).isFalse();
        assertThat(Task5.isPalindromeDescendant(1224)).isFalse();
    }

    @Test
    @DisplayName("Потомок - палиндром неединчной длины")
    void testIsPalindromeDescendantNotSingleLength() {
        assertThat(Task5.isPalindromeDescendant(11211230)).isTrue();
        assertThat(Task5.isPalindromeDescendant(13001120)).isTrue();
        assertThat(Task5.isPalindromeDescendant(23336014)).isTrue();
        assertThat(Task5.isPalindromeDescendant(994410)).isTrue();
    }
}
