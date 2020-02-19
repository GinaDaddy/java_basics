package com.brian.predicate;

import java.util.function.BiPredicate;

public class BiPredicateDemo {
    public static boolean isEqual(String a, String b, BiPredicate<String, String> predicate) {
        return predicate.test(a, b);
    }
}
