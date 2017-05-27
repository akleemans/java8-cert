package ch.diso.javacert.examples;

import java.io.Console;
import java.util.Arrays;

public class ConsoleExample {

    public static void main(String[] args) {
        // Reading & writing to Console

        // Retrieve an instance/reference of Console Singleton.
        // This may return null if not on a text-based system!
        // Or even in eclipse / IntelliJ, because javaw is used, no console reference will be available.
        Console console = System.console();
        if (console == null) {
            System.out.println("Could not obtain console instance.");
        }

        String line = console.readLine("Please enter a line and finish with Enter: ");

        // We can get a PrintWriter (like System.out!) and write to it:
        console.writer().println("You entered: " + line);

        // Flush to make sure all content was pushed to Console.
        console.writer().flush();

        // We can also read passwords:
        char[] pw;
        pw = console.readPassword("Now please enter secret password (hidden): ");
        if (pw.equals("secret")) {
            // Writer has all methods available like print(), println(), format()...
            console.writer().print("Correct!");
        } else {
            console.writer().print("Wrong password...");
        }

        // Override char[], so that it won't be available in memory (and an attacker could dump it).
        Arrays.fill(pw, 'x');
    }
}
