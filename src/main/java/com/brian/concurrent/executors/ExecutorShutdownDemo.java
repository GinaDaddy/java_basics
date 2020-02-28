package com.brian.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Shutdown executor demo.
 * change the sleep time from 1, 5, and 6 seconds and check the outputs.
 */
public class ExecutorShutdownDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                String name = Thread.currentThread().getName();
                TimeUnit.SECONDS.sleep(6);
                System.out.println("Hello " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            System.out.println("Attempt to shutdown the executor");
            executor.shutdown();
            executor.awaitTermination(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("task interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("Cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished.");
        }

    }
}
