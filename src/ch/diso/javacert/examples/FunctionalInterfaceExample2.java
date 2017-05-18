package ch.diso.javacert.examples;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.*;

public class FunctionalInterfaceExample2 {
    public static void main(String[] args) {
        final HashMap<String, Integer> hashMap = new HashMap<>();

        // Examples for functional interfaces with method references
        Supplier<LocalDate> supplier = LocalDate::now;
        Consumer<String> consumer = System.out::println;
        BiConsumer<String, Integer> biConsumer = hashMap::put;
        Predicate<String> predicate = String::isEmpty;
        Function<String, Integer> function = String::length;
        UnaryOperator<String> unaryOperator = String::toUpperCase;
        BinaryOperator<String> binaryOperator = String::concat;

        // Examples with lambdas
        Supplier<Long> supplier2 = () -> 42L;
        Consumer<String> consumer2 = (String x) -> System.out.println("value of x: " + x);
        BiConsumer<String, Integer> biConsumer2 = (key, value) -> hashMap.put(key, value);
        Predicate<String> predicate2 = (x) -> x.equals("foobar");
        Function<String, Integer> function2 = (String s) -> {
            return 2 * s.length();
        };
        UnaryOperator<String> unaryOperator2 = (s) -> s + "_";
        BinaryOperator<String> binaryOperator2 = (String s1, String s2) -> {
            return s2 + s1;
        };
    }
}

