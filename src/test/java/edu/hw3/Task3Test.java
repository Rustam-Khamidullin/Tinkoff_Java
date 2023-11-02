package edu.hw3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Simple Test 1")
    public void testFreqDict1() {
        List<String> input = Arrays.asList("apple", "banana", "apple", "cherry", "banana");
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("apple", 2);
        expected.put("banana", 2);
        expected.put("cherry", 1);

        assertThat(Task3.freqDict(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Simple Test 2")
    public void testFreqDict2() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 1);
        HashMap<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 3);
        expected.put(2, 2);
        expected.put(3, 2);
        expected.put(4, 2);
        expected.put(5, 1);

        assertThat(Task3.freqDict(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Simple Test 3")
    public void testFreqDict3() {
        List<Character> input = Arrays.asList('a', 'b', 'c', 'a', 'b', 'd');
        HashMap<Character, Integer> expected = new HashMap<>();
        expected.put('a', 2);
        expected.put('b', 2);
        expected.put('c', 1);
        expected.put('d', 1);

        assertThat(Task3.freqDict(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Empty list")
    public void testFreqDictEmptyList() {
        List<Character> input = List.of();
        HashMap<Character, Integer> expected = new HashMap<>();

        assertThat(Task3.freqDict(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Null")
    public void testFreqDictNull() {
        List<Character> input = null;
        HashMap<Character, Integer> expected = null;

        assertThat(Task3.freqDict(input)).isEqualTo(expected);
    }
}
