package ch.diso.javacert.examples;

public class CommandlineExample {

/*
    Compile Java class with:
        javac CommandlineExample.java
    Run Java class with (from within src directory):
        java ch.diso.javacert.examples.CommandlineExample arg1 arg2

    See what it prints out. Class name is not part of the arguments!
 */

    public static void main(String[] args) {
        System.out.println("Your arguments:");
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
