package edu.hw8.Task2;

public class Fibonacci {
    private static final int THREADS = 6;

    private Fibonacci() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void calculateNFib(int n) {
        var pool = new FixedThreadPool(THREADS);
        pool.start();

        for (int i = 0; i < n; i++) {
            final int num = i;
            pool.execute(() -> System.out.println(num + " " + fibonacci(num)));
        }

        try {
            pool.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static long fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
