package ch.diso.javacert.examples;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;

public class PathExample {

    public static void main(String[] args) {
        // Path: part of NIO.2 (non-blocking IO v2, introduced in Java 7).

        // Get path instance using Paths (plural as provider, like Executors, Collections, Arrays, Files etc.
        Path path = Paths.get("strings_nl.properties");

        // Can also construct a Path based on a URI (Unique Resource Identifier).
        try {
            Path uriPath = Paths.get(new URI("http://www.kleemans.ch"));
            URI uri = uriPath.toUri();
            System.out.println("Created a Path from URI: " + uri);
        } catch (URISyntaxException | FileSystemNotFoundException e) {
            System.out.println("Error creating Path from URI: " + e);
        }

        // Paths.get() is shorthand for FileSystems.getDefault().getPath()
        Path pathViaFileSystems = FileSystems.getDefault().getPath("/Users/kleemans");

        // We can work with legacy code using File:
        File file = pathViaFileSystems.toFile();

        // ..and back to path.
        Path pathFromFile = file.toPath();

        // Get the parent directory with getParent()
        System.out.println("Parent of path: " + path.getParent());

        // And some more methods:
        System.out.println("getNameCount: " + path.getNameCount());
        System.out.println("getName (for first element): " + path.getName(0));
        System.out.println("getFileName: " + path.getFileName());
        System.out.println("getRoot: " + path.getRoot());

        // We can calculate path between files. Both paths should be either realative or absolute, don't mix!
        // Also, on windows, they'll have to be on the same Drive (C:, D: etc.) to work.
        Path from = Paths.get("/Shared/Info");
        Path to = Paths.get("/Users/kleemans/Desktop/adi.jpg");
        System.out.println("Relativize path from " + from + " to " + to + ": " + from.relativize(to));

        // Other way: resolve from an absolute path and a path to "walk" as a relative path.
        // .normalize() will erase the /../ (which is just appended), another example of it follows later.
        Path toRelative = Paths.get("..");
        System.out.println("Resolve path from " + from + " with " + toRelative + ": " + from.resolve(toRelative).normalize());

        // Let's try a special case of getRoot. We can't get it from a relative path:
        Path relativePath = Paths.get(".");
        System.out.println("relativePath isAbsolute: " + relativePath.isAbsolute());
        System.out.println("relativePath getRoot: " + path.getRoot());

        Path absolutePath = relativePath.toAbsolutePath();
        System.out.println("Converted relativePath toAbsolutePath(): " + absolutePath);
        System.out.println("absolutePath isAbsolute: " + absolutePath.isAbsolute());
        System.out.println("absolutePath getRoot: " + path.getRoot());

        // Built-in support to follow SymLinks. We have to treat the checked IOException.
        try {
            System.out.println("Current parent toRealPath(): " + Paths.get("..").toRealPath());
        } catch (IOException e) {
            System.out.println("Couldn't convert parent directory to real path! " + e);
        }

        // We can also play with special path params. Printing subpaths will print path names, end index exclusive.
        Path userhome = Paths.get("/Shared/Info/../../Users/kleemans");
        Path complicatedUserhome = Paths.get("/Shared/Info/../../Users/kleemans");
        System.out.println("Print parts 1 and 2 from name: " + complicatedUserhome.subpath(1, 3));

        // Check if two files are the same. Will throw IOException if not found.
        try {
            System.out.println("Check if userhome and complicatedUserhome are the same: " + Files.isSameFile(userhome, complicatedUserhome));
        } catch (IOException e) {
            System.out.println("Couldn't check if files are the same! " + e);
        }

        // We can also resolve special path parameters, like /../ will be erased.
        System.out.println("Couldn't check if files are the same! " + complicatedUserhome.normalize());

        // Does file exist?
        System.out.println("Userhome exists: " + Files.exists(complicatedUserhome));
    }
}
