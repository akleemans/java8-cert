package ch.diso.javacert.examples;

import java.util.*;

public class CollectionExample {

    public static void main(String[] args) {

        //  ========== List: ordered  ==========

        // ArrayList: fast list with O(1) read
        List<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("a", "b", "c"));
        System.out.println("arrayList: " + arrayList);
        System.out.println("arrayList.isEmpty(): " + arrayList.isEmpty());

        // LinkedList: list implemented by linked elements, fast add/remove at start/end of list
        List<String> linkedList = new LinkedList<>();
        linkedList.addAll(Arrays.asList("z", "y", "x", "w", "v"));
        // remove the element at index 2 (= "x")
        linkedList.remove(2);
        System.out.println("linkedList: " + linkedList);
        System.out.println("linkedList contains 'w': " + linkedList.contains("w"));


        //  ========== Sets: no duplicates  ==========

        // HashSet: fast set
        Set<String> hashSet = new HashSet<>();
        hashSet.add("g");
        hashSet.add("q");
        hashSet.add("h");
        hashSet.add("g"); // will insert nothing as "g" already is in hashSet
        hashSet.add("i");
        hashSet.add("l");
        System.out.println("hashSet: " + hashSet); // [q, g, h, i, l]

        // TreeSet: sorted set
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("g");
        treeSet.add("q");
        treeSet.add("h");
        treeSet.add("g");
        treeSet.add("i");
        treeSet.add("l");
        System.out.println("treeSet: " + treeSet); // [g, h, i, l, q]


        //  ========== Queue: collection for item processing ==========

        // LinkedList: List which is also a Queue
        Queue<String> linkedQueue = new LinkedList<>();
        linkedQueue.offer("first");
        System.out.println("Adding second element to linkedQueue: " + linkedQueue.offer("second")); // will return true if succeeded
        System.out.println("linkedQueue is now: " + linkedQueue);
        System.out.println("linkedQueue.peek(): " + linkedQueue.peek());
        System.out.println("linkedQueue is now: " + linkedQueue);
        System.out.println("linkedQueue.poll(): " + linkedQueue.poll());
        System.out.println("linkedQueue is now: " + linkedQueue);
        linkedQueue.poll();
        System.out.println("linkedQueue.peek() on empty list: " + linkedQueue.peek());

        // ArrayDeque: optimized Queue
        Queue<String> arrayDeque = new ArrayDeque<>();


        // ========== Map: key - value store ==========

        // HashMap: fast map, unsorted
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("1d", "1dfh");
        hashMap.put("b", "bbb");
        hashMap.put("1", "185");
        hashMap.put("a", "abc");
        hashMap.put("c", "cba");
        System.out.println("hashMap keys: " + hashMap.keySet()); // [1, a, b, 1d, c]
        System.out.println("hashMap values: " + hashMap.values());

        // TreeMap: sorted map
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("1d", "1dfh");
        treeMap.put("b", "bbb");
        treeMap.put("1", "185");
        treeMap.put("a", "abc");
        treeMap.put("c", "cba");
        System.out.println("treeMap keys: " + treeMap.keySet()); // [1, 1d, a, b, c]
    }
}
