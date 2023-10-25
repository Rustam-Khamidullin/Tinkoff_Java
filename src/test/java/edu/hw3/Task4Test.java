package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task4.convertToRoman;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Test ConvertToRoman 1256")
    public void testConvertToRoman1() {
        assertThat(convertToRoman(1256)).isEqualTo("MCCLVI");
    }

    @Test
    @DisplayName("Test ConvertToRoman 3999")
    public void testConvertToRoman2() {
        assertThat(convertToRoman(3999)).isEqualTo("MMMCMXCIX");
    }

    @Test
    @DisplayName("Test ConvertToRoman 8")
    public void testConvertToRoman3() {
        assertThat(convertToRoman(8)).isEqualTo("VIII");
    }

    @Test
    @DisplayName("Test ConvertToRoman incorrect input")
    public void testConvertToRomanIncorrectInput1() {
        assertThat(convertToRoman(-1234)).isEqualTo(null);
    }

    @Test
    @DisplayName("Test ConvertToRoman incorrect input")
    public void testConvertToRomanIncorrectInput2() {
        assertThat(convertToRoman(0)).isEqualTo(null);
    }

    @Test
    @DisplayName("Test ConvertToRoman incorrect input")
    public void testConvertToRomanIncorrectInput3() {
        assertThat(convertToRoman(4000)).isEqualTo(null);
    }
}
