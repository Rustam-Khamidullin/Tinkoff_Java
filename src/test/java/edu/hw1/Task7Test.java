package edu.hw1;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task7Test {
    @Test
    @DisplayName("rotateRight простые тесты")
    void testRotateRightSimple() {
        assertThat(Task7.rotateRight(8, 1)).isEqualTo(4);
        assertThat(Task7.rotateRight(1, 14)).isEqualTo(1);
    }

    @Test
    @DisplayName("rotateLeft простые тесты")
    void testRotateLeftSimple() {
        assertThat(Task7.rotateLeft(16, 1)).isEqualTo(1);
        assertThat(Task7.rotateLeft(17, 2)).isEqualTo(6);
        assertThat(Task7.rotateLeft(1, 3)).isEqualTo(1);
    }

    @Test
    @DisplayName("rotateRight с маленькими сдигами")
    void testRotateRight() {
        assertThat(Task7.rotateRight(43, 1)).isEqualTo(53);
        assertThat(Task7.rotateRight(43, 2)).isEqualTo(58);
        assertThat(Task7.rotateRight(43, 3)).isEqualTo(29);
        assertThat(Task7.rotateRight(43, 4)).isEqualTo(46);
        assertThat(Task7.rotateRight(43, 5)).isEqualTo(23);
        assertThat(Task7.rotateRight(43, 6)).isEqualTo(43);
    }

    @Test
    @DisplayName("rotateLeft с маленькими сдигами")
    void testRotateLeft() {
        assertThat(Task7.rotateLeft(43, 1)).isEqualTo(23);
        assertThat(Task7.rotateLeft(43, 2)).isEqualTo(46);
        assertThat(Task7.rotateLeft(43, 3)).isEqualTo(29);
        assertThat(Task7.rotateLeft(43, 4)).isEqualTo(58);
        assertThat(Task7.rotateLeft(43, 5)).isEqualTo(53);
        assertThat(Task7.rotateLeft(43, 6)).isEqualTo(43);
    }

    @Test
    @DisplayName("rotateRight с большими сдигами")
    void testRotateRightLargeShift() {
        assertThat(Task7.rotateRight(43, 6005)).isEqualTo(23);
    }

    @Test
    @DisplayName("rotateLeft с большими сдигами")
    void testRotateLeftLargeShift() {
        assertThat(Task7.rotateLeft(43, 6004)).isEqualTo(58);
    }
}
