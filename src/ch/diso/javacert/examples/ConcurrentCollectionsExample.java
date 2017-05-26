package ch.diso.javacert.examples;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentCollectionsExample {

    public static void main(String[] args) {
        // Try & iterate over normal HashMap while modifying it inside the loop.
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        // The loop below will throw a ConcurrentModificationException, because modifications
        // are made to the list but the Iterator is not updated (line map.remove()).
        for (String key : map.keySet()) {
            System.out.println("Keys: " + key);
            //map.remove(key);
        }

        // Now with a concurrent implementation of HashMap.
        // Note that we can reuse our Map<> map and point it to a new concurrent map.
        map = new ConcurrentHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        for (String key : map.keySet()) {
            map.remove(key);
        }

        // Now try with Runnables and a Concurrent List: CopyOnWriteArrayList
        final List<String> list = new CopyOnWriteArrayList<>();
        Runnable runnable = () -> {
            list.add("item " + list.size());
        };

        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 15; i++) {
            service.submit(runnable);
        }
        service.shutdown();

        // Wait for a moment and then show results.
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println("List is now: " + list);

        // Create a thread-safe collection from an ordinary Set
        Set<Long> set = new HashSet<Long>();
        set.add(1L);
        set.add(1L);
        set.add(2L);

        Set<Long> synchronizedSet = Collections.synchronizedSet(set);
        System.out.println("Synchronized set is of type " + synchronizedSet.getClass().getSimpleName());
        // ConcurrentSkipListSet or ConcurrentSkipListSet are two synchronized Set<> implementations.
    }
}
