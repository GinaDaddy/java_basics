package com.brian.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * UnaryOperator extends Function. It accepts one operand and returns a value of the same.
 * BinaryOperator accepts two operand of the same type and returns the result of the same type as operand.
 * It is a convenient function for Function.
 */
public class UnaryBinaryOperatorTest {

    @Test
    public void unaryOeratorTest() {
        UnaryOperator<Integer> unaryOpt = i -> i*i;
        List<Integer> list = Arrays.asList(1, 2, 3);

        List<Integer> uniList = new ArrayList<>();
        list.forEach(i -> uniList.add(unaryOpt.apply(i)));

        assertThat(uniList).containsOnly(1, 4, 9);
    }

    @Test
    public void binaryOperatorTest() {
        Map<String, String> map = new HashMap<>();
        map.put("x", "a");
        map.put("y", "b");
        map.put("z", "c");

        BinaryOperator<String> binaryOperator = (s1, s2) -> s1+"-"+s2;

        List<String> bList = new ArrayList<>();
        map.forEach((s1, s2) -> bList.add(binaryOperator.apply(s1, s2)));

        assertThat(bList).containsExactly("x-a", "y-b", "z-c");
    }
}
