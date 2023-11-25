package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private Task1() {
    }

    public static int parallelInc() {
        var sum = new AtomicInteger(0);

        Thread first = new Thread(new AtomicIncrementor(sum));
        Thread second = new Thread(new AtomicIncrementor(sum));

        first.start();
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return sum.intValue();
    }

    @SuppressWarnings("MagicNumber")
    static class AtomicIncrementor implements Runnable {
        private final AtomicInteger value;

        AtomicIncrementor(AtomicInteger value) {
            this.value = value;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                value.incrementAndGet();
            }
        }
    }
}
