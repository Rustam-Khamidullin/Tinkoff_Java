package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @Test
    @DisplayName("3524")
    void testCountK1() {
        assertThat(Task6.countK(3524)).isEqualTo(3);
    }

    @Test
    @DisplayName("6621")
    void testCountK2() {
        assertThat(Task6.countK(6621)).isEqualTo(5);
    }

    @Test
    @DisplayName("6554")
    void testCountK3() {
        assertThat(Task6.countK(6554)).isEqualTo(4);
    }

    @Test
    @DisplayName("1234")
    void testCountK4() {
        assertThat(Task6.countK(1234)).isEqualTo(3);
    }

    @Test
    @DisplayName("Предельный случай")
    void testCountK5() {
        assertThat(Task6.countK(8352)).isEqualTo(1);
    }

    @Test
    @DisplayName("Постоянная Капрекара")
    void testCountK6() {
        assertThat(Task6.countK(6174)).isEqualTo(0);
    }

    @Test
    @DisplayName("Рекурсивный вызов от 999")
    void testCountK7() {
        assertThat(Task6.countK(9998)).isEqualTo(5);
    }

}
