package ch.diso.javacert.examples;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class PrintWriterExample {

    public static void main(String[] args) throws IOException {
        // We can wrap a PrintStream like System.out into a PrintWriter.
        PrintWriter consoleWriter = new PrintWriter(System.out);
        // We can use all overloaded functions from print(), printf()/format(), println()
        consoleWriter.printf("Printing a number to console: %.1f\n", 3.5f);
        consoleWriter.print(42L);
        consoleWriter.println();
        consoleWriter.println(LocalDateTime.now());
        consoleWriter.append("...some more text.");
        consoleWriter.flush();

        // Writing to a file. We'll have to flush or at least close
        // the resource properly to see a result in test.log.
        PrintWriter out = new PrintWriter("test.log");

        // PrintWriter method: println()
        out.println("println()");

        // Writer method: writer(). Writer just writes.
        out.write("write(test)");
        out.flush();
        out.close();

        // Constructing a PrintWriter from a BufferedWriter -> FileWriter.
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("test2.log")))) {
            // Print a new line, best done with println().
            printWriter.print("This is a new line on Linux/Mac: \n");
            printWriter.print("This is a new line on Windows: \r\n");
            printWriter.println("This always be just a new line, OS dependent.");

        }

        // TODO: Overview java.io, InputStream / Reader / OutputStream / Writer
    }
}
