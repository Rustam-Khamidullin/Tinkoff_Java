package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Массив a1 вложен в массив a2")
    void testIsNestableTrue() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {0, 6};
        assertThat(Task3.isNestable(a1, a2)).isTrue();

        int[] a3 = {3, 1};
        int[] a4 = {4, 0};
        assertThat(Task3.isNestable(a3, a4)).isTrue();
    }

    @Test
    @DisplayName("Массив a1 не вложен в массив a2")
    void testIsNestableFalse() {
        int[] a1 = {9, 9, 8};
        int[] a2 = {8, 9};
        assertThat(Task3.isNestable(a1, a2)).isFalse();

        int[] a3 = {1, 2, 3, 4};
        int[] a4 = {2, 3};
        assertThat(Task3.isNestable(a3, a4)).isFalse();
    }

    @Test
    @DisplayName("Массивы a1 и a2 пусты")
    void testEmptyArrays() {
        int[] a1 = {};
        int[] a2 = {};
        assertThat(Task3.isNestable(a1, a2)).isFalse();
    }

    @Test
    @DisplayName("Массив a1 не вложен в себя")
    void testSameArray() {
        int[] a1 = {1, 2, 3, 4};
        assertThat(Task3.isNestable(a1, a1)).isFalse();
    }

    @Test
    @DisplayName("Массив a2 пустой")
    void testEmptyArrayA2() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {};
        assertThat(Task3.isNestable(a1, a2)).isFalse();
    }
}
