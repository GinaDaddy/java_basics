package com.brian.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.AllArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CollectorsTest {

    @Test
    public void groupBy() {
        List<String> names = Arrays.asList("Jon", "Ajeet", "Steve", "Ajeet", "Jon", "Ajeet");
        // Below Function.identity() is equivalent to x -> x. It might save memory.
        // https://stackoverflow.com/questions/28032827/java-8-lambdas-function-identity-or-t-t
        Map<String, Long> map = names.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        assertThat(map).extractingByKey("Jon").isEqualTo(2L);
        assertThat(map).extractingByKey("Ajeet").isEqualTo(3L);
        assertThat(map).extractingByKey("Steve").isEqualTo(1L);
    }

    /*
    PartitioningBy is a specialized case of groupingBy that accepts a Predicate instance and collects Stream elements into a Map instance
    that stores Boolean values as keys and collections as values.
     */
    @Test
    public void partitioningBy() {
        List<String> names = Arrays.asList("Jon", "Ajeet", "Steve", "Ajeet", "Jon", "Ajeet");
        Map<Boolean, List<String>> result = names.stream()
            .collect(Collectors.partitioningBy(s -> s.length() > 3));

        assertThat(result.get(true)).containsExactly("Ajeet", "Steve", "Ajeet", "Ajeet");
        assertThat(result.get(false)).containsExactly("Jon", "Jon");
    }

    @Test
    public void shouldRaise_IllegalStateException_whenKeyIsDuplicated_toMap() {
        List<String> names = Arrays.asList("Jon", "Ajeet", "Steve", "Ajeet", "Jon", "Ajeet");

        Throwable thrown = catchThrowable(() -> {
            names.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        });

        assertThat(thrown).isInstanceOf(IllegalStateException.class)
            .hasNoCause()
            .hasMessageContaining("Duplicate key");

    }

    @Test
    public void toList() {
        List<Student> studentlist = getStudents();

        List<String> names = studentlist.stream()
            .map(n -> n.name)
            .collect(Collectors.toList());

        assertThat(names).containsExactly("Jon", "Steve", "Lucy", "Sansa", "Maggie");
    }

    @Test
    public void toSet() {
        List<Student> studentlist = getStudents();
        Set<Student> students = studentlist.stream()
            .filter(n -> n.age>18)
            .collect(Collectors.toSet());

        assertThat(students).extracting("name").containsOnly("Jon", "Lucy", "Sansa");
    }

    @Test
    public void joining() {
        List<String> list = Arrays.asList("a", "b", "c");
        String result = list.stream()
            .collect(Collectors.joining(":"));

        assertThat(result).isEqualTo("a:b:c");
    }

    @Test
    public void maxBy() {
        List<String> list = Arrays.asList("a", "b", "c");
        Optional<String> result = list.stream()
            .collect(Collectors.maxBy(Comparator.naturalOrder()));

        assertThat(result.get()).isEqualTo("c");
    }

    @Test
    public void averagingInt() {
        List<Student> studentList = getStudents();
        Double avgAge = studentList.stream()
            .collect(Collectors.averagingInt(s -> s.age));

        assertThat(avgAge).isEqualTo(20.6);
    }

    private List<Student> getStudents() {
        List<Student> studentlist = new ArrayList<>();
        studentlist.add(new Student(11,"Jon",22));
        studentlist.add(new Student(22,"Steve",18));
        studentlist.add(new Student(33,"Lucy",22));
        studentlist.add(new Student(44,"Sansa",23));
        studentlist.add(new Student(55,"Maggie",18));
        return studentlist;
    }

    @AllArgsConstructor
    private class Student {
        int id;
        String name;
        int age;
    }
}

