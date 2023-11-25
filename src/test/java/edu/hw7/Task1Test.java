package edu.hw7;

import org.junit.jupiter.api.RepeatedTest;
import static edu.hw7.Task1.parallelInc;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @RepeatedTest(10)
    public void parallelIncTest() {
        assertEquals(20000, parallelInc());
    }
}
