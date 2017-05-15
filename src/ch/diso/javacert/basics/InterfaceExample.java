package ch.diso.javacert.basics;

public class InterfaceExample {

    public static void main(String[] args) {
        // default methods & multiple implementation:
        // We create a new KoalaBear which implements both Bear & Marsupial.
        // Which default method will be called? (See class definition below.)
        KoalaBear koalaBear = new KoalaBear();
        System.out.println("koalaBear.isBear(): " + koalaBear.isBear()); // false

        // static methods on interfaces: can be called directly, without implementing object!
        System.out.println("Marsupial.hasPouch(): " + Marsupial.hasPouch());
    }
}

interface Bear {
    // interface with default method
    default boolean isBear() {
        return true;
    }
}

interface Marsupial {
    // same default method as Bear
    default boolean isBear() {
        return false;
    }

    // static method on interface
    static boolean hasPouch() {
        return true;
    }
}

class KoalaBear implements Bear, Marsupial {
    // Would not compile without the method below:
    // "KoalaBear inherits unrelated defaults for isBear() from types Bear and Marsupial"
    // It's okay though if we provide our own method (so it's clear what should be returned):
    public boolean isBear() {
        return false;
    }
}