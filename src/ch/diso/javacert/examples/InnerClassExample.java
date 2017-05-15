package ch.diso.javacert.examples;

public class InnerClassExample {

    private static int a = 0;

    public static void main(String[] args) {
        doStuff();
    }

    private static void doStuff() {
        // b will be 'effectively final' (= declared final by the compiler) if code still executes
        // because we have an 'local inner class' (= inner class within method) coming up below
        int b = 1;

        // b1 can not be declared final because we change its value, can not be accessed from LocalInnerClass.
        // "Variable 'b1' is accessed from within inner class, needs to be final or effectively final"
        int b1 = 3;
        b1 = 4;

        class LocalInnerClass {
            private int c = 2;

            // 'private' is possible because it is an inner class, see http://stackoverflow.com/a/12542295/811708
            private LocalInnerClass() {
                // we can access all members of the 'enclosing' class
                System.out.printf("%d %d %d\n", a, b, c);
                //System.out.printf("%d", b1); - will not compile
            }
        }

        // instantiate new LocalInnerClass, this will call private constructor & print a, b & c
        LocalInnerClass localInnerClass = new LocalInnerClass();

        // we can even access private members of our inner class
        System.out.println("Private field 'c' of inner class:" + localInnerClass.c);
    }
}
