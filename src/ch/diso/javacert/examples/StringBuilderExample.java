package ch.diso.javacert.examples;

import java.util.StringTokenizer;

public class StringBuilderExample {

    public static void main(String[] args) {
        // start building with StringBuilder
        // Docs: https://docs.oracle.com/javase/tutorial/java/data/buffers.html
        StringBuilder sb = new StringBuilder(200);
        sb.append("A long time ago..... ");
        sb.append("It is a period of civil war. ");

        // added a dot too much, delete the last one
        sb.delete(19, 20);

        // oops, forgot something, insert
        sb.insert(15, " in a galaxy far, far away");

        sb.append("Rebel spaceships, striking from a hidden base, have won (...)");
        System.out.println(sb);

        // Syntax for formatted output (https://docs.oracle.com/javase/tutorial/java/data/numberformat.html)
        // %f - float, %d - int, %t - date & time, %n - new line

        String author = "Stephen Hawking";
        int books = 15;
        System.out.printf("%s has written at least %02d books.%n", author, books);
        // %02d: 2 characters in width, with leading zeroes as necessary

        float amount = 15.3f;
        System.out.printf("Money: %+.2f $ %n", amount);
        // +: always print sign
        // .2: print two places after decimal point

        // String tokenizer
        StringTokenizer st = new StringTokenizer(sb.toString(), " ");
        System.out.printf("Nr of tokens (or words) in opening crawl: %d", st.countTokens());
    }
}
