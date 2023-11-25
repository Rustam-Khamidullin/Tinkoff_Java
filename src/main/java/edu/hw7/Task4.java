package edu.hw7;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

public class Task4 {
    private static final int THREADS = 6;

    private Task4() {
    }

    @SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
    public static double calculatePi(long iterations) {

        long isCirclePoints = 0;
        long subIterations = iterations / THREADS + 1;
        long realIterations = subIterations * THREADS;

        var start = System.nanoTime();

        try (ExecutorService executorService = Executors.newFixedThreadPool(THREADS)) {
            var runningTasks = executorService.invokeAll(getSubtasks(subIterations));

            executorService.shutdown();

            try {
                if (!executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }

            for (var runningTask : runningTasks) {
                isCirclePoints += runningTask.get();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        var end = System.nanoTime();

        double pi = ((double) isCirclePoints * 4) / realIterations;

        System.out.println("Time: " + Duration.ofNanos(end - start).toMillis() + " ms");
        System.out.println("Result: " + pi);
        System.out.println("Error rate: " + Math.abs(Math.PI - pi));

        return pi;
    }

    @NotNull private static List<Callable<Long>> getSubtasks(long iterations) {
        List<Callable<Long>> tasks = new ArrayList<>();

        for (int j = 0; j < THREADS; j++) {
            tasks.add(() -> {
                long result = 0;

                for (int i = 0; i < iterations; i++) {
                    var x = ThreadLocalRandom.current().nextDouble() * 2 - 1;
                    var y = ThreadLocalRandom.current().nextDouble() * 2 - 1;
                    if (x * x + y * y < 1) {
                        result++;
                    }
                }
                return result;
            });
        }
        return tasks;
    }


    @SuppressWarnings({"UncommentedMain", "MagicNumber"})
    public static void main(String[] args) {
        calculatePi(1_000_000_000L);
        /*
         1_000_000_000
         6 Treads:
            Time: 1149 ms
            Result: 3.141556929716886
            Error rate: 3.5723872906956444E-5
         1 Thread:
            Time: 5280 ms
            Result: 3.1416315528583683
            Error rate: 3.889926857514325E-5

         100_000_000
         6 Treads:
            Time: 142 ms
            Result: 3.1418497371630054
            Error rate: 2.5708357321230224E-4
         1 Thread:
            Time: 538 ms
            Result: 3.1416429685835703
            Error rate: 5.0314993777167416E-5

         10_000_000
         6 Treads:
            Time: 43 ms
            Result: 3.141122571775486
            Error rate: 4.7008181430729934E-4
         1 Thread:
            Time: 70 ms
            Result: 3.1420272857972713
            Error rate: 4.3463220747819165E-4
         */
    }
}
