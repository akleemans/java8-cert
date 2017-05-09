package ch.diso.javacert.basics;

public class InheritanceExample {

    public static void main(String[] args) {
        // check which 'result' field is used
        Bar bar = new Bar();
        System.out.println("Result: " + bar.getResult());
        bar.resetResult();
        System.out.println("Result after resetting: " + bar.getResult());

        // "The instanceof operator uses the inheritance hierarchy to test whether a type is an instance of a specified class."
        System.out.println("Bar instance of Bar?: " + (new Bar() instanceof Bar)); // true
        System.out.println("Bar instance of Foo?: " + (new Bar() instanceof Foo)); // also true
    }

    static class Foo {
        // IDE warning: "Field 'result' is never used."
        protected int result;
    }

    static class Bar extends Foo {
        private int result = 10;

        void resetResult() {
            this.result = 0;
        }

        int getResult() {
            // uses result from Bar, not from Foo
            return this.result;
        }
    }
}
