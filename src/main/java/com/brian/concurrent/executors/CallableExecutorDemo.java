package com.brian.concurrent.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable Executor demo. Callable returns result while Runnable returns void.
 */
public class CallableExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            return 123;
        });

        try {
            System.out.println(future.isDone() ? "Future is done": "Future is not done");
            Integer result = future.get();
            System.out.println(future.isDone() ? "Future is done": "Future is not done");

            System.out.println("result " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
