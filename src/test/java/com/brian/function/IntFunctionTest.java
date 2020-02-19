package com.brian.function;

import java.util.function.IntFunction;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * IntFunction takes an primitive int value.
 */
public class IntFunctionTest {

    @Test
    public void test1() {
        IntFunction<String> mapper = a -> Integer.toString(a);
        String s = mapper.apply(3);

        assertThat(s).isEqualTo("3");
    }
}
