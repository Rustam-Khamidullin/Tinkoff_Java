package edu.hw9.task1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ParallelStatsCollector {
    private final static int THREADS = 6;
    private final ExecutorService executorService;
    private final BlockingQueue<MetricStats> results;

    public ParallelStatsCollector() {
        this.executorService = Executors.newFixedThreadPool(THREADS);
        this.results = new LinkedBlockingQueue<>();
    }

    public void push(String metricName, double[] values) {
        executorService.execute(() -> {
            int cnt = values.length;
            if (cnt == 0) {
                results.add(new MetricStats(metricName, 0, 0, 0, 0));
            }

            double sum = 0;
            double average;
            double min = Double.MAX_VALUE;
            double max = -Double.MAX_VALUE;

            for (var value : values) {
                sum += value;
                if (value > max) {
                    max = value;
                }
                if (value < min) {
                    min = value;
                }
            }
            average = sum / cnt;

            results.add(new MetricStats(metricName, sum, average, min, max));
        });
    }

    public MetricStats pop() throws InterruptedException {
        return results.take();
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executorService.awaitTermination(timeout, unit);
    }

    public record MetricStats(String metricName, double sum, double average, double min, double max) {
    }
}
