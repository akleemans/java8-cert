package ch.diso.javacert.exercises.ocp03;

public abstract class Animal {

    protected int legs;

    public Animal(int legs) {
        this.legs = legs;
    }

    public void walk() {
        System.out.println("This animal walks on " + legs + " legs.");
    }

    public abstract void eat();

}