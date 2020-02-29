package com.brian.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

import com.brian.concurrent.ConcurrentUtils;

/**
 * https://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
 *
 * Sometimes it's useful to convert a read lock into a write lock without unlocking and locking again.
 *
 * The task first obtains a read lock and prints the current value of field count to the console.
 * But if the current value is zero we want to assign a new value of 23.
 * We first have to convert the read lock into a write lock to not break potential concurrent access by other threads.
 * Calling tryConvertToWriteLock() doesn't block but may return a zero stamp indicating that no write lock is currently available.
 * In that case we call writeLock() to block the current thread until a write lock is available.
 */
public class ConvertLockDemo {
    int count = 0;

    void execute() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        executor.submit(()->{
            long stamp = lock.readLock();
            try {
                if (count == 0) {
                    stamp = lock.tryConvertToWriteLock(stamp);
                    if (stamp == 0L) {
                        System.out.println("Could not convert to write lock as there is no write lock available now");
                        stamp = lock.writeLock(); // wait till a write lock is available
                    }
                    count = 23;     // in case count == 0, convert to write lock and assign(write) a new value 23 to count
                }
                System.out.println(count);
            } finally {
                lock.unlock(stamp);
            }
        });

        ConcurrentUtils.stop(executor);
    }

    public static void main(String[] args) {
        ConvertLockDemo demo = new ConvertLockDemo();
        demo.execute();
    }
}
