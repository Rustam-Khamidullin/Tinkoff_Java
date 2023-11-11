package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task6.isSubstring;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {

    @Test
    void testValidSubstringContainedInString() {
        assertTrue(isSubstring("abcdef", "cd"));
    }

    @Test
    void testValidSubstringWithSpecialCharacter() {
        assertTrue(isSubstring("abc^def", "^"));
    }

    @Test
    void testValidSubstringWithSquareBrackets() {
        assertTrue(isSubstring("abc[]def", "[]"));
    }

    @Test
    void testValidSubstringWithBackslash() {
        assertTrue(isSubstring("abc\\def", "\\d"));
    }

    @Test
    void testValidSubstringWithCaretAndDollarSign() {
        assertTrue(isSubstring("^abcdef$", "^abcdef$"));
    }

    @Test
    void testValidSubstringWithEmptySubstring() {
        assertTrue(isSubstring("abc@def", ""));
    }

    @Test
    void testInvalidSubstring() {
        assertFalse(isSubstring("abc^def", "$"));
    }

    @Test
    void testValidSubstringWithEmptyString() {
        assertTrue(isSubstring("", ""));
    }

    @Test
    void testValidSubstringWithTwoWords() {
        assertTrue(isSubstring("abc def", "c de"));
    }
}
