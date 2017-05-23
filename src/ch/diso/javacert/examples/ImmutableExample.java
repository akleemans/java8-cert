package ch.diso.javacert.examples;

import java.util.Arrays;
import java.util.List;

public class ImmutableExample {

    public static void main(String[] args) {
        // Creating a immutable, read-only table.

        // Steps to make a class immutable:
        // 1. Use a constructor to set all properties of the object
        // 2. Mark all instance variables as private and final
        // 3. Don't define any setter methods
        // 4. Don't allow referenced mutable objects to be modified or accessed directly
        // 5. Prevent methods from being overriden

        // Initialize everything in the beginning with the constructor.
        List<String> materials = Arrays.asList("wood", "iron");
        final Table table = new Table(100, 80, 150, materials);

        // private variables are not accessible, because they're private & no setters exist
        System.out.println("Size of our table: " + table.getSize());

        // get materials - no direct access to list, but have to query objects themselves
        System.out.println("Amount of materials: " + table.getAmountOfMaterials());
        String material = table.getMaterial(0);
        System.out.println("First material is: " + material);

        // Let's try manipulating it by reference - not possible (String is immutable)
        material += " (oak)";
        System.out.println("Changed material: " + material);
        System.out.println("First material of table is: " + table.getMaterial(0));

        // Never share references to a mutable object (even if contained within a immutable object).
        // As we can't access the list directly (to maniuplate it), the object is immutable!
    }
}

final class Table {
    // 2. Mark all instance variables as private and final
    private final int height;
    private final int width;
    private final int length;

    private final List<String> materials;

    // 1. Use a constructor to set all properties of the object
    public Table(int height, int width, int length, List<String> materials) {
        this.height = height;
        this.width = width;
        this.length = length;
        this.materials = materials;
    }

    // 5. Prevent methods from being overriden
    public final String getSize() {
        return width + "x" + length + "x" + height;
    }

    public final int getAmountOfMaterials() {
        return this.materials.size();
    }

    // 4. Don't allow referenced mutable objects to be modified or accessed directly
    public final String getMaterial(int i) {
        return materials.get(i);
    }

    // 3. Don't define any setter methods
}