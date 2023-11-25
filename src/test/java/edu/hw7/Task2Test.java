package edu.hw7;

import org.junit.jupiter.api.RepeatedTest;
import java.math.BigInteger;
import static edu.hw7.Task2.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @RepeatedTest(10)
    public void factorialTest() {
        assertEquals(new BigInteger("720"), factorial(6));
        assertEquals(new BigInteger("25852016738884976640000"), factorial(23));
    }
}
