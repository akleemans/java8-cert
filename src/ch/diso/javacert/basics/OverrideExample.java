package ch.diso.javacert.basics;

public class OverrideExample {

    public static void main(String[] args) {
        Foo foo = new Foo();
        Foo bar = new Bar();
        foo.sayHello();
        // even bar was declared as Foo, sayHello() from Bar will be called
        bar.sayHello();
    }

}

class Foo {
    void sayHello() {
        System.out.print("\nHello.");
    }
}

class Bar extends Foo {
    // has to be at least with the same access; private sayHello() would not be allowed
    @Override
    void sayHello() {
        // call sayHello() of superclass
        super.sayHello();
        System.out.print("...and also hi!");
    }
}