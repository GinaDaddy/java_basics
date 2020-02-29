package com.brian.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.brian.concurrent.ConcurrentUtils;

/**
 * https://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
 * The interface ReadWriteLock specifies another type of lock maintaining a pair of locks for read and write access.
 * The idea behind read-write locks is that it's usually safe to read mutable variables concurrently as long as nobody is writing to this variable.
 * So the read-lock can be held simultaneously by multiple threads as long as no threads hold the write-lock.
 * This can improve performance and throughput in case that reads are more frequent than writes.
 *
 * When you execute this code sample you'll notice that both read tasks have to wait the whole second until the write task has finished.
 * After the write lock has been released both read tasks are executed in parallel and print the result simultaneously to the console.
 * They don't have to wait for each other to finish because read-locks can safely be acquired concurrently as long as no write-lock is held by another thread.
 */
public class ReadWriteLockDemo {

    void execute() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                ConcurrentUtils.sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.writeLock().unlock();
            }
        });

        Runnable task = () -> {
            lock.readLock().lock();
           try {
               System.out.println(map.get("foo"));
               ConcurrentUtils.sleep(1);
           } finally {
               lock.readLock().unlock();
           }
        };

        executor.submit(task);
        executor.submit(task);

        ConcurrentUtils.stop(executor);
    }

    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        demo.execute();
    }
}
