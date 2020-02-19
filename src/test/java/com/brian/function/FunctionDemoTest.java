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
}