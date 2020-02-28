package com.brian.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Concurrency API introduces the concept of an ExecutorService as a higher level replacement for working with threads directly.
 * Executors are capable of running asynchronous tasks and typically manage a pool of threads, so we don't have to create new threads manually.
 * All threads of the internal pool will be reused under the hood for revenant tasks,
 * so we can run as many concurrent tasks as we want throughout the life-cycle of our application with a single executor service.
 */
public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
    }
}
