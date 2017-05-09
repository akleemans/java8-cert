package ch.diso.javacert.basics;

public class OverrideExample2 {

    public static void main(String[] args) {
        AnyNumber n = new AnyLong();
        System.out.println(n.calculate());
    }

}

abstract class AnyNumber {
    abstract protected Number calculate();
}

class AnyLong extends AnyNumber {
    // Access modifiers can be less restrictive (=more open).
    // Return types can be subtype of original method.
    @Override
    public Long calculate() {
        return 2L;
    }
}