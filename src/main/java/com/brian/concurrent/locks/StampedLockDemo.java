package com.brian.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

import com.brian.concurrent.ConcurrentUtils;

/**
 * https://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
 *
 * Java 8 ships with a new kind of lock called StampedLock which also support read and write locks.
 * In contrast to ReadWriteLock the locking methods of a StampedLock return a stamp represented by a long value.
 * You can use these stamps to either release a lock or to check if the lock is still valid.
 * Additionally stamped locks support another lock mode called optimistic locking.
 *
 * Obtaining a read or write lock via readLock() or writeLock() returns a stamp which is later used for unlocking within the finally block.
 * Keep in mind that stamped locks don't implement reentrant characteristics.
 * Each call to lock returns a new stamp and blocks if no lock is available even if the same thread already holds a lock.
 * So you have to pay particular attention not to run into deadlocks.
 *
 * Just like in the previous ReadWriteLock example both read tasks have to wait until the write lock has been released.
 * Then both read tasks print to the console simultaneously because multiple reads doesn't block each other as long as no write-lock is held.
 */
public class StampedLockDemo {

    void execute() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                ConcurrentUtils.sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable task = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println(map.get("foo"));
                ConcurrentUtils.sleep(1);
           } finally {
                lock.unlockRead(stamp);
           }
        };

        executor.submit(task);
        executor.submit(task);

        ConcurrentUtils.stop(executor);
    }

    public static void main(String[] args) {
        StampedLockDemo demo = new StampedLockDemo();
        demo.execute();
    }
}
