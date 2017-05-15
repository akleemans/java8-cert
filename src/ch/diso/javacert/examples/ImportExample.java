package ch.diso.javacert.examples;

// static import of static class member
import static java.lang.Math.PI;

// import some list types form java.util
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// asterisk as import wildcard
import java.io.*;

// Some data types are in other packages
import java.util.concurrent.atomic.AtomicLong;
import java.math.BigDecimal;


public class ImportExample {
    public static void main(String[] args) {
        System.out.println("Because Math is in java.lang, it's available for free, yay! : " + Math.PI);
        System.out.println("To use it without `Math`, we'll do a static import. : " + PI);

        System.out.println("Import all the lists! \\o/");
        List<String> list = new ArrayList<>();
        list = new LinkedList<>();

        StringWriter sw = new StringWriter(200);
        sw.append("Although we're using only Stringwriter, we import the whole package...");
        System.out.println(sw);

        AtomicLong al = new AtomicLong();
        BigDecimal bd = new BigDecimal(42);
        System.out.printf("And some additional types: %d %d", al.intValue(), bd.intValue());
    }
}
