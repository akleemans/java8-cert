package ch.diso.javacert.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamExample2 {

    public static void main(String[] args) {
        // firing up an infinite stream
        String str = Stream.generate(() -> "foobar")
                .limit(5)
                .skip(2)
                .peek(System.out::println)
                .reduce(String::concat)
                .orElse("");
        System.out.println("Str: " + str);

        // other infinite stream by .infinite(), using .skip() & .peek()
        boolean includes49 = Stream.iterate(0, n -> n + 7)
                .limit(30)
                .anyMatch(p -> p == 49);
        System.out.println("Stream includes 49:" + includes49);

        // lazy evaluation: stream gets evaluated when it is used, not immediately
        System.out.print("Printing list: ");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        Stream<String> stream = list.stream();
        list.add("c");
        stream.forEach(System.out::print);

        // primitive streams
        IntStream someNumbers = IntStream.of(14, 16, 22, 87, 55, 60, 31);
        System.out.println("\nAverage: " + someNumbers.average());
        // average() returns an  OptionalDouble[40.714285714285715]

        // infinite streams with .iterate(), and print all 4-digit numbers
        LongStream.iterate(1, d -> d * 2)
                .limit(100)
                .filter(d -> ("" + d).length() == 4)
                .forEach(System.out::println);

        // DoubleStream by DoubleSupplier (Math.random), printing first number over 0.9
        DoubleStream doubleStream = DoubleStream.generate(Math::random);
        doubleStream.filter(d -> (d > 0.9))
                .limit(1)
                .forEach(System.out::println);
    }
}
