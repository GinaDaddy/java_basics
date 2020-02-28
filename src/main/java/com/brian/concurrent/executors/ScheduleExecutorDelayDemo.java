package com.brian.concurrent.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
 *
 * In this example, this job runs after 3 second delay.
 */
public class ScheduleExecutorDelayDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);// Delay 3 seconds before run

        TimeUnit.MILLISECONDS.sleep(1337);

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining delay: %s ms\n", remainingDelay);
    }
}
