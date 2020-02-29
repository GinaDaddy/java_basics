package com.brian.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.brian.concurrent.ConcurrentUtils;

/**
 * https://winterbe.com/posts/2015/05/22/java8-concurrency-tutorial-atomic-concurrent-map-examples/
 *
 * AtomicInteger supports various kinds of atomic operations.
 * The method updateAndGet() accepts a lambda expression in order to perform arbitrary arithmetic operations upon the integer:
 */
public class AtomicIntegerUpdateAndGetDemo {

    void executes() {
        AtomicInteger atomicInt = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> {
                Runnable task = () ->
                    atomicInt.updateAndGet(n -> n + 2);
                executor.submit(task);
            });

        ConcurrentUtils.stop(executor);

        System.out.println(atomicInt.get());
    }

    public static void main(String[] args) {
        AtomicIntegerUpdateAndGetDemo demo = new AtomicIntegerUpdateAndGetDemo();
        demo.executes();
    }
}
