package ch.diso.javacert.examples;

public class InheritanceExample2 {

    public static void main(String[] args) {
        A a = new B();

        // Because the overridden method is invoked, this will print 999 from class B.
        a.printResult(); // 999

        // (!) The behaviour with instance variables is different: This will print 1 from class A!
        System.out.println(a.result); // 1

        // To print `result` from B, it must be declared as B.
        B b = new B();
        System.out.println(b.result); // 999

        // static methods can't be overridden
        B c1 = new C();
        c1.staticMethod(); // B

        C c2 = new C();
        c2.staticMethod(); // C
    }
}

abstract class A {
    int result = 1;

    void printResult() {
        System.out.println(result);
    }
}

class B extends A {
    int result = 999;

    @Override
    void printResult() {
        System.out.println(result);
    }

    static void staticMethod() {
        System.out.println("I'm from B...");
    }
}

class C extends B {
    // C has its own staticMethod(), but
    static void staticMethod() {
        System.out.println("I'm actually from C...");
    }
}