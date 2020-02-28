package com.brian.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * https://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
 */
public class SynchronizeDemo {
    static int count = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> executor.submit(SynchronizeDemo::increment));

        ConcurrentUtils.stop(executor);

        System.out.println(count);
    }

    synchronized static void increment() {
        count ++;
    }
}
