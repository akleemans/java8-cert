package ch.diso.javacert.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparableExample {

    public static void main(String[] args) {
        // Creating some Door objects for later comparing
        List<Door> doors = new ArrayList<>();
        doors.add(new Door(220, 105));
        doors.add(new Door(240, 100));
        doors.add(new Door(235, 105));
        doors.add(new Door(210, 95));

        System.out.println("Doors before sorting: " + doors);

        // Sort the door with Collections.sort().
        // This will trigger comparing The Doors by "native" compareTo,
        // which is possible because Door extends Comparable.
        Collections.sort(doors);
        System.out.println("Doors after sorting with compareTo (by height): " + doors);


        // We can define a new Comparator<Door> the "traditional" way in 6 lines....
        Comparator<Door> widthComparator = new Comparator<Door>() {
            @Override
            public int compare(Door door1, Door door2) {
                return Integer.compare(door1.getWidth(), door2.getWidth());
            }
        };

        // ...or use a fancy new Lambda expression with 1 line! :-)
        Comparator<Door> lambdaWidthComparator = (d1, d2) -> Integer.compare(d1.getWidth(), d2.getWidth());


        // We can write it even shorter with comparingInt() and a method reference to Door.getWidth().
        Comparator<Door> lambdaWidthComparator2 = Comparator.comparingInt(Door::getWidth);

        // Sort the door using a custom Comparator.
        Collections.sort(doors, lambdaWidthComparator);
        System.out.println("Doors after sorting with custom Comparator (by width): " + doors);
    }
}


class Door implements Comparable {

    private int height;
    private int width;

    public Door(int height, int width) {
        this.height = height;
        this.width = width;
    }

    // Implement Comparable interface: Return 0 if "equal",
    // -1 if current object is "smaller" than argument, 1 if current object is "bigger".
    // All terms can "equal", "smaller", "bigger" can refer to a abstract quality we can define in the method below.
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Door)) {
            return 0;
        } else {
            Door otherDoor = (Door) o;
            if (height < otherDoor.getHeight()) {
                // if our height is smaller than other, return -1
                return -1;
            } else if (height > otherDoor.getHeight()) {
                // if our height is bigger, return 1
                return 1;
            } else {
                // return 0 if height is equal
                return 0;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String toString() {
        return "Door[w=" + width + ", h=" + height + "]";
    }
}

