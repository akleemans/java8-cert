package ch.diso.javacert.examples;

import java.util.function.Predicate;

public class FunctionalInterfaceExample {

    public static void main(String[] args) {
        // let's create a new Programmer and check
        Programmer programmer = new Programmer();

        // call method above with lambda as ProgrammerProperty
        check(programmer, p -> p.drinksCoffee());

        // the same lambda expression, rewritten with type & body
        check(programmer, (Programmer p) -> {
            return p.drinksCoffee();
        });

        // using the second version with Predicate<T> which should print the same result
        checkWithPredicate(programmer, p -> p.drinksCoffee());
    }

    // a function which takes the Programmer and the functional Interface below as an argument
    public static void check(Programmer programmer, ProgrammerProperty programmerProperty) {
        if (programmerProperty.test(programmer)) {
            System.out.println("Programmer has property!");
        } else {
            System.out.println("Property not existing...");
        }
    }

    // ... and the version with the generic java.util.function.Predicate
    public static void checkWithPredicate(Programmer programmer, Predicate<Programmer> programmerProperty) {
        if (programmerProperty.test(programmer)) {
            System.out.println("Programmer has property! (checked with Predicate<T>)");
        }
    }
}

class Programmer {
    public boolean drinksCoffee() {
        return true;
    }
}

// We define a "functional Interface" with one abstract method
// which we'll use with a lambda expression.
// Later we'll just use Predicate<Programmer>, this is for educational purpose only.
@FunctionalInterface
interface ProgrammerProperty {
    boolean test(Programmer programmer);
}

//@FunctionalInterface - will not compile: "Multiple abstract methods found"
interface NoFunctionalInterface {
    boolean test1();

    boolean test2();
}
