package ch.diso.javacert.examples;

public class AssertionExample {

    // Assertions are disabled by default. Run with
    // - java -ea AssertionExample (enable for class)
    // - java -ea:ch.diso.javacert... (enable for package & subpackages)
    public static void main(String[] args) {
        long initialHay = 5;
        HayStack stack = new HayStack(initialHay);

        // One way to put an assertion, an expression which should
        // evaluate to true and a text message.
        assert initialHay == stack.getHay() : "Didn't initialize hay stack correctly!";
        // Will throw a AssertionError (if -ea flag is set), because a HayStack does not work correctly:
        // Exception in thread "main" java.lang.AssertionError: Didn't initialize hay stack correctly
        //    at ch.diso.javacert.examples.AssertionExample.main(AssertionExample.java:14)

        // We _could_ catch this, but this is bad practice (an Error implies system failure and should not be caught).
    }
}

class HayStack {
    private long amount;

    HayStack(long amount) {
        // Another way to assert, without message.
        // This is just an example and should better checked with throwing an IllegalArgumentException.
        assert amount >= 0;
        this.amount = amount - 1;
    }

    long getHay() {
        return amount;
    }
}