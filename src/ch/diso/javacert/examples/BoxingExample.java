package ch.diso.javacert.examples;

import java.util.ArrayList;
import java.util.List;

public class BoxingExample {

    public static void main(String[] args) {
        // we can have only objects in a collections, because the references are stored
        List<Integer> list = new ArrayList<>();
        int h = 1;
        // autoboxing int 1 to Integer to add to list
        list.add(h);

        // Autoboxing int 3 to Integer(3)
        Integer i = h;
        System.out.println("Value of i: " + i);
        System.out.println("Value of i, explicitly unboxed: " + i.intValue());

        // Explicit boxing
        Integer j = new Integer(3);
        System.out.println("j instanceof Integer: " + (j instanceof Integer));

        // Unboxing
        int k = i + j;
        //System.out.println("k instanceof Integer" + (k instanceof Integer)); - will not compile
    }
}
