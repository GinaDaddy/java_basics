package com.brian.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
As an atomic counter which is being used by multiple threads concurrently.
In compare-and-swap operations to implement non-blocking algorithms.

https://howtodoinjava.com/java/multi-threading/atomicinteger-example/
 */
public class AtomicTest {

    @Test
    public void testAtomicInteger() {
        AtomicInteger atomicInteger = new AtomicInteger(100);

        // addAndGet()
        assertThat(atomicInteger.addAndGet(2)).isEqualTo(102);

        // getAndAdd()
        assertThat(atomicInteger.getAndAdd(2)).isEqualTo(102);
        assertThat(atomicInteger.get()).isEqualTo(104);

        // incrementAndGet()
        assertThat(atomicInteger.incrementAndGet()).isEqualTo(105);

        // getAndIncrement()
        assertThat(atomicInteger.getAndIncrement()).isEqualTo(105);
        assertThat(atomicInteger.get()).isEqualTo(106);

        // decrementAndGet();
        assertThat(atomicInteger.decrementAndGet()).isEqualTo(105);

        // getAndDecrement()
        assertThat(atomicInteger.getAndDecrement()).isEqualTo(105);
        assertThat(atomicInteger.get()).isEqualTo(104);
    }

    @Test
    public void testCompareAndSet() {
        AtomicInteger atomicInteger = new AtomicInteger(100);

        boolean isSuccess = atomicInteger.compareAndSet(100, 110);
        assertThat(atomicInteger.get()).isEqualTo(110);
        assertThat(isSuccess).isTrue();

        isSuccess = atomicInteger.compareAndSet(100, 120);
        assertThat(atomicInteger.get()).isEqualTo(110);
        assertThat(isSuccess).isFalse();
    }
}
