package ch.diso.javacert.examples;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ExceptionsExample {

    public static void main(String[] args) {
        // try executing code which could throw exceptions with "try"
        try {
            testIO();
            testParsing();
            testSQL();
            testOwnExceptions();
        }
        // 3 standard checked exceptions: IO, Parse, SQL. We're only allowed to catch
        // these because are methods are declared to potentially throw them.
        catch (IOException e) {
        }
        // we can also catch two Exceptions with one catch clause
        catch (SQLException | ParseException e) {
            // print some information about the exception
            System.out.println("Exception thrown: " + e.getMessage());
        }
        // Only possible in this order (get evaluated that way): A more general exception
        catch (RuntimeException e) {
        }
        // Catch even more general exception (in fact, all of them).
        // Notice that without this line the code would not compile or
        // MyCheckedException would have to be caught separately.
        catch (Exception e) {
        }
        // finally: always execute
        finally {
            System.out.println("This will always be printed.");
        }
    }

    // "throws" keyword: method _could_ throw exception
    private static void testIO() throws IOException {
        System.out.println("Could throw IOException.");
    }

    private static void testParsing() throws ParseException {
        System.out.println("Could throw ParseException.");
    }

    private static void testSQL() throws SQLException {
        System.out.println("Throw an SQLException!");
        // throw a new SQLException which the caller has to handle.
        throw new SQLException("SQL error!");
    }

    // can also throw multiple exception
    private static void testOwnExceptions() throws MyCheckedException, MyUncheckedException {
        System.out.println("Could throw a custom exception.");
    }
}

class MyCheckedException extends Exception {
}

class MyUncheckedException extends RuntimeException {
}
