package edu.hw9;

import edu.hw9.task1.ParallelStatsCollector;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task1Test {
    @Test
    public void testParallelStatsCollector() throws InterruptedException {
        var statsCollector = new ParallelStatsCollector();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                var arr = new double[10000000];
                for (int j = 0; j < 10000000; j++) {
                    arr[j] = j;
                }
                statsCollector.push("metric_name", arr);
            });
        }

        for (int i = 0; i < 1000; i++) {
            System.out.println(statsCollector.pop());
        }
    }
}
