package ch.diso.javacert.basics;

public class InstanceofExample {

    public static void main(String[] args) {
        Animal crab = new Crab();
        Animal penguin = new Penguin();

        // check if instance of class
        System.out.println("crab is an Animal: " + (crab instanceof Animal));
        System.out.println("crab is a Crab: " + (crab instanceof Crab));
        System.out.println("penguin is an Animal: " + (penguin instanceof Animal));

        // this will compile, as penguin is defined as Animal
        System.out.println("penguin is a Crab: " + (penguin instanceof Crab));

        Penguin realPenguin = new Penguin();
        // THIS WILL NOT COMPILE - as there is no way for a realPenguin to become a crab!
        //System.out.println("penguin is a Crab: " + (realPenguin instanceof Crab));

        // we can also check against an interface
        System.out.println("crab has claws: " + (crab instanceof HasClaws));

        // the compiler knows that Penguin doesn't extend hasClaws, but - maybe a subclass implements it!
        System.out.println("penguin has claws: " + (penguin instanceof HasClaws));

        // this will always be true... (every object extends Object)
        System.out.println("crab is an Object: " + (crab instanceof Object));

        // ...except for this case.
        crab = null;
        System.out.println("null-crab is an Object: " + (crab instanceof Object));

    }

}

interface HasClaws {
    void pinch();
}

abstract class Animal {
    private int legs;
}

class Crab extends Animal implements HasClaws {
    @Override
    public void pinch() {
        System.out.println("Ouch, that hurt!");
    }
}

class Penguin extends Animal {
}