package com.brian.concurrent.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The difference is that the wait time period applies between the end of a task and the start of the next task.
 * In this case, this job delays 2 seconds between each task.
 */
public class ScheduleExecutorFixedDelayDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + System.nanoTime());
            } catch (InterruptedException e) {
                System.err.println("Task interrupted");
            }
        };

        int initalDelay = 0;
        int delay = 1;
        executor.scheduleWithFixedDelay(task, initalDelay, delay, TimeUnit.SECONDS);
    }
}
