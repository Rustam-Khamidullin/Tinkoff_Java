package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Подсчет количества цифр в положительном числе")
    void testCountDigitsPositive() {
        assertThat(Task2.countDigits(12345)).isEqualTo(5);
        assertThat(Task2.countDigits(7)).isEqualTo(1);
        assertThat(Task2.countDigits(987654321)).isEqualTo(9);
    }

    @Test
    @DisplayName("Подсчет количества цифр в отрицательном числе")
    void testCountDigitsNegative() {
        assertThat(Task2.countDigits(-54321)).isEqualTo(5);
        assertThat(Task2.countDigits(-9)).isEqualTo(1);
        assertThat(Task2.countDigits(-123456789)).isEqualTo(9);
    }

    @Test
    @DisplayName("Подсчет количества цифр в нуле")
    void testCountDigitsZero() {
        assertThat(Task2.countDigits(0)).isEqualTo(1);
    }

    @Test
    @DisplayName("Подсчет количества цифр граничные значения")
    void testCountDigitsBoundaryValue() {
        assertThat(Task2.countDigits(Long.MIN_VALUE)).isEqualTo(19);
        assertThat(Task2.countDigits(Long.MAX_VALUE)).isEqualTo(19);
    }
}
