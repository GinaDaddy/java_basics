package com.brian.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.brian.concurrent.ConcurrentUtils;

/**
 * https://winterbe.com/posts/2015/05/22/java8-concurrency-tutorial-atomic-concurrent-map-examples/
 *
 * nternally, the atomic classes make heavy use of compare-and-swap (CAS), an atomic instruction directly supported by most modern CPUs.
 * Those instructions usually are much faster than synchronizing via locks.
 * So my advice is to prefer atomic classes over locks in case you just have to change a single mutable variable concurrently.
 *
 * By using AtomicInteger as a replacement for Integer we're able to increment the number concurrently in a thread-safe manor without synchronizing the access to the variable.
 * The method incrementAndGet() is an atomic operation so we can safely call this method from multiple threads.
 */
public class AtomicIntegerDemo {

    void executes() {
        AtomicInteger atomicInt = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> executor.submit(atomicInt::incrementAndGet));

        ConcurrentUtils.stop(executor);

        System.out.println(atomicInt.get());
    }

    public static void main(String[] args) {
        AtomicIntegerDemo demo = new AtomicIntegerDemo();
        demo.executes();
    }
}
