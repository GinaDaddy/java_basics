package com.brian.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import com.brian.concurrent.ConcurrentUtils;

/**
 * https://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
 * The class ReentrantLock is a mutual exclusion lock with the same basic behavior as the implicit monitors accessed via the synchronized keyword
 * but with extended capabilities.
 * As the name suggests this lock implements reentrant characteristics just as implicit monitors.
 */
public class ReentrantLockDemo {
    ReentrantLock lock = new ReentrantLock();
    int count = 0;

    /**
     * A lock is acquired via lock() and released via unlock().
     * It's important to wrap your code into a try/finally block to ensure unlocking in case of exceptions.
     * This method is thread-safe just like the synchronized counterpart.
     * If another thread has already acquired the lock subsequent calls to lock() pause the current thread until the lock has been unlocked.
     * Only one thread can hold the lock at any given time.
     */
    void increment() {
        lock.lock();
        try {
            count ++;
        } finally {
            lock.unlock();
        }
    }

    void execute() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> executor.submit(this::increment));

        ConcurrentUtils.stop(executor);
        System.out.println(count);
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        demo.execute();
    }
}
