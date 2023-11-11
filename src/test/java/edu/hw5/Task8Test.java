package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task8.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    @Test
    public void testIsOddLength() {
        assertTrue(isOddLength("0"));
        assertFalse(isOddLength("00"));
        assertTrue(isOddLength("101"));
        assertFalse(isOddLength("1111"));
        assertFalse(isOddLength(""));
    }

    @Test
    public void testIsStartZeroOddLengthOrStartOneEvenLength() {
        assertTrue(isStartZeroOddLengthOrStartOneEvenLength("010"));
        assertFalse(isStartZeroOddLengthOrStartOneEvenLength("1"));
        assertTrue(isStartZeroOddLengthOrStartOneEvenLength("000"));
        assertTrue(isStartZeroOddLengthOrStartOneEvenLength("11"));
        assertFalse(isStartZeroOddLengthOrStartOneEvenLength("01"));
    }

    @Test
    public void testIsZeroMultipleThree() {
        assertTrue(isZeroMultipleThree("10010"));
        assertFalse(isZeroMultipleThree("101"));
        assertTrue(isZeroMultipleThree("11"));
        assertTrue(isZeroMultipleThree(""));
        assertFalse(isZeroMultipleThree("111001"));
        assertTrue(isZeroMultipleThree("1000110010"));
    }

    @Test
    public void testIsAllExceptSome() {
        assertTrue(isAllExceptSome("0101"));
        assertTrue(isAllExceptSome("1"));
        assertFalse(isAllExceptSome("11"));
        assertFalse(isAllExceptSome("111"));
        assertTrue(isAllExceptSome("1110"));
        assertTrue(isAllExceptSome("11011"));
        assertTrue(isAllExceptSome(""));
        assertTrue(isAllExceptSome("1111"));
    }

    @Test
    public void testIsOddEqualOne() {
        assertTrue(isOddEqualOne("101"));
        assertFalse(isOddEqualOne("1101"));
        assertTrue(isOddEqualOne("1"));
        assertFalse(isOddEqualOne("0"));
        assertTrue(isOddEqualOne(""));
    }

    @Test
    public void testIsAtLeastTwo0AndNoMoreOne1() {
        assertTrue(isAtLeastTwo0AndNoMoreOne1("0010"));
        assertFalse(isAtLeastTwo0AndNoMoreOne1("111"));
        assertTrue(isAtLeastTwo0AndNoMoreOne1("100"));
        assertFalse(isAtLeastTwo0AndNoMoreOne1("10"));
        assertFalse(isAtLeastTwo0AndNoMoreOne1("10000000010000"));
    }

    @Test
    public void testThereIsNoConsecutiveOne() {
        assertTrue(thereIsNoConsecutiveOne("010"));
        assertTrue(thereIsNoConsecutiveOne("101"));
        assertTrue(thereIsNoConsecutiveOne("1"));
        assertTrue(thereIsNoConsecutiveOne("1000"));
        assertTrue(thereIsNoConsecutiveOne("10001010101"));
        assertFalse(thereIsNoConsecutiveOne("1101"));
        assertFalse(thereIsNoConsecutiveOne("001001101000"));
    }
}
