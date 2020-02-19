package com.brian.predicate;

import java.util.function.IntPredicate;

/**
 * IntPredicate allows to use primitive int type to test
 * DoublePredicate, LongPredicate are the similar variants.
 */
public class IntPredicateDemo {
    public static boolean eval(int a, IntPredicate predicate) {
        return predicate.test(a);
    }
}
