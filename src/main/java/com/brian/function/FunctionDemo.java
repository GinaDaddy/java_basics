package com.brian.function;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionDemo {
    public static List<String> convert(List<Integer> intList, Function<Integer, String> mapper) {
        return intList.stream()
            .map(mapper)
            .collect(Collectors.toList());
    }
}
