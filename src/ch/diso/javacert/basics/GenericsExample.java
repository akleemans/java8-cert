package ch.diso.javacert.basics;

public class GenericsExample {

    public static void main(String[] args) {
        // Using LongAdder, which implements the 'adder'-interface
        LongAdder longAdder = new LongAdder();
        System.out.println("1L + 2L = " + longAdder.add(1L, 2L));

        // Using Counter<Type> with some different types
        Counter<String> stringCounter = new Counter<>();
        Counter<Long> longCounter = new Counter<>();

        long n1 = stringCounter.count("A", "B", "C", "D", "E");
        long n2 = longCounter.count(32L, 64L, 23L, 23L, 65L, 2L, 5L);
        System.out.println("There are " + n1 + " strings and " + n2 + " Longs.");
    }

}

// we define a generic interface, with type T
interface Addable<T> {
    long add(T number1, T number2);
}

// LongAdder implements interface adder with type Long (as T).
// Therefore, the method signature is given (Long, Long)
class LongAdder implements Addable<Long> {

    @Override
    public long add(Long number1, Long number2) {
        return number1 + number2;
    }
}

// Generic class which can count any object types.
// conventional abbreviations: T - Type, E - Element, K - Key, V - Value
class Counter<T> {

    long count(T... objects) {
        return objects.length;
    }
}