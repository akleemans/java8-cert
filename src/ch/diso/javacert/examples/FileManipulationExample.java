package ch.diso.javacert.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManipulationExample {

    public static void main(String[] args) {
        final String userhome = "/Users/kleemans";

        // Create a new subdirectory using createDirectory().
        Path path = Paths.get(userhome, "newsubdir");

        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            System.out.println("Could not create directory: " + e);
        }

        // First, lets create a file.
        Path original = Paths.get(userhome, "foo.txt");
        try {
            Files.createFile(original);
        } catch (IOException e) {
            System.out.println("Could not create file: " + e);
        }

        // Can also copy File...
        Path copy = Paths.get(userhome, "bar.txt");
        try {
            Files.copy(original, copy);
        } catch (IOException e) {
            System.out.println("Could not copy file: " + e);
        }

        // ..or move file...
        Path copy2 = Paths.get(userhome, "bar2.txt");
        try {
            Files.move(original, copy2);
        } catch (IOException e) {
            System.out.println("Could not move file: " + e);
        }

        // ..or even delete it.
        try {
            Files.delete(copy);
            Files.deleteIfExists(copy2);
            Files.delete(path);
        } catch (IOException e) {
            System.out.println("Could not delete file: " + e);
        }

        System.out.println("Everything finished & cleaned up.");
    }
}
