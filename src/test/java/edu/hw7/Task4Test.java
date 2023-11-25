package edu.hw7;

import org.junit.jupiter.api.Test;
import static edu.hw7.Task4.calculatePi;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    public void calculatePiTest() {
        assertTrue(Math.abs(Math.PI - calculatePi(10_000_000_000L)) < 0.001);
    }
}
