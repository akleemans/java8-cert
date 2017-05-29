package ch.diso.javacert.examples;

import java.io.File;
import java.io.IOException;

public class FileExample {

    public static void main(String[] args) throws IOException {
        // Create a new file (reference), old school with java.io.File
        File file = new File("foo.txt");

        // "File" can also be a directory.
        // Notice how we now create a File with an absolute path rather than a relative like before.
        File parentDirectory = new File("/Users/kleemans/");
        System.out.println("User home is directory: " + parentDirectory.isDirectory());

        // List files in directory
        System.out.println("Number of files & directories in my user home: " + parentDirectory.listFiles().length);

        // We can also create it through a parent directory
        File file2 = new File(parentDirectory, "bar.txt");
        boolean wasCreated = file2.createNewFile();

        // Let's really 'create' the file & write it to disk.
        System.out.println("File was created: " + wasCreated);

        // Check if file exists & get name:
        System.out.println("File " + file2.getName() + " does exist: " + file2.exists());

        if (file2.exists()) {
            System.out.println("Last modified: " + file2.lastModified());
            System.out.println("Size in bytes: " + file2.length());
            System.out.println("Path: " + file2.getPath());
            System.out.println("Absolute path: " + file2.getAbsolutePath());
            System.out.println("Parent: " + file2.getParent());
        }

        // Rename file
        boolean wasRenamed = file2.renameTo(new File("foobar.txt"));
        System.out.println("Renaming file was successful: " + wasRenamed);

        // Clean up, delete file after displaying information.
        boolean wasDeleted = file2.delete();
        System.out.println("Deleting file was successful: " + wasDeleted);


    }
}
