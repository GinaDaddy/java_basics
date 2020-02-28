package com.brian.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * https://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
 * Instead of seeing a constant result count of 10000 the actual result varies with every execution of the above code.
 * The reason is that we share a mutable variable upon different threads without synchronizing the access to this variable which results in a race condition.
 */
public class RaceConditionDemo {
    static int count = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> executor.submit(RaceConditionDemo::increment));

        ConcurrentUtils.stop(executor);

        System.out.println(count);
    }

    static void increment() {
        count ++;
    }
}
