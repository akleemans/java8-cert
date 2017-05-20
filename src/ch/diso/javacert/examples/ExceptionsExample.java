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
        }
        // 3 checked exceptions: IO, Parse, SQL
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
        // catch an even more general exception
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
}
