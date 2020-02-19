package com.brian.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionDemoTest {

    @Test
    public void convertIntegerListToStringList() {
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> convert = FunctionDemo.convert(intList, n -> String.valueOf(n));

        assertThat(convert).containsExactly("1", "2", "3");
    }

    /**
     * compare compose() and andThen().
     * The difference is order. andThen() follows the natural order while compose() executes the caller last.
     */
    @Test
    public void composeNandThen() {
        Function<Integer, Integer> times2 = e -> e * 2;
        Function<Integer, Integer> squared = e -> e * e;

        int compose = times2.compose(squared).apply(4);
        int andthen = times2.andThen(squared).apply(4);

        assertThat(compose).isEqualTo(32);
        assertThat(andthen).isEqualTo(64);
    }
}