package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task7.isAtLeastTreeAndZeroThird;
import static edu.hw5.Task7.isLengthMoreOneLessTree;
import static edu.hw5.Task7.isStartEqualsEnd;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {

    @Test
    void testIsAtLeastTreeAndZeroThird_ValidString1() {
        assertTrue(isAtLeastTreeAndZeroThird("010101"));
    }

    @Test
    void testIsAtLeastTreeAndZeroThird_ValidString2() {
        assertTrue(isAtLeastTreeAndZeroThird("110"));
    }

    @Test
    void testIsAtLeastTreeAndZeroThird_InvalidString1() {
        assertFalse(isAtLeastTreeAndZeroThird("011"));
    }

    @Test
    void testIsAtLeastTreeAndZeroThird_InvalidString2() {
        assertFalse(isAtLeastTreeAndZeroThird("00"));
    }

    @Test
    void testIsAtLeastTreeAndZeroThird_InvalidString3() {
        assertFalse(isAtLeastTreeAndZeroThird(""));
    }

    @Test
    void testIsStartEqualsEnd_ValidString1() {
        assertTrue(isStartEqualsEnd("101"));
    }

    @Test
    void testIsStartEqualsEnd_ValidString2() {
        assertTrue(isStartEqualsEnd("1"));
    }

    @Test
    void testIsStartEqualsEnd_ValidString3() {
        assertTrue(isStartEqualsEnd("0101010101011101010"));
    }

    @Test
    void testIsStartEqualsEnd_InvalidString1() {
        assertFalse(isStartEqualsEnd(""));
    }

    @Test
    void testIsStartEqualsEnd_InvalidString2() {
        assertFalse(isStartEqualsEnd("0101"));
    }

    @Test
    void testIsStartEqualsEnd_InvalidString3() {
        assertFalse(isStartEqualsEnd("10"));
    }

    @Test
    void testIsLengthMoreOneLessTree_ValidString1() {
        assertTrue(isLengthMoreOneLessTree("1"));
    }

    @Test
    void testIsLengthMoreOneLessTree_ValidString2() {
        assertTrue(isLengthMoreOneLessTree("01"));
    }

    @Test
    void testIsLengthMoreOneLessTree_ValidString3() {
        assertTrue(isLengthMoreOneLessTree("101"));
    }

    @Test
    void testIsLengthMoreOneLessTree_InvalidString1() {
        assertFalse(isLengthMoreOneLessTree(""));
    }

    @Test
    void testIsLengthMoreOneLessTree_InvalidString2() {
        assertFalse(isLengthMoreOneLessTree("0101"));
    }
}
