package ch.diso.javacert.examples;

public class ReferenceExample {

    // defining an interface, a superclass and an Ant class, this time as inner classes
    interface HasLegs {
        default void hasLegs() {
            System.out.println("I have legs.");
        }
    }

    abstract class TinyAnimal {
        public void isTiny() {
            System.out.println("I'm tiny.");
        }
    }

    class Ant extends TinyAnimal implements HasLegs {
        public void bite() {
            System.out.println("biting!");
        }
    }

    public static void main(String[] args) {
        ReferenceExample referenceExample = new ReferenceExample();

        // object will have all methods from Ant available (object is Ant, reference is Ant too)
        Ant ant = referenceExample.new Ant();

        // Because reference is also Ant, we can access all methods which Ant has through inheritance:
        ant.isTiny();
        ant.hasLegs();
        ant.bite();

        // By redefining the reference (object stays the same!), we will lose access to some methods.
        TinyAnimal tinyAnimal = ant;
        tinyAnimal.isTiny();
        //tinyAnimal.hasLegs(); - will not compile
        //tinyAnimal.bite(); - will not compile

        HasLegs hasLegs = ant;
        //hasLegs.isTiny(); - will not compile
        hasLegs.hasLegs();
        //hasLegs.bite(); - will not compile
    }
}
