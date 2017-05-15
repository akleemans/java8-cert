package ch.diso.javacert.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompareExample {

    public static void main(String[] args) {
        Book bookUniverse = new Book("The universe in a nutshell");
        Book bookTime = new Book("A brief history of time");
        Book bookDesign = new Book("The grand design");

        List<Book> bookshelf = new ArrayList<>();
        bookshelf.add(bookUniverse);
        bookshelf.add(bookTime);
        bookshelf.add(bookDesign);
        System.out.println("Added " + bookshelf.size() + " books to bookshelf.");

        // sort by compareTo (by title)
        bookshelf.sort(Book::compareTo);
        System.out.println("Bookshelf sorted by title: " + bookshelf);
        // we can also compare other objects
        System.out.println("A book compared with a string: " + bookTime.compareTo("example string"));

        // sort by LengthSorter-Comparator
        LengthSorter lengthSorter = new LengthSorter();
        bookshelf.sort(lengthSorter);
        System.out.println("Bookshelf sorted by title length, shortest first: " + bookshelf);
    }
}

// A book class, can directly compare books by compareTo()
class Book implements Comparable {

    private String title;

    Book(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Book)) {
            return 0;
        } else {
            // fall back to String.compareTo(), which will sort by alphabet by default
            return this.title.compareTo(((Book) o).getTitle());
        }

    }

    String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}

// Book Comparator which compares title lengths.
class LengthSorter implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        if (book1.getTitle().length() == book2.getTitle().length()) {
            // equal length or unknown
            return 0;
        } else if (book1.getTitle().length() > book2.getTitle().length()) {
            return 1;
        } else {
            return -1;
        }
    }
}
