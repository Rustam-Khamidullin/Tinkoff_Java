package edu.hw10;

import edu.hw10.task2.CacheProxy;
import edu.hw10.task2.FibCalculator;
import edu.hw10.task2.RecursiveFibCalculator;
import org.junit.jupiter.api.RepeatedTest;
import java.time.Duration;

public class Task2Test {
    @RepeatedTest(2)
    public void testCacheProxy() {
        FibCalculator c = new RecursiveFibCalculator();
        FibCalculator proxy = CacheProxy.create(c, FibCalculator.class);

        long start = System.nanoTime();
        System.out.println(proxy.fib(45));
        long end = System.nanoTime();
        System.out.println("Time: " + Duration.ofNanos(end - start).toMillis() + " ms");

        start = System.nanoTime();
        System.out.println(proxy.fib(45));
        end = System.nanoTime();
        System.out.println("Time: " + Duration.ofNanos(end - start).toMillis() + " ms");

        System.out.println();
    }
}
