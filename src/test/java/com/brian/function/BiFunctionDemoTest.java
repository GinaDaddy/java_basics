package com.brian.function;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BiFunctionDemoTest {

    @Test
    public void test1() {
        BiFunction<Integer, Integer, String> mapper = (a, b) -> String.valueOf(a + b);

        String result = BiFunctionDemo.map(1, 2, mapper);
        assertThat(result).isEqualTo("3");
    }

    @Test
    public void testAndThen() {
        BiFunction<String, String, String> mapper1 = (a, b) -> a + ":" + b;
        Function<String, String> mapper2 = String::toUpperCase;

        String result = mapper1.andThen(mapper2).apply("name", "brian");
        assertThat(result).isEqualTo("NAME:BRIAN");
    }
}