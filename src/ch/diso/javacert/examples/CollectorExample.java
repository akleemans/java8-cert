package ch.diso.javacert.examples;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorExample {

    public static void main(String[] args) {
        // .joining() collector
        String[] names = {"Adrian", "Ben", "Claude", "Dennis", "Eric", "Ferdinand", "Eric-William"};
        System.out.println("All names in one String: " + Stream.of(names)
                .collect(Collectors.joining(", ")));

        // .averagingInt() collector
        System.out.println("Average length: " + Stream.of(names)
                .collect(Collectors.averagingInt(String::length)));

        // .toMap() collector
        System.out.println("Mapping name to length: " + Stream.of(names)
                .collect(Collectors.toMap(s -> s, String::length)));

        // .groupingBy() collector
        System.out.println("Grouping by first letter: " + Stream.of(names)
                .collect(Collectors.groupingBy(s -> s.charAt(0))));

        // .partitioningBy() collector
        Stream<String> nameStream = Stream.of(names);
        System.out.println("Partitioning by Predicate, if name is Eric: " + nameStream
                .collect(Collectors.partitioningBy(s -> s.startsWith("Eric"))));

        // trying to access already closed stream: "IllegalStateException: stream has already been operated upon or closed"
        //nameStream.forEach(System.out::println); // will compile, but throws RuntimeException
    }
}