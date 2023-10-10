package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test{
    @Test
    @DisplayName("Правильное преобразование корректного времени в секунды")
    void testValidTimeConversion() {
        assertThat(Task1.minutesToSeconds("3:45")).isEqualTo(225);
        assertThat(Task1.minutesToSeconds("0:15")).isEqualTo(15);
    }

    @Test
    @DisplayName("Обработка некорректного времени")
    void testInvalidTime() {
        assertThat(Task1.minutesToSeconds("4:75")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("abc:xyz")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Обработка времени с нулевыми минутами и секундами")
    void testZeroTime() {
        assertThat(Task1.minutesToSeconds("0:0")).isEqualTo(0);
        assertThat(Task1.minutesToSeconds("0:00")).isEqualTo(0);
    }

    @Test
    @DisplayName("Обработка времени с секундами больше или равными 60")
    void testSecondsOutOfRange() {
        assertThat(Task1.minutesToSeconds("1:60")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("2:61")).isEqualTo(-1);
    }
}
