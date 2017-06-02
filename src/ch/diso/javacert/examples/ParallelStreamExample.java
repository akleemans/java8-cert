package ch.diso.javacert.examples;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreamExample {

    public static void main(String[] args) {
        // Create a parallel Stream using .parallelStream()
        Arrays.asList(1, 2, 3, 4, 5).parallelStream()
                .forEach(System.out::println);

        // Other method: .stream().parallel()
        Stream<String> stringStream = Arrays.asList("a", "b", "c", "d").stream();
        stringStream.parallel()
                .forEach(System.out::println);
        // Both methods will create a parallel stream that has no defined order, and the numbers / Strings will likely not be in order.

        // We can use it to get a random element:
        System.out.print("Random dice throw: " + Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().findAny().get());

        // We can also force the stream to order results in the end.
        System.out.print("Numbers 0 to 5 in order: ");
        Arrays.asList(0, 1, 2, 3, 4, 5).parallelStream()
                .forEachOrdered(System.out::print);

        // Now use a concurrent collection (map) and a parallel stream to operate on it:
        ConcurrentMap<Integer, String> map = Stream.of("foo", "bar", "foobar", "foo1", "bar12")
                .parallel()
                .collect(Collectors.toConcurrentMap(
                        String::length, // key mapper - identity
                        k -> k, // value mapper - accumulator
                        (s1, s2) -> s1 + "," + s2) // merge function - combiner
                );

        System.out.println("Map: " + map);
        System.out.println("Type of map: " + map.getClass()); // ConcurrentHashMap

        // There are also other concurrent collectors, like groupingByConcurrent(), to put same keys
        ConcurrentMap<Integer, List<String>> map2 = Stream.of("foo", "bar", "foobar", "foo1", "bar12")
                .parallel()
                .collect(Collectors.groupingByConcurrent(String::length));

        System.out.println("Map with groupingByConcurrent: " + map2);
    }
}
