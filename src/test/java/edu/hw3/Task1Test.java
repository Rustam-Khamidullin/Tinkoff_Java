package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task1.atbash;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Simple test 1")
    void testSimple1() {
        assertThat(atbash("Hello world!")).isEqualTo("Svool dliow!");
    }

    @Test
    @DisplayName("Simple test 2")
    void testSimple2() {
        String input =
            "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        String expected =
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
        assertThat(atbash(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Only uppercase")
    public void testUppercaseLetters() {
        String input = "HELLO";
        String expected = "SVOOL";
        assertThat(atbash(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Only lowercase")
    public void testLowercaseLetters() {
        String input = "world";
        String expected = "dliow";
        assertThat(atbash(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Empty String")
    public void testEmptyString() {
        String input = "";
        String expected = "";
        assertThat(atbash(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("There are no letters")
    public void testNoLetters() {
        String input = "1 2_3.4,5[7]";
        String expected = "1 2_3.4,5[7]";
        assertThat(atbash(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Null")
    public void testNull() {
        String input = null;
        String expected = null;
        assertThat(atbash(input)).isEqualTo(expected);
    }
}
