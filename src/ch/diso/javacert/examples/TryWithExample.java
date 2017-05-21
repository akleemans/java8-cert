package ch.diso.javacert.examples;

import java.util.Scanner;

public class TryWithExample {

    public static void main(String[] args) {
        // "try with"-clause. All resources opened here will be closed automatically.
        try (Scanner scanner = new Scanner(System.in);
             House house = new House()) {
            System.out.println("Please enter new name!");
            String name = scanner.nextLine();
            house.setName(name);
        }
        // No "catch" or "finally" block required, because the internal "finally" block
        // will be called to close the opened resources.
        finally {
            // Our own finally-block, will be executed after the internal finally (with close()) block.
            System.out.println("Self-defined finally-block, house should already be closed.");
        }
    }
}

class House implements AutoCloseable {
    private String name;

    // Implementing close() from AutoCloseable interface.
    // This will be called after the try block, before execution of finally block.
    // We could even throw an Exception, if resource cannot be closed.
    @Override
    public void close() {
        System.out.println("Finished, closing door of " + name);
    }

    public House() {
        // Constructor will be called in "try with" clause.
        System.out.println("Building new house!");
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Setting new name: " + name);
    }
}
