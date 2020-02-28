package com.brian.concurrent.thread;

/**
 * https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
 * Due to concurrent execution we cannot predict if the runnable will be invoked before or after printing 'done'.
 * The order is non-deterministic, thus making concurrent programming a complex task in larger applications.
 */
public class ThreadDemo {

    public static void main(String[] args) {

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run();     // Hello main

        Thread thread = new Thread(task);
        thread.start(); // Hello Thread-0

        System.out.println("Done!");
    }

}
