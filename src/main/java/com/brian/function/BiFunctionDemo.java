package com.brian.function;

import java.util.function.BiFunction;

/**
 * BiFunction takes two args, then convert it.
 */
public class BiFunctionDemo {
    public static <T, U, R> R map(T t, U u, BiFunction<T, U, R> function) {
        return function.apply(t, u);
    }
}
