package ch.diso.javacert.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class FileStreamExample {

    public static void main(String[] args) throws IOException {
        // We can access Files as a (new) Stream, let's do some fancy stuff
        Path path = Paths.get("/Users/kleemans");

        // Walk all files in userhome and print python files
        Files.walk(path, 1)
                .filter(p -> p.toString().endsWith(".py"))
                .forEach(System.out::println);

        // We can also specify depth. 0 = only path itself, 1 = path with children etc.
        Files.walk(path, 0)
                .forEach(p -> System.out.println("Path itself: " + p));

        Files.walk(path, 1)
                .forEach(p -> System.out.println("Path or child: " + p));

        // Instead of filtering, we can use Files.find() with a path, max depth & a BiPredicate representing path / file attributes
        Stream<Path> pathStream = Files.find(path, 1, (p, a) -> {
            return p.getFileName().toString().endsWith(".json");
        });
        //pathStream.forEach(System.out::println);
        System.out.println("All JSON files in userhome:" + pathStream.map(p -> p.getFileName().toString()).reduce("", (s1, s2) -> s1 + " " + s2));

        // More fancy stuff with File streams.
        System.out.println("All directories starting with D in userhome:");
        Files.list(path)
                .filter(Files::isDirectory)
                .map(p -> p.getFileName().toString())
                .filter(p -> p.startsWith("D"))
                .forEach(System.out::println);

        // We can even make a Stream of the content (lines) of the Files.
        System.out.println("Searching first logfile found & printing part of it:");
        Optional<Path> anyLogfile = Files.find(path, 1, (p, a) -> p.getFileName().toString().endsWith(".log"))
                .findAny();
        if (anyLogfile.isPresent()) {
            Files.lines(anyLogfile.get())
                    .filter(l -> l.contains("ERROR") || l.contains("WARN"))
                    .forEach(l -> System.out.println("- Found error or warning: " + l));
        }
    }
}
