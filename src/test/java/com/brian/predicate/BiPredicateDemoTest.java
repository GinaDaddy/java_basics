package com.brian.predicate;

import java.util.function.BiPredicate;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BiPredicateDemoTest {

    @Test
    public void simpleBiPredicateTest() {
        BiPredicate<String, String> predicate = (a, b) -> a.equals(b);
        boolean equal = BiPredicateDemo.isEqual("Brian", "Brian", predicate);

        assertThat(equal).isTrue();
    }

    @Test
    public void testPrdicateAnd() {
        BiPredicate<Integer, Integer> predicate1 = (i, j) -> i > j;
        BiPredicate<Integer, Integer> predicate2 = (i, j) -> i > 10 || j > 10;

        boolean andResult = predicate1.and(predicate2).test(10, 20);
        boolean orResult = predicate1.or(predicate2).test(10, 20);
        boolean negateResult = predicate1.test(10, 20);

        assertThat(andResult).isFalse();
        assertThat(orResult).isTrue();
        assertThat(negateResult).isFalse();
    }

}