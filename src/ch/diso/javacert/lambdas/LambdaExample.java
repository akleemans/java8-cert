package ch.diso.javacert.lambdas;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaExample {

    public static void main(String[] args) {

        List<Person> people = preparePeople();

        // some basic stream with a lambda
        System.out.println("All people:");
        people
                .stream()
                .map(person -> person.getName())
                .forEach(name -> {
                    System.out.println(name);
                });

        // filter with implicit predicate
        long belowThirty = people
                .stream()
                .filter(p -> p.getAge() < 30)
                .count();
        System.out.println("People below thirty: " + belowThirty);

        // explicit predicate
        Predicate<Person> overThirtyPredicate = p -> p.getAge() >= 30;
        long overThirty = people
                .stream()
                .filter(overThirtyPredicate)
                .count();
        System.out.println("People over thirty: " + overThirty);
    }

    private static List<Person> preparePeople() {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Finn", 23));
        people.add(new Person("Kylo Ren", 29));
        people.add(new Person("Han Solo", 63));
        people.add(new Person("Poe Dameron", 32));
        people.add(new Person("Chewbacca", 234));
        people.add(new Person("Rey", 19));
        people.add(new Person("Luke Skywalker", 53));

        return people;
    }
}
