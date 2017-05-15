package ch.diso.javacert.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Some examples with Optionals, straight from
 * https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html.
 */
public class OptionalExample {

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(3);
        values.add(5);
        values.add(7);

        // create some optionals
        Optional<Integer> overFour = values.stream().filter(i -> i > 4).findFirst();
        Optional<Integer> evenNumber = values.stream().filter(i -> i % 2 == 0).findFirst();

        // check if optional is present with isPresent()
        if (overFour.isPresent()) {
            System.out.println("Found an Integer bigger than 4, value on overFour optional is present!");
        }

        // get()
        if (overFour.isPresent()) {
            System.out.println("Value is: " + overFour.get());
        }

        // orElse()
        System.out.println("Value of evenNumber (-1 if nothing found): " + evenNumber.orElse(-1));

        // empty Optional
        Optional<Integer> emptyOptional = Optional.empty();

        // create a new Optional with static Optional.of()
        Optional<Integer> optionalOne = Optional.of(1);

        // filter() will return an empty Optional if value doesn't match given predicate
        Optional<Integer> optionalThree = optionalOne.filter(i -> i == 3);

        // lambda on ifPresent() with a consumer
        optionalThree
                .ifPresent(three -> {
                    System.out.println("Found a three! : " + three);
                });

        // throw exception/throwable if no value on optional with orElseThrow()
        try {
            System.out.println("optionalThree: " + optionalThree.orElseThrow(IllegalArgumentException::new));
        } catch (RuntimeException e) {
            System.out.println("There was an exception, no value found on optionalThree...");
        }

    }

}
