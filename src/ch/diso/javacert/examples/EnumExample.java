package ch.diso.javacert.examples;

public enum EnumExample {
    FOO, BAR;

    public static void main(String[] args) {
        // will print the string representation of the Enum (= the enum as string)
        System.out.println(EnumExample.BAR); // BAR

        // it's also possible to print the ordinal
        System.out.println("Ordinal of BAR:" + EnumExample.BAR.ordinal()); // 1 (FOO = 0, BAR = 1)
    }
}
