package com.brian.concurrent.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;

/**
 * https://winterbe.com/posts/2015/05/22/java8-concurrency-tutorial-atomic-concurrent-map-examples/
 * https://github.com/winterbe/java8-tutorial/blob/master/src/com/winterbe/java8/samples/concurrent/ConcurrentHashMap1.java
 *
 * All of those methods use a common first argument called parallelismThreshold.
 * This threshold indicates the minimum collection size when the operation should be executed in parallel.
 * E.g. if you pass a threshold of 500 and the actual size of the map is 499 the operation will be performed sequentially on a single thread.
 */
public class ConcurrentMapDemo {
    public static void main(String[] args) {
        System.out.println("Current my PC Parallelism: " + ForkJoinPool.getCommonPoolParallelism()); // 11

        testForEach();
        testSearch();
        testReduce();
    }

    /**
     * The method reduce() already known from Java 8 Streams accepts two lambda expressions of type BiFunction.
     * The first function transforms each key-value pair into a single value of any type.
     * The second function combines all those transformed values into a single result, ignoring any possible null values.
     */
    private static void testReduce() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        String reduced = map.reduce(1,
            (key, value) -> key + "=" + value,
            (s1, s2) -> s1 + "," + s2);
        System.out.println("Reduced: " + reduced);
    }

    /**
     * The method search() accepts a BiFunction returning a non-null search result for the current key-value pair or null
     * if the current iteration doesn't match the desired search criteria.
     * As soon as a non-null result is returned further processing is suppressed.
     * Keep in mind that ConcurrentHashMap is unordered.
     * The search function should not depend on the actual processing order of the map.
     * If multiple entries of the map match the given search function the result may be non-deterministic.
     */
    private static void testSearch() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        System.out.println("\nsearch()\n");

        String result1 = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if (key.equals("foo") && value.equals("bar")) {
                return "foobar";
            }
            return null;
        });

        System.out.println(result1);

        System.out.println("\nsearchValues()\n");

        String result2 = map.searchValues(1, value -> {
            System.out.println(Thread.currentThread().getName());
            if (value.length() > 3) {
                return value;
            }
            return null;
        });

        System.out.println(result2);
    }

    private static void testForEach() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        /**
         * parallelismThreshold 1 means if the collection size is less than 1, then use a single thread which means I will use muti thread for this.
         * In reference, the current parallelism for this pc is 11.
         * While when you use 5 as a parallelism, and the collection size is 4, that means it will use single thread for this.
         */
        map.forEach(1, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));
//        map.forEach(5, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));

        System.out.println(map.mappingCount());
    }

}
