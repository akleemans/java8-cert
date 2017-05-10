package ch.diso.javacert.exercises.ocp03;

public class Cat extends Animal implements Pet {

    private String name;

    public Cat() {
        super(4);
    }

    public Cat(String name) {
        this();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        System.out.println(name + " likes to play with string.");
    }

    @Override
    public void eat() {
        System.out.println("Cats like to eat spiders and fish.");
    }
}
