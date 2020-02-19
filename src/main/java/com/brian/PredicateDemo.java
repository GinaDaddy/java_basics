package com.brian;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateDemo {
    public static List<Integer> eval(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream()
            .filter(predicate)
            .collect(Collectors.toList());
    }
}
