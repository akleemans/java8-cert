package ch.diso.javacert.examples;

import java.sql.*;
import java.time.LocalDate;

public class JDBCExample {

    public static void main(String[] args) {
        /*
        4 important JDBC interfaces:
        - Driver
        - Connection
        - Statement
        - ResultSet
         */
        String postgres = "jdbc:postgres://localhost:5432/brikks";
        String oracle = "jdbc:oracle:thin:@1.1.1.1:1521:mydb";
        String mysql = "jdbc:mysql://localhost:3306/mydb";

        // Trying to open connection to PostgreSQL, may need .jar with drivers
        try (Connection connection = DriverManager.getConnection(postgres)) {
            System.out.println("Connection: " + connection);
        } catch (SQLException e) {
            // If missing drivers, will print "No suitable driver found for [JDBC URL]"
            System.out.println("SQLException: " + e);
        }

        // Can also open a connection with username / pw. Hardcoded in code, yay!
        try (Connection connection = DriverManager.getConnection(postgres, "user", "pw")) {
            System.out.println("Connection: " + connection);
        } catch (SQLException e) {
            // If missing drivers, will print "No suitable driver found for [JDBC URL]"
            System.out.println("SQLException: " + e);
        }

        // On to Statements and ResultSets!
        try (Connection connection = DriverManager.getConnection(postgres)) {

            // Prepare a statement from connection
            Statement statement = connection.createStatement();

            // With the statment, we can execute a Query on it.
            ResultSet resultSet = statement.executeQuery("SELECT id, name FROM books");

            // Now loop through ResultSet.
            // We can ask where the pointer currently is, before, on or after the results.
            // next() will return false if on last result row.
            while (resultSet.next()) {
                // We can get columns of row by column id, STARTING AT 1 (Just... why??)
                System.out.println("Book " + resultSet.getInt(1) + " has name: " + resultSet.getString(2));
            }

            // We can navigate through results, with methods like next(), forward(), first(), last(), beforeFirst(), afterLast().
            resultSet.next();

            // Executing a next query will implicitely close the last query. Could also call it manually:
            resultSet.close();
            // Note that on closing the connection, the ResultSet and also Statement will be also closed.

            // Next up is writing data. Return value will be how many rows were affected.
            // Use executeUpdate for UPDATE, INSERT, DELETE (not only for UPDATE!).
            int howManyRows = statement.executeUpdate("UPDATE books SET price = 25 WHERE price > 25");
            System.out.println("Capping price to 25$ on " + howManyRows + " rows.");

            // Can also call "raw" execute() instead of executeQuery() or executeUpdate().
            boolean a = statement.execute("SELECT COUNT(*) from books");

            // Now, getting result set...
            ResultSet bookCountResultSet = statement.getResultSet();

            // ...moving pointer to first (and only) row...
            bookCountResultSet.next();

            // ... and getting result.
            int bookCount = bookCountResultSet.getInt(0);
            System.out.println("Counting books: " + bookCount);


            // One more thing: Dates. Getting a date from the database will result in a SQLDate.
            ResultSet dateResultSet = statement.executeQuery("SELECT date_sold FROM books WHERE id = 42");
            if (resultSet.next()) {
                java.sql.Date sqlDate = dateResultSet.getDate(1);
                LocalDate localDate = sqlDate.toLocalDate();
                System.out.println("LocalDate: " + localDate);
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }
}
