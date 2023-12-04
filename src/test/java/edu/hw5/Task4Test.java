package edu.hw5;


import org.junit.jupiter.api.Test;
import static edu.hw5.Task4.isCorrectPassword;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    void testValidPasswordWithSpecialCharacter() {
        assertTrue(isCorrectPassword("abc^def"));
    }

    @Test
    void testInvalidPasswordWithoutSpecialCharacter() {
        assertFalse(isCorrectPassword("abcdef"));
    }

    @Test
    void testValidPasswordWithSpecialCharacterAtStart() {
        assertTrue(isCorrectPassword("@def"));
    }

    @Test
    void testValidPasswordWithSpecialCharacterAtEnd() {
        assertTrue(isCorrectPassword("abc%"));
    }

    @Test
    void testInvalidEmptyPassword() {
        assertFalse(isCorrectPassword(""));
    }

    @Test
    void testInvalidPasswordWithUnsupportedCharacter() {
        assertFalse(isCorrectPassword("asdf$sadfl|"));
    }
}
