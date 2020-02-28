package com.brian.concurrent.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
 * invokeAny() demo.
 *  Instead of returning future objects this method blocks until the first callable terminates and returns the result of that callable.
 *
 *  We use this method to create a bunch of callables with different durations from one to three seconds.
 *  Submitting those callables to an executor via invokeAny() returns the string result of the fastest callable - in that case task2:
 *
 *  The above example uses yet another type of executor created via newWorkStealingPool().
 *  This factory method is part of Java 8 and returns an executor of type ForkJoinPool which works slightly different than normal executors.
 *  Instead of using a fixed size thread-pool ForkJoinPools are created for a given parallelism size
 *  which per default is the number of available cores of the hosts CPU.
 */
public class InvokeAnyDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
            callable("task1", 2),
            callable("task2", 1),
            callable("task3", 3)
        );

        String result = executor.invokeAny(callables);
        System.out.println(result);
    }

    static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
