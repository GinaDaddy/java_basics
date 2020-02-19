package com.brian.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectorsTest {

    @Test
    public void groupBy() {
        List<String> names = Arrays.asList("Jon", "Ajeet", "Steve", "Ajeet", "Jon", "Ajeet");
        Map<String, Long> map = names.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        assertThat(map).extractingByKey("Jon").isEqualTo(2L);
        assertThat(map).extractingByKey("Ajeet").isEqualTo(3L);
        assertThat(map).extractingByKey("Steve").isEqualTo(1L);
    }
}
