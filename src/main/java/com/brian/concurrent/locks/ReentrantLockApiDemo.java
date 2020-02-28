package com.brian.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import com.brian.concurrent.ConcurrentUtils;

/**
 * https://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
 * The method tryLock() as an alternative to lock() tries to acquire the lock without pausing the current thread.
 * The boolean result must be used to check if the lock has actually been acquired before accessing any shared mutable variables.
 */
public class ReentrantLockApiDemo {

    void execute() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock();

        executor.submit(() -> {
            lock.lock();
            try {
                ConcurrentUtils.sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean tryLock = lock.tryLock();
            System.out.println("Lock acquired: " + tryLock);
        });

        ConcurrentUtils.stop(executor);
    }

    public static void main(String[] args) {
        ReentrantLockApiDemo demo = new ReentrantLockApiDemo();
        demo.execute();
    }
}
