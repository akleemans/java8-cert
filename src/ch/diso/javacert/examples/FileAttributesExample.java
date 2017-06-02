package ch.diso.javacert.examples;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;

public class FileAttributesExample {

    public static void main(String[] args) {
        // let' start with a simple Path
        Path path = Paths.get("/Users/kleemans");

        // Some methods with Files. ...() starting with type of path
        System.out.println("isRegularFile:" + Files.isRegularFile(path));
        System.out.println("isDirectory:" + Files.isDirectory(path));
        System.out.println("isSymbolicLink:" + Files.isDirectory(path));

        // some basic attributes
        try {
            System.out.println("isHidden:" + Files.isHidden(path));
        } catch (IOException e) {
        }
        System.out.println("isReadable:" + Files.isReadable(path));
        System.out.println("isExecutable:" + Files.isExecutable(path));

        // size in bytes
        try {
            System.out.println("size:" + Files.size(path));
        } catch (IOException e) {
        }

        // last modified time
        try {
            FileTime fileTime = Files.getLastModifiedTime(path);
            System.out.println("getLastModifiedTime:" + fileTime);

            // we can convert it to epoch ms
            System.out.println("converted to epoch milliseconds:" + fileTime.toMillis());
        } catch (IOException e) {
        }

        // owner & name
        try {
            UserPrincipal userPrincipal = Files.getOwner(path);
            System.out.println("getOwner:" + userPrincipal);
            System.out.println("Name of owner:" + userPrincipal.getName());
        } catch (IOException e) {
        }

        // we can also look up a user principal with a - wait for it - UserPrincipalLookupService :-)
        UserPrincipalLookupService userPrincipalLookupService = FileSystems.getDefault().getUserPrincipalLookupService();

        try {
            System.out.println("Trying to find user Adrianus...");
            UserPrincipal userPrincipal = userPrincipalLookupService.lookupPrincipalByName("Adrianus Kleemans");
            System.out.println("Found UserPrincipal: " + userPrincipal);
        } catch (IOException e) {
            System.out.println("User not found.");
        }
    }
}
