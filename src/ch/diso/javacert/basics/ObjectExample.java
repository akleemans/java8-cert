package ch.diso.javacert.basics;

public class ObjectExample {

    public static void main(String[] args) {
        Foo foo = new Foo();
        System.out.println(foo.toString());

        Object o = new Object();
        System.out.println(foo.equals(o));

        System.out.println(foo.hashCode());
    }

    // every class extends Object by default, so we can override some default methods
    static class Foo {
        @Override
        public String toString() {
            return "";
        }

        @Override
        public boolean equals(Object f2) {
            return this.toString().equals(f2.toString());
        }

        @Override
        public int hashCode() {
            return 3;
        }

    }

}
