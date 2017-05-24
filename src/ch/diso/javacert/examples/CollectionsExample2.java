package ch.diso.javacert.examples;

import java.util.*;

public class CollectionsExample2 {

    public static void main(String[] args) {
        // Comparing ArrayDeque: Use as Stack & as Queue
        Queue<String> arrayDeque1 = new ArrayDeque<>();
        ArrayDeque<String> arrayDeque2 = new ArrayDeque<>();

        arrayDeque1.addAll(Arrays.asList("a", "b", "c"));
        arrayDeque2.addAll(Arrays.asList("a", "b", "c"));

        // popping first element on both
        System.out.println("First item on Queue (poll): " + arrayDeque1.poll());
        System.out.println("Next first item on Queue (remove): " + arrayDeque1.remove());
        // can't get/remove last element

        // ArrayDeque can provide elements from both sides:
        System.out.println("Last element of ArrayDeque: " + arrayDeque2.pollLast());
        System.out.println("First element of ArrayDeque: " + arrayDeque2.pollFirst());


        // removeIf() with Lambda
        List<Integer> intList = new ArrayList<>();
        intList.addAll(Arrays.asList(3, 6, 7, 8, 9, 0, 3, 4, -5, -8, 5, 6, 2, 4, 8, 5, -1, 6, 2, 3));

        System.out.println("All numbers: " + intList);
        intList.removeIf(i -> i < 0);
        System.out.println("# of all numbers equal or greater than 0: " + intList.size());
        intList.removeIf(i -> i % 2 != 0);
        System.out.println("# of all numbers dividable by 2: " + intList.size());
        intList.removeIf(i -> i != 6);
        System.out.println("# of the number 6: " + intList.size());
        System.out.println("List is now: " + intList);

        // replaceAll() with UnaryOperator (takes Integer and provides Integer)
        intList.replaceAll(i -> i - 3);
        System.out.println("Replacing each element by i-3: " + intList);

        // collection.forEach with Consumer
        intList.forEach(System.out::println);


        // Map.merge() with a BiFunction<T, U, V>
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "eins");
        map.put(2, "zwei");
        map.put(3, "three");
        map.put(4, "vier");
        System.out.println("Creating HashMap: " + map);

        List<String> englishWords = new ArrayList<>();
        englishWords.addAll(Arrays.asList("one", "two", "three"));

        // let's merge a value which already exists in the map
        map.merge(3, "drei", (String s, String t) -> {
            System.out.println("Merging '" + s + "' and '" + t + "'");
            if (englishWords.contains(s)) {
                return t;
            } else {
                return s;
            }
        });
        System.out.println("New HashMap: " + map);

        // now merge a new value into the map:
        map.merge(5, "fünf", (String s, String t) -> {
            System.out.println("Merging '" + s + "' and '" + t + "'");
            return "";
        });
        // No printing -> because 5 does not exists as key, it just gets added without the merge function being called!
        System.out.println("New HashMap is now: " + map);


        // Queue vs. Dequeue
        System.out.println("\nQueue vs. Deque.");
        Queue<String> queue = new LinkedList<>();
        // Queue: offer, peek, poll
        System.out.println("Offering a, b, c to queue.");
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");

        System.out.println("queue: " + queue);
        System.out.println("Polling: " + queue.poll());
        System.out.println("Peeking: " + queue.peek());
        System.out.println("Queue => waiting queue = FIFO");


        // Deque: offer/push, peek, pop
        Deque<String> deque = new ArrayDeque<>();
        System.out.println("Offering a, b, c to deque.");
        deque.push("a");
        deque.push("b");
        deque.push("c");

        System.out.println("deque: " + deque);
        System.out.println("Polling: " + deque.pop());
        System.out.println("Peeking: " + deque.peek());
        System.out.println("Deque => Stack = LIFO");
    }
}
