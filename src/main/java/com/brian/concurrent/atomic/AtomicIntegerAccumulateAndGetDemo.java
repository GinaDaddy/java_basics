package com.brian.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.brian.concurrent.ConcurrentUtils;

/**
 * https://winterbe.com/posts/2015/05/22/java8-concurrency-tutorial-atomic-concurrent-map-examples/
 *
 * The method accumulateAndGet() accepts another kind of lambda expression of type IntBinaryOperator.
 * We use this method to sum up all values from 0 to 1000 concurrently.
 */
public class AtomicIntegerAccumulateAndGetDemo {

    void executes() {
        AtomicInteger atomicInt = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> {
                Runnable task = () ->
                    atomicInt.accumulateAndGet(i, (n, m) -> n + m);
                executor.submit(task);
            });

        ConcurrentUtils.stop(executor);

        System.out.println(atomicInt.get());
    }

    public static void main(String[] args) {
        AtomicIntegerAccumulateAndGetDemo demo = new AtomicIntegerAccumulateAndGetDemo();
        demo.executes();
    }
}
