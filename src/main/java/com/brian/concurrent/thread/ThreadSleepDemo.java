package com.brian.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * Sleep demo using TimeUnit
 */
public class ThreadSleepDemo {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}
