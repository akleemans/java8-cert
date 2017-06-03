package ch.diso.javacert.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileAttributesViewExample {

    public static void main(String[] args) {
        // Create path & get FileView.
        Path path = Paths.get("/Users/kleemans");

        // We can get the FileAttributes via Files.readAttributes().
        // The base class is BasicFileAttributes and can be extended by a OS dependent implementation,
        // e.g. DosFileAttributes (Windows) or PosixFileAttributes (Linux, Mac etc.)
        try {
            BasicFileAttributes metadata = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println("Type of FileAttributes: " + metadata.getClass().getSimpleName());

            System.out.println("\nSome attributes: ");
            System.out.println("creationTime: " + metadata.creationTime());
            System.out.println("lastModifiedTime: " + metadata.lastModifiedTime());
            System.out.println("lastAccessTime: " + metadata.lastAccessTime());
            System.out.println("size: " + metadata.size());
            System.out.println("isDirectory: " + metadata.isDirectory());
            System.out.println("isRegularFile: " + metadata.isRegularFile());
            System.out.println("isSymbolicLink: " + metadata.isSymbolicLink());
            System.out.println("isOther: " + metadata.isOther());
        } catch (IOException e) {
            System.out.println("Couldn't read file.");
        }

        // We can also get a FileAttributeView, which even allows editing!
        // Lets tinker with the Timestamps on the file.
        try {
            BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);

            // Prepare milliseconds since epoch
            long epochMilliseconds = System.currentTimeMillis();
            FileTime lastAccessTime = FileTime.fromMillis(epochMilliseconds);

            // Set only lastAccessTime, leave lastModifiedTime & createTime by passing null
            view.setTimes(null, lastAccessTime, null);

            // We could also get the attributes again:
            BasicFileAttributes metadata = view.readAttributes();

            System.out.println("The lastAccessTime is now: " + metadata.lastAccessTime());
        } catch (IOException e) {
            System.out.println("Couldn't read file.");
        }
    }
}
