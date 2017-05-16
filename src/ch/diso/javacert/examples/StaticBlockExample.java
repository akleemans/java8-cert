package ch.diso.javacert.examples;

import java.util.ArrayList;
import java.util.List;

public class StaticBlockExample {

    public static void main(String[] args) {
        // Initialize two instances and compare how static block,
        // non-static block & constructor code gets executed (and in what order).
        BlockClass blockClass1 = new BlockClass();
        BlockClass blockClass2 = new BlockClass();
    }
}

final class BlockClass {

    final static long firstFibonacciNumber;

    final List<Long> fibonacciNumbers = new ArrayList<>();

    // Static block which gets executed if class is initialized.
    static {
        System.out.println("Setting static first fibonacci number.");
        firstFibonacciNumber = 1;
    }

    // Non-static block which gets executed every time an instance of the class is created.
    // Note: The code gets executed before the constructor!
    {
        System.out.println("Preparing fibonacci numbers for instance!");
        fibonacciNumbers.add(1L);
        fibonacciNumbers.add(1L);
        fibonacciNumbers.add(2L);
        fibonacciNumbers.add(3L);
        fibonacciNumbers.add(5L);
        fibonacciNumbers.add(8L);
        fibonacciNumbers.add(13L);
    }

    BlockClass() {
        System.out.println("In constructor, already " + fibonacciNumbers.size() + " fibonacci numbers known.");
    }
}