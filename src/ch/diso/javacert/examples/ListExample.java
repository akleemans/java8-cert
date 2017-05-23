package ch.diso.javacert.examples;

import java.util.*;

public class ListExample {

    public static void main(String[] args) {

        // special case: with Arrays.asList, our List will be bound to array, no adding allowed.
        Integer[] array = {3, 2, 1, 6, 6, 5};

        List<Integer> listFromArray = Arrays.asList(array);
        // listFromArray.add(4); - will faily at runtime: UnsupportedOperationException (AbstractList.add)

        // Arrays.asList returns ArrayList
        System.out.println("Type of List we got from Arrays.asList: " + (listFromArray.getClass().getSimpleName()));

        // Change an element in array: This will reflect in list!
        array[3] = 7;
        System.out.println("listFromArray: " + listFromArray); // [3, 2, 1, 7, 6, 5]

        // sorting list
        Collections.sort(listFromArray);
        System.out.println("listFromArray sorted: " + listFromArray); // [1, 2, 3, 5, 6, 7]

        // binary search: where is element?
        // precondition: list is already sorted
        int idx = Collections.binarySearch(listFromArray, 2);
        System.out.println("2 is at position: " + idx); // 1

        // now search for a element which is not in list, which will return position where it should belong.
        // Algorithm: position of next higher element (3), negative value (-3), minus one => -4
        // other notation: (-(insertion point) - 1)
        // see docs: http://docs.oracle.com/javase/8/docs/api/java/util/Collections.html
        // "This guarantees that the return value will be >= 0 if and only if the key is found."
        idx = Collections.binarySearch(listFromArray, 4);
        System.out.println("4 should be inserted at position: " + idx); // -4

        // create a mutable list and insert number 4 with same formula
        List<Integer> mutableList = new ArrayList<>(listFromArray);
        System.out.println("New mutable list: " + mutableList);
        int insertIdx = -(idx) - 1;
        mutableList.add(insertIdx, 4);
        System.out.println("New mutable list with inserted element: " + mutableList);
    }
}
