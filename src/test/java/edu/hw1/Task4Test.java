package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @Test
    @DisplayName("123456")
    void testFixString1() {
        String input = "123456";
        String expected = "214365";
        assertThat(Task4.fixString(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("hTsii  s aimex dpus rtni.g")
    void testFixString2() {
        String input = "hTsii  s aimex dpus rtni.g";
        String expected = "This is a mixed up string.";
        assertThat(Task4.fixString(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("badce")
    void testFixString3() {
        String input = "badce";
        String expected = "abcde";
        assertThat(Task4.fixString(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Один символ")
    void testFixString4() {
        String input = "1";
        String expected = "1";
        assertThat(Task4.fixString(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустая строка")
    void testFixString5() {
        String input = "";
        String expected = "";
        assertThat(Task4.fixString(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("abracadabra")
    void testFixString6() {
        String input = "abracadabra";
        String expected = "baaracadrba";
        assertThat(Task4.fixString(input)).isEqualTo(expected);
    }
}
