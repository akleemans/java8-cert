package ch.diso.javacert.examples;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderExample {

    public static void main(String[] args) {
        // Construct a FileReader to read a file.
        // Main difference to Streams: We can access characters (instead of bytes), but already with a certain encoding.

        // Preparing a file with one line
        File source = new File("/Users/kleemans/hanoi.txt");

        System.out.println("Writing text to file.");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(source))) {
            writer.write("Hà Nội is the capital of Vietnam.");
        } catch (IOException e) {
            throw new IllegalStateException("IOException happened, could not write file :(");
        }

        // Let's try reading the file with our own, custom (wrong) encoding (ASCII)
        Charset ascii = Charset.forName("US-ASCII");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), ascii))) {
            String line = reader.readLine();
            System.out.println("Text in file (with ASCII encoding): " + line);
        } catch (IOException e) {
            System.out.println("IOException happened while reading :(");
        }

        // Reading file with a BufferedReader around a FileReader.
        System.out.print("Now with a proper FileReader which should detect encoding by itself: ");
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            System.out.println("IOException happened while reading :(");
        }
    }
}
