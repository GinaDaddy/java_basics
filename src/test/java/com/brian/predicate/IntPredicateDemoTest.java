package com.brian.predicate;

import java.util.function.IntPredicate;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntPredicateDemoTest {

    @Test
    public void test1() {
        IntPredicate predicate = a -> a > 0;
        boolean bool = IntPredicateDemo.eval(5, predicate);

        assertThat(bool).isTrue();
    }

    @Test
    public void testAndOr() {
        IntPredicate predicate1 = a -> a > 0;
        IntPredicate predicate2 = a -> a % 2 == 0;

        boolean booland = predicate1.and(predicate2).test(10);
        boolean boolor = predicate1.or(predicate2).test(-2);

        assertThat(booland).isTrue();
        assertThat(boolor).isTrue();
    }
}