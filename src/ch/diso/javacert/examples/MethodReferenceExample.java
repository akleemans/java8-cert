package ch.diso.javacert.examples;

import java.util.function.*;

public class MethodReferenceExample {

    public static void main(String[] args) {
        Chair normalChair = new Chair(4);
        Chair weirdChair = new Chair(2);
        Chair milkingStool = new Chair(1);

        /*
        There are four formats for method references:
            Static methods
            Instance methods on a particular instance
            Instance methods on an instance to be determined at runtime
            Constructors
         */

        // 1. Static method reference (on class)
        // As a Chair is defined to be Furniture, this will always return true.
        Predicate<Chair> isFurniture = Chair::isFurniture;
        System.out.println("isFurniture: " + isFurniture.test(normalChair));

        // 2. Instance methods on a particular instance
        // Prepare a method reference to hasMoreLegs from milkingStool.
        // Any Chair with more than 1 leg will test positive!
        Predicate<Chair> hasMoreLegs = milkingStool::hasMoreLegs;
        System.out.println("hasMoreLegs: " + hasMoreLegs.test(normalChair));

        // 3.Instance method on an instance to be determined at runtime
        // Tests a Chair if stable (legs >= 3).
        Predicate<Chair> isStable = Chair::isStable;
        System.out.println("test normalChair if stable: " + isStable.test(normalChair));
        System.out.println("test weirdChair if stable: " + isStable.test(weirdChair));

        // 4. Constructor method reference
        // Create a Supplier<T> with reference to Constructor.
        Supplier<Chair> chairSupplier = Chair::new;
        Chair defaultChair = chairSupplier.get();
        System.out.println("Default chair has this many legs: " + defaultChair.getLegs());
    }
}

// Marker interface: no methods, just implementing a concept.
interface Furniture {
}

class Chair implements Furniture {
    private final int legs;

    public static boolean isFurniture(Chair chair) {
        return true;
    }

    public Chair() {
        this.legs = 4;
    }

    public Chair(int legs) {
        this.legs = legs;
    }

    public boolean isStable() {
        return legs >= 3;
    }

    public int getLegs() {
        return legs;
    }

    public boolean hasMoreLegs(Chair chair) {
        return this.legs - chair.getLegs() >= 0;
    }
}