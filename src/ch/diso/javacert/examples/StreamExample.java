package ch.diso.javacert.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Examples for the stream() API of Collections, some are from
 * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */
public class StreamExample {

    public static void main(String[] args) {
        List<String> letterCodes = Arrays.asList("a1", "a2", "b1", "c2", "c1");

        // Some basic stream operations: stream(), filter(), map(), sorted(), forEach()
        letterCodes
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        // Creating a stream directly with IntStream.range(), limit using limit()
        IntStream
                .range(5, 17)
                .limit(2)
                .forEach(System.out::println);

        // Create a stream with Stream.of(), collect elements for new list
        List<String> letterCodes2 = Stream.of("a2", "a4", "a4", "a5", "b5")
                .filter(s -> s.startsWith("a"))
                .collect(Collectors.toList());

        // change type of elements using map(), for example Integer => String
        Stream.of(2, 3, 4, 5)
                .map(i -> i.toString())
                .forEach(System.out::println);

        // reduce multiple elements to a single one using reduce()
        Integer sum = IntStream
                .range(1, 101) // start inclusive, end exclusive
                .reduce(0, Integer::sum);
        System.out.println("Sum of numbers from 1 to 100: " + sum);

        // unpack nested lists using flatMap()
        List<String> allCodes = Stream.of(letterCodes, letterCodes2)
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());

        // distinct, count
        long n = allCodes
                .stream()
                .distinct()
                .count();
        System.out.println("Found " + n + " codes.");

        // parallel streams: no defined order.
        allCodes
                .parallelStream()
                .forEach(System.out::println);

        // reduce(), collect() and forEach() are all terminal.
        // explanation: http://files.zeroturnaround.com/pdf/zt_java8_streams_cheat_sheet.pdf
    }
}
