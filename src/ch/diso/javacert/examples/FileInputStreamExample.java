package ch.diso.javacert.examples;

import java.io.*;

public class FileInputStreamExample {

    public static void main(String[] args) {
        // Read stream of bytes: One int at a time.
        // This is opposed to FileReader / FileWriter, which reads one character at a time.
        String source = "/Users/kleemans/test.log";

        // We can use the try-with syntax, Streams are generally AutoCloseable.
        try (InputStream in = new BufferedInputStream(new FileInputStream(source))) {
            // Defining a byte array with a appropriate size.
            // Tune here (using multiples of 2 for size) to get a better performance.
            byte[] buffer = new byte[1024];
            int lengthRead = 0;

            // Read loop: read as long as length read is above 0.
            // Will return on -1 (special value).
            while ((lengthRead = in.read(buffer)) > 0) {
                System.out.println("Read a length of: " + lengthRead);
                System.out.println("First byte of buffer: " + buffer[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, abort, abort!");
        } catch (IOException e) {
            System.out.println("General IOException occured: " + e);
        }

        System.out.println("\nNow trying to use the mark() operation...");

        // Open file again, this time
        try (InputStream in = new BufferedInputStream(new FileInputStream(source))) {
            // Check if jumping around in our stream is supported with markSupported().
            if (in.markSupported()) {
                System.out.println("'Marking' on our Stream is allowd! Yaay....");

                // We now set a mark to which we can return later.
                in.mark(64);

                // Read a small portion for later.
                byte[] firstRead = new byte[16];
                in.read(firstRead);

                // When calling reset() from now on, we will return to the "marked" position.
                // Let's progress a bit in the Stream and then return.
                byte[] tempBuffer = new byte[32];
                in.read(tempBuffer);

                in.reset();
                byte[] secondRead = new byte[16];
                in.read(secondRead);
                System.out.println("Leading byte from first round = [" + firstRead[0] + "] should be equal to second = [" + secondRead[0] + "]");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found, abort, abort!");
        } catch (IOException e) {
            System.out.println("General IOException occured: " + e);
        }
    }
}
