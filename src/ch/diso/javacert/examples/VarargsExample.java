package ch.diso.javacert.examples;

import java.util.HashMap;
import java.util.Map;

public class VarargsExample {

    public static void main(String[] args) {
        Map<Engineer, Long> map = new HashMap<>();
        Engineer b1 = new Engineer("Joe");
        Engineer b2 = new Engineer("Clyde");

        // call method multiple times, with Varargs we can pass variable amount of same type
        addEngineers(map, b1, b2);
        addEngineers(map, new Engineer("John"), new Engineer("Johnny"), new Engineer("Jonathan"));
        //addEngineers(map, new Engineer(), "foo"); - will not compile, not the same type (or no string allowed)
        map.get(b1);

        // all keys
        System.out.println("Map: " + map);
    }

    // "Engineer... engineers" is the Varargs notation for a variable amount of parameters (of the same type).
    // See http://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html
    private static void addEngineers(Map<Engineer, Long> map, Engineer... engineers) {
        long wage = 1000L;

        // enhanced for (forEach) loop
        for (Engineer engineer : engineers) {
            engineer.setWage(wage);
            map.put(engineer, wage);
            wage += 1000L;
        }
    }
}

class Engineer {
    private long wage;
    private String name;

    public Engineer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setWage(long wage) {
        this.wage = wage;
    }
}