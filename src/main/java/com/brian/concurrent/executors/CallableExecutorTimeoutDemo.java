package com.brian.concurrent.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Callable Executor timeout demo.
 */
public class CallableExecutorTimeoutDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 123;
        });

        try {
            System.out.println(future.isDone() ? "Future is done": "Future is not done");
            Integer result = future.get(1L, TimeUnit.SECONDS);
            System.out.println(future.isDone() ? "Future is done": "Future is not done");

            System.out.println("result " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
