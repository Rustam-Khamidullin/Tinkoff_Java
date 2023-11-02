package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Simple Test 1")
    public void testClusterize1() {
        String input = "()()()";
        String[] expected = {"()", "()", "()"};

        assertThat(Task2.clusterize(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Simple Test 2")
    public void testClusterize2() {
        String input = "((()))";
        String[] expected = {"((()))"};

        assertThat(Task2.clusterize(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Simple Test 3")
    public void testClusterize3() {
        String input = "((()))(())()()(()())";
        String[] expected = {"((()))", "(())", "()", "()", "(()())"};

        assertThat(Task2.clusterize(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Simple Test 4")
    public void testClusterize4() {
        String input = "((())())(()(()()))";
        String[] expected = {"((())())", "(()(()()))"};

        assertThat(Task2.clusterize(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Simple Test 5")
    public void testClusterize5() {
        String input = "()";
        String[] expected = {"()"};

        assertThat(Task2.clusterize(input)).isEqualTo(expected);
    }


    @Test
    @DisplayName("Unclusterable Test 1")
    public void testUnclusterable1() {
        String input = "(()))(()";
        String[] expected = null;

        assertThat(Task2.clusterize(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Unclusterable Test 2")
    public void testUnclusterable2() {
        String input = null;
        String[] expected = null;

        assertThat(Task2.clusterize(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Unclusterable Test 3")
    public void testUnclusterable3() {
        String input = "((a))";
        String[] expected = null;

        assertThat(Task2.clusterize(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Empty input")
    public void testEmptyInput() {
        String input = "";
        String[] expected = {};

        assertThat(Task2.clusterize(input)).isEqualTo(expected);
    }

}
