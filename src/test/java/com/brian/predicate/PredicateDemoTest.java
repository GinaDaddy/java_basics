package com.brian.predicate;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.brian.predicate.PredicateDemo;

import static org.assertj.core.api.Assertions.assertThat;

public class PredicateDemoTest {

    @Test
    public void returnAll_whenTrue() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> evalList = PredicateDemo.eval(list, n -> true);

        assertThat(evalList).containsExactly(1, 2, 3);
    }

    @Test
    public void returnAll_whenEvenNumber() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> evalList = PredicateDemo.eval(list, n -> n % 2 == 0);

        assertThat(evalList).containsExactly(2);
    }
}