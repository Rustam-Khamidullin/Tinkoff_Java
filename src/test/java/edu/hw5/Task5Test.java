package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task5.isCorrectLicencePlate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @Test
    void testValidLicencePlate1() {
        assertTrue(isCorrectLicencePlate("А123ВЕ777"));
    }

    @Test
    void testValidLicencePlate2() {
        assertTrue(isCorrectLicencePlate("О777ОО177"));
    }

    @Test
    void testInvalidLicencePlate3() {
        assertFalse(isCorrectLicencePlate("123АВЕ777"));
    }

    @Test
    void testInvalidLicencePlateWithDifferentLetter() {
        assertFalse(isCorrectLicencePlate("А123ВГ77"));
    }

    @Test
    void testInvalidLicencePlateWithExtraDigit() {
        assertFalse(isCorrectLicencePlate("А123ВЕ7777"));
    }

    @Test
    void testInvalidLicencePlateWithSpecialCharacter() {
        assertFalse(isCorrectLicencePlate("А123ВЕ77&"));
    }

    @Test
    void testInvalidLicencePlateWithLowerCaseLetters() {
        assertFalse(isCorrectLicencePlate("а123ве777"));
    }
    @Test
    void testInvalidWithEmptyLicencePlate() {
        assertFalse(isCorrectLicencePlate(""));
    }
}
