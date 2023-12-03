package edu.hw8.Task2;

import java.util.LinkedList;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool {
    private final int threadNumber;
    private final Thread[] pool;
    private final Queue<Runnable> tasks;
    private boolean isClosed = false;

    public FixedThreadPool(int threadNumber) {
        this.threadNumber = threadNumber;
        this.pool = new Thread[threadNumber];
        this.tasks = new LinkedList<>() {
        };
    }

    @Override
    public void start() {
        for (int i = 0; i < threadNumber; i++) {
            pool[i] = new Worker();
            pool[i].start();
        }
    }

    @Override
    public void execute(Runnable task) {
        if (isClosed) {
            return;
        }

        synchronized (tasks) {
            tasks.offer(task);
            tasks.notify();
        }
    }

    @Override
    public void close() throws InterruptedException {
        if (isClosed) {
            return;
        }

        isClosed = true;
        synchronized (tasks) {
            tasks.notify();
        }

        for (int i = 0; i < threadNumber; i++) {
            pool[i].join();
        }
    }

    public int getThreadNumber() {
        return threadNumber;
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            Runnable task;
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty() && !isClosed) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            break;
                        }
                    }

                    if (tasks.isEmpty()) {
                        return;
                    }

                    task = tasks.poll();
                }

                try {
                    task.run();
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
