package edu.project1;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {
    @Test
    public void testGuessSuccessfulGuess() {
        Session session = new Session("hello");

        GuessResult result = session.guess("e");

        assertTrue(result instanceof GuessResult.SuccessfulGuess);
        assertFalse(result.toFinish());
    }

    @Test
    public void testGuessUsedLetter() {
        Session session = new Session("hello");
        session.guess("e");

        GuessResult result = session.guess("e");

        assertTrue(result instanceof GuessResult.UsedLetter);
        assertFalse(result.toFinish());
    }

    @Test
    public void testGuessWin() {
        Session session = new Session("hello");
        session.guess("h");
        session.guess("e");
        session.guess("l");

        GuessResult result = session.guess("o");

        assertTrue(result instanceof GuessResult.Win);
        assertTrue(result.toFinish());
    }

    @Test
    public void testGuessDefeat() {
        Session session = new Session("z");
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        GuessResult result = null;
        for (int i = 0; i < Session.MAX_ATTEMPTS; i++) {
            result = session.guess(String.valueOf(alphabet.charAt(i)));
        }
        assertTrue(result instanceof GuessResult.Defeat);
        assertTrue(result.toFinish());
    }

    @Test
    public void testGuessIncorrectInput1() {
        Session session = new Session("hello");

        GuessResult result = session.guess("12");

        assertTrue(result instanceof GuessResult.IncorrectInput);
        assertFalse(result.toFinish());
    }

    @Test
    public void testGuessIncorrectInput2() {
        Session session = new Session("hello");

        GuessResult result = session.guess("");

        assertTrue(result instanceof GuessResult.IncorrectInput);
        assertFalse(result.toFinish());
    }

    @Test
    public void testGuessIncorrectInput3() {
        Session session = new Session("hello");

        GuessResult result = session.guess("a b");

        assertTrue(result instanceof GuessResult.IncorrectInput);
        assertFalse(result.toFinish());
    }

    @Test
    public void testGuessQuit() {
        Session session = new Session("hello");

        GuessResult result = session.guess("quit");

        assertTrue(result instanceof GuessResult.Quit);
        assertTrue(result.toFinish());
    }

    @Test
    public void testProcess() {
        Session session = new Session("hello");
        Set<Character> expectedSet = new HashSet<>();

        assertEquals(session.getLettersToGuess(), session.getHiddenWord().length());
        assertEquals(session.getUsedLetter(), expectedSet);

        session.guess("e");
        expectedSet.add('e');
        assertEquals(session.getAttempts(), 0);
        assertEquals(session.getHiddenWord().toString(), "*e***");
        assertEquals(session.getUsedLetter(), expectedSet);
        assertEquals(session.getLettersToGuess(), 4);

        session.guess("z");
        expectedSet.add('z');
        assertEquals(session.getAttempts(), 1);
        assertEquals(session.getHiddenWord().toString(), "*e***");
        assertEquals(session.getUsedLetter(), expectedSet);
        assertEquals(session.getLettersToGuess(), 4);

        session.guess("e");
        assertEquals(session.getAttempts(), 1);
        assertEquals(session.getHiddenWord().toString(), "*e***");
        assertEquals(session.getUsedLetter(), expectedSet);
        assertEquals(session.getLettersToGuess(), 4);

        session.guess("l");
        expectedSet.add('l');
        assertEquals(session.getAttempts(), 1);
        assertEquals(session.getHiddenWord().toString(), "*ell*");
        assertEquals(session.getUsedLetter(), expectedSet);
        assertEquals(session.getLettersToGuess(), 2);

        session.guess("z");
        assertEquals(session.getAttempts(), 1);
        assertEquals(session.getHiddenWord().toString(), "*ell*");
        assertEquals(session.getUsedLetter(), expectedSet);
        assertEquals(session.getLettersToGuess(), 2);

        session.guess("H");
        expectedSet.add('h');
        assertEquals(session.getAttempts(), 1);
        assertEquals(session.getHiddenWord().toString(), "hell*");
        assertEquals(session.getUsedLetter(), expectedSet);
        assertEquals(session.getLettersToGuess(), 1);

        session.guess("O");
        expectedSet.add('o');
        assertEquals(session.getAttempts(), 1);
        assertEquals(session.getHiddenWord().toString(), "hello");
        assertEquals(session.getUsedLetter(), expectedSet);
        assertEquals(session.getLettersToGuess(), 0);
    }
}
